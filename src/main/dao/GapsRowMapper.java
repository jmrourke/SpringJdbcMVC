package main.dao;

import main.model.Tour;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("gapsRowMapper")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
/**
 * This class will perform the spring mapping of any database table.
 * 
 * @author BS58194
		private Integer id;
		private String name;
		private String code;
		private int continent;
		private Date date;
		private int duration;
		private boolean allInclusive = false;
 *
 */
public class GapsRowMapper {

	public Tour mapTour(ResultSet rs, int rowNum) throws SQLException {
		Tour tour = new Tour();
		tour.setId(new Integer(rs.getInt("id")));
		tour.setAllInclusive(rs.getBoolean("all_inclusive"));
		tour.setCode(rs.getString("code"));
		tour.setContinent(rs.getInt("continent"));
		tour.setDate(rs.getTimestamp("date"));
		tour.setDuration(rs.getInt("duration"));
		tour.setName(rs.getString("name"));
		return tour;
	} // end of mapTour method


} // end of GapsRowMapper