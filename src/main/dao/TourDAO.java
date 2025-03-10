package main.dao;

import main.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


@Repository
public class TourDAO {

    String className = this.getClass().getSimpleName();

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    private GapsRowMapper gapsRowMapper = new GapsRowMapper();

    public boolean tourExists(int tourId) {
        System.out.println(className + " - Invoking tourExists method - tourId = " + tourId);
        String sql = "select count(id) from tour where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",  tourId);
        boolean tourExists = jdbc.queryForObject(sql, params, Integer.class) > 0;
        System.out.println("Tour exists = " + tourId);
        System.out.println(className + " - tourExists method completed");
        return tourExists;
    } // end of tourExists method


    public Integer getMaxTourId() {
        System.out.println(className + " - Invoking getMaxTourId method");
        Integer maxKey = jdbc.queryForObject("SELECT MAX(id) from tour", new MapSqlParameterSource(), Integer.class);
        if (maxKey == null) maxKey = new Integer(0);
        System.out.println(className + " - getMaxTourId method completed - " + maxKey.intValue());
        return maxKey;
    }


    public boolean addTours() {
        System.out.println(className + " -  Invoking add120Tours method");
        MapSqlParameterSource params = new MapSqlParameterSource();
        int startKey = getMaxTourId() + 1;
        for (int key = startKey ; key < (startKey + 2001) ; key++) {
            Tour tour = new Tour();
            tour.setId(key);
            tour.setName("My Tour " + key);
            tour.setCode("12345");
            tour.setContinent(1);
            tour.setDate(new Date());
            tour.setDuration(key*2);
            tour.setAllInclusive(true);
            params.addValue("key",  tour.getId());
            params.addValue("name", tour.getName());
            params.addValue("code", tour.getCode());
            params.addValue("continent", tour.getContinent());
            params.addValue("date", tour.getDate());
            params.addValue("duration", tour.getDuration());
            params.addValue("all", tour.isAllInclusive());

            int rows = jdbc.update("insert into tour"
                    + " (id, name, code, continent, date, duration, all_inclusive)"
                    + " values (:key, :name, :code, :continent, :date, :duration, :all)",
                    params);

            System.out.println("inserted tour - " + key);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } // end try/catch
        } // end for loop
        return true;
    }


    public Tour getById(int tourId) {
        System.out.println("Invoking getById v 3- tourId = " + tourId);

        String sql = "select * from tour where id = :id";

        String sqlB = "update tour"
                + " set duration = :val "
                + " where id = 1";

        String sqlC = "delete from tourX where id = 2";

        Tour tour = null;
        try {
            tour = jdbc.queryForObject(sql, new MapSqlParameterSource("id", tourId), new RowMapper<Tour>() {
                public Tour mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return gapsRowMapper.mapTour(rs, rowNum);
                } // end anonymous method
            });
            System.out.println(tour.toString());
        } catch (EmptyResultDataAccessException e) {} // end try/catch

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("val", 400);
        jdbc.update(sqlB, params);
        System.out.println("value is now 400");

        System.out.println("method getById completed - tourId = " + tourId);
        return tour;

    }

} // end of TourDAO class
