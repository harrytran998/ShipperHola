/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import model.Category;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class CategoryDao extends BaseDao {

    private static final RowMapper<Category> MAPPER = (rs, rowNum) -> new Category(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description")
    );

    public CategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<Category> getAll() throws DataAccessException {
        return jdbcTemplate.query("SELECT * FROM Category", MAPPER);
    }

    public Category getById(int id) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Category WHERE id = ?", new Object[]{id}, MAPPER);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    public boolean add(Category category) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("name", category.getName());
        params.put("description", category.getDescription());
        Number id = simpleJdbcInsert.withTableName("Category").usingGeneratedKeyColumns("id").executeAndReturnKey(params);
        if (id != null) {
            category.setId(id.intValue());
            return true;
        } else {
            return false;
        }
    }

    public boolean update(Category category) throws DataAccessException {
        return jdbcTemplate.update("UPDATE Category SET name = ?, description = ? WHERE id = ?", category.getName(), category.getDescription(), category.getId()) > 0;
    }

}
