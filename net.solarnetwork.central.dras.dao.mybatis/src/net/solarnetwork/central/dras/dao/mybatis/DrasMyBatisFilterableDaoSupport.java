/* ==================================================================
 * DrasMyBatisFilterableDaoSupport.java - Jun 8, 2011 6:44:15 PM
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

package net.solarnetwork.central.dras.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import net.solarnetwork.central.dao.FilterableDao;
import net.solarnetwork.central.domain.Entity;
import net.solarnetwork.central.domain.Filter;
import net.solarnetwork.central.domain.FilterMatch;
import net.solarnetwork.central.domain.FilterResults;
import net.solarnetwork.central.domain.SortDescriptor;
import net.solarnetwork.central.support.BasicFilterResults;

/**
 * Abstract base MyBatis FilterableDao for DRAS support.
 * 
 * @author matt
 * @version 1.0
 */
public abstract class DrasMyBatisFilterableDaoSupport<T extends Entity<Long>, M extends FilterMatch<Long>, F extends Filter>
		extends DrasMyBatisGenericDaoSupport<T> implements FilterableDao<M, Long, F> {

	/** A query property for a general Filter object value. */
	public static final String FILTER_PROPERTY = "filter";

	private final Class<? extends M> filterResultClass;

	/**
	 * Constructor.
	 * 
	 * @param domainClass
	 *        the domain class
	 */
	public DrasMyBatisFilterableDaoSupport(Class<? extends T> domainClass,
			Class<? extends M> filterResultClass) {
		super(domainClass);
		this.filterResultClass = filterResultClass;
	}

	/**
	 * Get the filter query name for a given domain.
	 * 
	 * @param filterDomain
	 *        the domain
	 * @return query name
	 */
	protected String getFilteredQuery(String filterDomain, F filter) {
		return getQueryForAll() + "-" + filterDomain;
	}

	/**
	 * Callback to alter the default SQL properties set up by
	 * {@link #findFiltered(Filter, List, Integer, Integer)}.
	 * 
	 * @param filter
	 *        the current filter
	 * @param sqlProps
	 *        the properties
	 */
	protected void postProcessFilterProperties(F filter, Map<String, Object> sqlProps) {
		// nothing here, extending classes can implement
	}

	@Override
	public FilterResults<M> findFiltered(F filter, List<SortDescriptor> sortDescriptors, Integer offset,
			Integer max) {
		final String filterDomain = getMemberDomainKey(filterResultClass);
		final String query = getFilteredQuery(filterDomain, filter);
		Map<String, Object> sqlProps = new HashMap<String, Object>(1);
		sqlProps.put(FILTER_PROPERTY, filter);
		if ( sortDescriptors != null && sortDescriptors.size() > 0 ) {
			sqlProps.put(SORT_DESCRIPTORS_PROPERTY, sortDescriptors);
		}
		postProcessFilterProperties(filter, sqlProps);
		List<M> results = null;
		if ( offset != null && offset >= 0 && max != null && max > 0 ) {
			results = getSqlSession().selectList(query, sqlProps, new RowBounds(offset, max));
		} else {
			results = getSqlSession().selectList(query, sqlProps);
		}

		return new BasicFilterResults<M>(results, Long.valueOf(results.size()), offset, results.size());
	}

}
