/* ==================================================================
 * SimpleAlertBizTest.java - Jun 19, 2011 2:06:24 PM
 * 
 * Copyright 2007-2011 SolarNetwork.net Dev Team
 * 
 * This program is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as 
 * published by the Free Software Foundation; either version 2 of 
 * the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with this program; if not, write to the Free Software 
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 
 * 02111-1307 USA
 * ==================================================================
 */

package net.solarnetwork.central.dras.biz.alert.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import net.solarnetwork.central.dras.biz.AlertBiz;
import net.solarnetwork.central.dras.biz.AlertBiz.AlertProcessingResult;
import net.solarnetwork.central.dras.biz.alert.SimpleAlertBiz;
import net.solarnetwork.central.dras.dao.EventDao;
import net.solarnetwork.central.dras.dao.OutboundMailDao;
import net.solarnetwork.central.dras.dao.ProgramDao;
import net.solarnetwork.central.dras.dao.UserDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisEventDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisOutboundMailDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisProgramDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisUserDao;
import net.solarnetwork.central.dras.dao.mybatis.test.AbstractMyBatisDaoTestSupport;
import net.solarnetwork.central.dras.domain.Event;
import net.solarnetwork.central.dras.domain.User;
import net.solarnetwork.central.dras.domain.UserContact;
import net.solarnetwork.central.dras.domain.UserContact.ContactKind;
import net.solarnetwork.central.dras.support.SimpleAlert;
import net.solarnetwork.central.test.CallingThreadExecutorService;

/**
 * Test case for {@link SimpleAlertBiz}.
 * 
 * @author matt
 * @version 1.0
 */
public class SimpleAlertBizTest extends AbstractMyBatisDaoTestSupport {

	private EventDao eventDao;
	private OutboundMailDao outboundMailDao;
	private ProgramDao programDao;
	private UserDao userDao;

	private SimpleAlertBiz alertBiz;

	@Before
	public void setupSecurity() {
		eventDao = new MyBatisEventDao();
		((SqlSessionDaoSupport) eventDao).setSqlSessionFactory(getSqlSessionFactory());
		outboundMailDao = new MyBatisOutboundMailDao();
		((SqlSessionDaoSupport) outboundMailDao).setSqlSessionFactory(getSqlSessionFactory());
		programDao = new MyBatisProgramDao();
		((SqlSessionDaoSupport) programDao).setSqlSessionFactory(getSqlSessionFactory());
		userDao = new MyBatisUserDao();
		((SqlSessionDaoSupport) userDao).setSqlSessionFactory(getSqlSessionFactory());

		alertBiz = new SimpleAlertBiz();
		alertBiz.setMailSender(new net.solarnetwork.central.mail.mock.MockMailSender());
		alertBiz.setEventDao(eventDao);
		alertBiz.setOutboundMailDao(outboundMailDao);
		alertBiz.setProgramDao(programDao);
		alertBiz.setUserDao(userDao);
		alertBiz.setProcessor(new CallingThreadExecutorService());

		SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken(TEST_USERNAME, "unittest"));
	}

	private Event setupEvent() {
		// assign contact info to user
		User u = userDao.get(TEST_USER_ID);
		List<UserContact> contacts = new ArrayList<UserContact>();
		contacts.add(new UserContact(ContactKind.EMAIL, "tester@localhost.localdomain", 1));
		u.setContactInfo(contacts);
		userDao.store(u);

		setupTestLocation();
		setupTestProgram(TEST_PROGRAM_ID, TEST_PROGRAM_NAME);
		setupTestParticipant();
		setupTestEvent(TEST_EVENT_ID, TEST_PROGRAM_ID);

		// add participant to program
		Set<Long> memberIds = new HashSet<Long>(1);
		memberIds.add(TEST_PARTICIPANT_ID);
		programDao.assignParticipantMembers(TEST_PROGRAM_ID, memberIds, TEST_EFFECTIVE_ID);

		// assign participant to event
		eventDao.assignParticipantMembers(TEST_EVENT_ID, memberIds, TEST_EFFECTIVE_ID);

		return eventDao.get(TEST_EVENT_ID);
	}

	@Test
	public void processEventCreatedAlert() throws Exception {
		Event event = setupEvent();

		SimpleAlert alert = new SimpleAlert();
		alert.setAlertType(AlertBiz.ALERT_TYPE_ENTITY_CREATED);
		alert.setRegardingIdentity(event);

		Future<AlertProcessingResult> future = alertBiz.postAlert(alert);
		assertNotNull(future);
		AlertProcessingResult result = future.get(1, TimeUnit.MINUTES);
		assertNotNull(result);
		assertNotNull(result.getAlert());
		assertEquals(alert.getAlertType(), result.getAlert().getAlertType());
		assertNotNull(result.getAlertedUsers());
		assertEquals(1, result.getAlertedUsers().size());
		assertTrue(result.getAlertedUsers().contains(new User(TEST_USER_ID)));
	}
}
