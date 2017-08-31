/*
 * Copyright © 2017 XVideos Team
 */
package dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import model.Category;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class CategoryDao extends AbstractGenericDao<Category, Integer >{
    
    private static final RowMapper<Category> MAPPER = (rs, rowNum) -> new Category(
            rs.getInt("id"),
            rs.getString("name")
    );
    
    public CategoryDao(DataSource dataSource){
        super(dataSource);
    }

    @Override
    public List<Category> getAll() {
        return jdbcTemplate.query("SELECT * FROM Category", MAPPER);
    }

    @Override
    public Category getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Category WHERE id = ?", new Object[]{id} ,MAPPER);
    }

    @Override
    public boolean add(Category category) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", category.getName());
        Number id = simpleJdbcInsert.withTableName("Category").usingGeneratedKeyColumns("id").executeAndReturnKey(params);
        if (id != null) {
            category.setId(id.intValue());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Category category) {
        return jdbcTemplate.update("UPDATE Category SET name = ? WHERE id = ?", category.getName(), category.getId()) > 0;      
    }

    @Override
    public boolean delete(Integer  id) {
        return jdbcTemplate.update("DELETE FROM Category WHERE id = ?", id) >0;
    }
    
    
        
}
