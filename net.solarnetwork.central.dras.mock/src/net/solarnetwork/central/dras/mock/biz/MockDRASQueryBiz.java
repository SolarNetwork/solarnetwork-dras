/* ==================================================================
 * MockDRASQueryBiz.java - May 1, 2011 4:49:24 PM
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

package net.solarnetwork.central.dras.mock.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.MutableDateTime;
import org.joda.time.Period;
import org.joda.time.ReadableInterval;
import net.solarnetwork.central.datum.domain.AggregateGeneralLocationDatumFilter;
import net.solarnetwork.central.datum.domain.AggregateGeneralNodeDatumFilter;
import net.solarnetwork.central.datum.domain.GeneralLocationDatumFilter;
import net.solarnetwork.central.datum.domain.GeneralLocationDatumFilterMatch;
import net.solarnetwork.central.datum.domain.GeneralNodeDatumFilter;
import net.solarnetwork.central.datum.domain.GeneralNodeDatumFilterMatch;
import net.solarnetwork.central.datum.domain.ReportingGeneralLocationDatumMatch;
import net.solarnetwork.central.datum.domain.ReportingGeneralNodeDatum;
import net.solarnetwork.central.datum.domain.ReportingGeneralNodeDatumMatch;
import net.solarnetwork.central.domain.FilterResults;
import net.solarnetwork.central.domain.Location;
import net.solarnetwork.central.domain.LocationMatch;
import net.solarnetwork.central.domain.SortDescriptor;
import net.solarnetwork.central.query.biz.QueryBiz;
import net.solarnetwork.central.query.domain.ReportableInterval;
import net.solarnetwork.central.support.BasicFilterResults;
import net.solarnetwork.domain.GeneralNodeDatumSamples;

/**
 * Mock implementation of QueryBiz that supports the
 * {@link MockDRASObserverBiz}.
 * 
 * @author matt
 * @version 1.1
 */
public class MockDRASQueryBiz implements QueryBiz {

	@SuppressWarnings("unused")
	private final ReadableInterval reportableInterval;

	private final float generationWattHours = 100.0f;

	/**
	 * Constructor.
	 * 
	 * @param observerBiz
	 *        the observer biz to setup mock data with
	 */
	public MockDRASQueryBiz(MockDRASObserverBiz observerBiz) {
		DateTimeZone tz = DateTimeZone.forID("Pacific/Auckland");
		reportableInterval = new Interval(new DateTime(2011, 1, 1, 8, 0, 0, 0, tz),
				new DateTime(2011, 2, 1, 8, 0, 0, 0, tz));
		/*
		 * for ( Program program : observerBiz.getAllPrograms(null) ) { for (
		 * NodeIdentity node : observerBiz.getProgramParticipants(program, null,
		 * null)) {
		 * 
		 * 
		 * } }
		 */
	}

	@Override
	public ReportableInterval getReportableInterval(Long nodeId, String sourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getAvailableSources(Long nodeId, DateTime start, DateTime end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilterResults<GeneralNodeDatumFilterMatch> findFilteredGeneralNodeDatum(
			GeneralNodeDatumFilter filter, List<SortDescriptor> sortDescriptors, Integer offset,
			Integer max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilterResults<ReportingGeneralNodeDatumMatch> findFilteredAggregateGeneralNodeDatum(
			AggregateGeneralNodeDatumFilter filter, List<SortDescriptor> sortDescriptors, Integer offset,
			Integer max) {
		MutableDateTime mdt = new MutableDateTime(filter.getStartDate());
		Period period;
		switch (filter.getAggregation()) {
			case Hour:
				period = Period.hours(1);
				break;

			case Day:
				period = Period.days(1);
				break;

			case Week:
				period = Period.weeks(1);
				break;

			case Month:
				period = Period.months(1);
				break;

			default:
				period = Period.minutes(1);
		}
		List<ReportingGeneralNodeDatumMatch> results = new ArrayList<ReportingGeneralNodeDatumMatch>();
		do {
			ReportingGeneralNodeDatum d = new ReportingGeneralNodeDatum();
			d.setNodeId(filter.getNodeId());
			d.setCreated(mdt.toDateTime());

			GeneralNodeDatumSamples samples = new GeneralNodeDatumSamples();

			Duration dur = period.toDurationFrom(mdt);
			float hours = (float) ((double) dur.getMillis() / (double) (1000 * 60 * 60));
			samples.putAccumulatingSampleValue("wattHours", Double.valueOf(hours * generationWattHours));

			d.setSamples(samples);
			results.add(d);
			mdt.add(period);
		} while ( mdt.isBefore(filter.getEndDate()) );
		return new BasicFilterResults<ReportingGeneralNodeDatumMatch>(results);

	}

	@Override
	public FilterResults<GeneralLocationDatumFilterMatch> findGeneralLocationDatum(
			GeneralLocationDatumFilter filter, List<SortDescriptor> sortDescriptors, Integer offset,
			Integer max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilterResults<ReportingGeneralLocationDatumMatch> findAggregateGeneralLocationDatum(
			AggregateGeneralLocationDatumFilter filter, List<SortDescriptor> sortDescriptors,
			Integer offset, Integer max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getLocationAvailableSources(Long locationId, DateTime start, DateTime end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReportableInterval getLocationReportableInterval(Long locationId, String sourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilterResults<LocationMatch> findFilteredLocations(Location filter,
			List<SortDescriptor> sortDescriptors, Integer offset, Integer max) {
		// TODO Auto-generated method stub
		return null;
	}

}
