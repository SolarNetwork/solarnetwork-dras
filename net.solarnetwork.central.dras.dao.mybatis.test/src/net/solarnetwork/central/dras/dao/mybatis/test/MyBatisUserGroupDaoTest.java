/* ==================================================================
 * MyBatisUserDaoTest.java - Feb 2, 2010 2:20:41 PM
 * 
 * Copyright 2007-2010 SolarNetwork.net Dev Team
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

package net.solarnetwork.central.dras.dao.mybatis.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import net.solarnetwork.central.domain.FilterResults;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisLocationDao;
import net.solarnetwork.central.dras.dao.mybatis.MyBatisUserGroupDao;
import net.solarnetwork.central.dras.domain.Location;
import net.solarnetwork.central.dras.domain.Match;
import net.solarnetwork.central.dras.domain.Member;
import net.solarnetwork.central.dras.domain.User;
import net.solarnetwork.central.dras.domain.UserGroup;
import net.solarnetwork.central.dras.support.SimpleUserGroupFilter;

/**
 * Test case for the {@link MyBatisUserGroupDao} class.
 * 
 * @author matt
 * @version 1.0
 */
public class MyBatisUserGroupDaoTest extends AbstractMyBatisDaoTestSupport {

	private MyBatisUserGroupDao userGroupDao;
	private MyBatisLocationDao locationDao;

	private Long lastGroupId;

	@Before
	public void setUp() throws Exception {
		userGroupDao = new MyBatisUserGroupDao();
		userGroupDao.setSqlSessionFactory(getSqlSessionFactory());
		locationDao = new MyBatisLocationDao();
		locationDao.setSqlSessionFactory(getSqlSessionFactory());
		lastGroupId = null;
	}

	/**
	 * Test get UserGroup.
	 */
	@Test
	public void getGroupById() {
		setupTestLocation();
		setupTestUserGroup(TEST_GROUP_ID, TEST_GROUPNAME, TEST_LOCATION_ID);

		// test getting unit test user
		UserGroup group = userGroupDao.get(TEST_GROUP_ID);
		assertNotNull(group);
		assertNotNull(group.getId());
		assertEquals(TEST_GROUP_ID, group.getId());
		assertEquals(TEST_GROUPNAME, group.getName());
		assertEquals(TEST_LOCATION_ID, group.getLocationId());
		assertEquals(Boolean.TRUE, group.getEnabled());
	}

	/**
	 * Test get SolarNode that doesn't exist.
	 */
	@Test
	public void getNonExistingGroupById() {
		UserGroup group = userGroupDao.get(-99999L);
		assertNull(group);
	}

	private void validateGroup(UserGroup group, UserGroup entity) {
		assertNotNull("Group should exist", entity);
		assertNotNull("Created date should be set", entity.getCreated());
		assertEquals(group.getId(), entity.getId());
		assertEquals(group.getName(), entity.getName());
		assertEquals(group.getLocationId(), entity.getLocationId());
		assertEquals(group.getEnabled(), entity.getEnabled());
	}

	/**
	 * Test store new UserGroup.
	 */
	@Test
	public void insertGroup() {
		setupTestLocation();
		UserGroup group = new UserGroup();
		group.setName("Foo Test Group");
		group.setLocationId(TEST_LOCATION_ID);
		group.setEnabled(Boolean.TRUE);

		logger.debug("Inserting new UserGroup: " + group);

		Long id = userGroupDao.store(group);
		assertNotNull(id);

		UserGroup entity = userGroupDao.get(id);
		validateGroup(group, entity);

		lastGroupId = id;
	}

	/**
	 * Test store updated User.
	 */
	@Test
	public void updateGroup() {
		insertGroup();

		UserGroup group = userGroupDao.get(lastGroupId);
		assertEquals("Foo Test Group", group.getName());
		group.setName("foogroup.update");

		Long id = userGroupDao.store(group);
		assertEquals(lastGroupId, id);

		UserGroup entity = userGroupDao.get(id);
		validateGroup(group, entity);
	}

	/**
	 * Test a user with no roles is OK.
	 */
	@Test
	public void emptyGroupMemberSet() {
		insertGroup();
		Set<Member> members = userGroupDao.getMembers(lastGroupId, null);
		assertNotNull(members);
		assertEquals(0, members.size());
	}

	@Test
	public void getGroupMember() {
		insertGroup();
		assignUserToGroup(lastGroupId, TEST_USER_ID, TEST_EFFECTIVE_ID);

		Set<Member> expected = new HashSet<Member>(1);
		expected.add(new User(TEST_USER_ID));

		Set<Member> found = userGroupDao.getMembers(lastGroupId, new DateTime());
		validateMembers(expected, found);
	}

	@Test
	public void setGroupMember() {
		insertGroup();
		Set<Long> memberIds = new HashSet<Long>(1);
		memberIds.add(TEST_USER_ID);
		userGroupDao.assignMembers(lastGroupId, memberIds, TEST_EFFECTIVE_ID);

		Set<Member> members = new HashSet<Member>(1);
		members.add(new User(TEST_USER_ID));

		Set<Member> found = userGroupDao.getMembers(lastGroupId, new DateTime());
		validateMembers(members, found);
	}

	@Test
	public void findFilteredName() {
		insertGroup();

		SimpleUserGroupFilter filter = new SimpleUserGroupFilter();
		filter.setName("foo");
		FilterResults<Match> results = userGroupDao.findFiltered(filter, null, null, null);
		assertNotNull(results);
		assertEquals(1, results.getReturnedResultCount().intValue());
		assertEquals(lastGroupId, results.getResults().iterator().next().getId());
	}

	@Test
	public void findFilteredBoxEmptySet() {
		insertGroup();

		// now search for box coordinates
		SimpleUserGroupFilter filter = new SimpleUserGroupFilter();
		filter.setLatitude(new BigDecimal("-3.3"));
		filter.setLongitude(new BigDecimal("-3.5"));
		filter.setBoxLatitude(3.3);
		filter.setBoxLongitude(3.5);

		FilterResults<Match> results = userGroupDao.findFiltered(filter, null, null, null);
		assertNotNull(results);
		assertEquals(0, results.getReturnedResultCount().intValue());
	}

	@Test
	public void findFilteredBox() {
		insertGroup();

		// create a location with lat/long
		Location l = locationDao.get(TEST_LOCATION_ID);
		l.setLatitude(new BigDecimal("1.1234"));
		l.setLongitude(new BigDecimal("2.3456"));
		locationDao.store(l);

		// create group at this location, assign user to it
		assignUserToGroup(lastGroupId, TEST_USER_ID, TEST_EFFECTIVE_ID);

		// now search for box coordinates
		SimpleUserGroupFilter filter = new SimpleUserGroupFilter();
		filter.setLatitude(new BigDecimal("-3.3"));
		filter.setLongitude(new BigDecimal("-3.5"));
		filter.setBoxLatitude(3.3);
		filter.setBoxLongitude(3.5);

		FilterResults<Match> results = userGroupDao.findFiltered(filter, null, null, null);
		assertNotNull(results);
		assertEquals(1, results.getReturnedResultCount().intValue());
		assertEquals(lastGroupId, results.getResults().iterator().next().getId());
	}

	@Test
	public void findFilteredEnabled() {
		insertGroup();

		// first search with no filter
		SimpleUserGroupFilter filter = new SimpleUserGroupFilter();

		FilterResults<Match> results = userGroupDao.findFiltered(filter, null, null, null);
		assertNotNull(results);
		assertEquals(1, results.getReturnedResultCount().intValue());
		assertEquals(lastGroupId, results.getResults().iterator().next().getId());

		// now search for enabled only
		filter.setEnabled(Boolean.TRUE);
		results = userGroupDao.findFiltered(filter, null, null, null);
		assertNotNull(results);
		assertEquals(1, results.getReturnedResultCount().intValue());
		assertEquals(lastGroupId, results.getResults().iterator().next().getId());

		// now search for disabled only
		filter.setEnabled(Boolean.FALSE);
		results = userGroupDao.findFiltered(filter, null, null, null);
		assertNotNull(results);
		assertEquals(0, results.getReturnedResultCount().intValue());
	}

}
