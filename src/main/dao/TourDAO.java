package main.dao;

import main.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional
public class TourDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    private GapsRowMapper gapsRowMapper = new GapsRowMapper();

    public boolean tourExists(int tourId) {
        System.out.println("Invoking tourExists - tourId = " + tourId);
        String sql = "select count(id) from tour where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",  tourId);
        boolean tourExists = jdbc.queryForObject(sql, params, Integer.class) > 0;
        System.out.println("Tour exists = " + tourId);
        return tourExists;
    } // end of tourExists method


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

        jdbc.update(sqlC, params);  // invalid SQL tourX is not a table name

        System.out.println("method getById completed - tourId = " + tourId);
        return tour;

    }



} // end of TourDAO class
