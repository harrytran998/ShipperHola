/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.io.Serializable;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * Abstract class for quick construction of DAO classes.
 * @author Le Cao Nguyen
 * @param <T> The entity's class.
 * @param <U> The class of entity's ID attribute.
 */
public abstract class AbstractGenericDao<T, U extends Serializable> {
    protected final JdbcTemplate jdbcTemplate;
    protected final SimpleJdbcInsert simpleJdbcInsert;
    
    public AbstractGenericDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    }
    
    public abstract List<T> getAll();
    
    public abstract T getById(U id);
    
    public abstract boolean add(T entity);
    
    public abstract boolean update(T entity);
    
    public abstract boolean delete(U id);
    
    
    
}
