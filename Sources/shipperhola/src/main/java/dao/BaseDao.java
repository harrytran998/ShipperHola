/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Abstract class for quick construction of DAO classes.
 *
 * @author Le Cao Nguyen
 */
public class BaseDao {

    protected final JdbcTemplate jdbcTemplate;

    public BaseDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
