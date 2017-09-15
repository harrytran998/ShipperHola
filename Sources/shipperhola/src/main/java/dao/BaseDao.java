/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * Abstract class for quick construction of DAO classes.
 *
 * @author Le Cao Nguyen
 */
public class BaseDao {

    protected final JdbcTemplate jdbcTemplate;
    protected final SimpleJdbcInsert simpleJdbcInsert;

    public BaseDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    }
}
