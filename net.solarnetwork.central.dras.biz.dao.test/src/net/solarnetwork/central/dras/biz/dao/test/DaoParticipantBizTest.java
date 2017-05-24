/* ==================================================================
 * DaoParticipantBizTest.java - Jun 14, 2011 4:48:56 PM
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
 * $Id$
 * ==================================================================
 */

package net.solarnetwork.central.dras.biz.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import net.solarnetwork.central.dras.biz.dao.DaoParticipantBiz;
import net.solarnetwork.central.dras.dao.CapabilityDao;
import net.solarnetwork.central.dras.dao.ConstraintDao;
import net.solarnetwork.central.dras.dao.EffectiveDao;
import net.solarnetwork.central.dras.dao.EventDao;
import net.solarnetwork.central.dras.dao.LocationDao;
import net.solarnetwork.central.dras.dao.ParticipantDao;
import net.solarnetwork.central.dras.dao.ParticipantGroupDao;
import net.solarnetwork.central.dras.dao.ProgramDao;
import net.solarnetwork.central.dras.dao.UserDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisCapabilityDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisConstraintDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisEffectiveDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisEventDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisLocationDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisParticipantDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisParticipantGroupDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisProgramDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisUserDao;
import net.solarnetwork.central.dras.dao.mybatis.test.AbstractMyBatisDaoTestSupport;
import net.solarnetwork.central.dras.domain.Capability;

/**
 * Test case for {@link DaoParticipantBiz}.
 * 
 * @author matt
 * @version $Revision$
 */
public class DaoParticipantBizTest extends AbstractMyBatisDaoTestSupport {

	private CapabilityDao capabilityDao;
	private ConstraintDao constraintDao;
	private EffectiveDao effectiveDao;
	private EventDao eventDao;
	private LocationDao locationDao;
	private ParticipantDao participantDao;
	private ParticipantGroupDao participantGroupDao;
	private ProgramDao programDao;
	private UserDao userDao;

	private DaoParticipantBiz participantBiz;

	@Before
	public void setup() {
		capabilityDao = new MyBatisCapabilityDao();
		((SqlSessionDaoSupport) capabilityDao).setSqlSessionFactory(getSqlSessionFactory());
		constraintDao = new MyBatisConstraintDao();
		((SqlSessionDaoSupport) constraintDao).setSqlSessionFactory(getSqlSessionFactory());
		effectiveDao = new MyBatisEffectiveDao();
		((SqlSessionDaoSupport) effectiveDao).setSqlSessionFactory(getSqlSessionFactory());
		eventDao = new MyBatisEventDao();
		((SqlSessionDaoSupport) eventDao).setSqlSessionFactory(getSqlSessionFactory());
		locationDao = new MyBatisLocationDao();
		((SqlSessionDaoSupport) locationDao).setSqlSessionFactory(getSqlSessionFactory());
		participantDao = new MyBatisParticipantDao();
		((SqlSessionDaoSupport) participantDao).setSqlSessionFactory(getSqlSessionFactory());
		participantGroupDao = new MyBatisParticipantGroupDao();
		((SqlSessionDaoSupport) participantGroupDao).setSqlSessionFactory(getSqlSessionFactory());
		programDao = new MyBatisProgramDao();
		((SqlSessionDaoSupport) programDao).setSqlSessionFactory(getSqlSessionFactory());
		userDao = new MyBatisUserDao();
		((SqlSessionDaoSupport) userDao).setSqlSessionFactory(getSqlSessionFactory());

		participantBiz = new DaoParticipantBiz(effectiveDao, userDao, capabilityDao, locationDao,
				participantDao, participantGroupDao, constraintDao);

		SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken(TEST_USERNAME, "unittest"));
	}

	@Test
	public void storeParticipantCapability() {
		setupTestLocation();
		setupTestParticipant();

		Capability input = new Capability();
		input.setShedCapacityWatts(1000L);
		input.setShedCapacityWattHours(2000L);

		Capability result = participantBiz.storeParticipantCapability(TEST_PARTICIPANT_ID, input);
		assertNotNull(result);
		assertNotNull(result.getId());

		// try to store same details again, should not make new Capability
		Capability result2 = participantBiz.storeParticipantCapability(TEST_PARTICIPANT_ID, input);
		assertNotNull(result);
		assertEquals(result, result2);
	}
}
