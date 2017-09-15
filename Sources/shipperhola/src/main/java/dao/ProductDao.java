/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import model.Account;
import model.Category;
import model.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Quang Hiep
 */
public class ProductDao extends BaseDao {

    private static final RowMapper<Product> MAPPER = (rs, rowNum) -> new Product(
            rs.getInt("id"),
            rs.getDate("date"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getDouble("currentPrice"),
            rs.getBoolean("allowOrder"),
            new Category(rs.getInt("categoryId")),
            new Account(rs.getInt("sellerId"))
    );

    public ProductDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<Product> getAll() throws DataAccessException {
        return jdbcTemplate.query("SELECT * FROM Product", MAPPER);
    }

    public Product getById(int id) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Product WHERE id = ?", new Object[]{id}, MAPPER);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }
    
    public List<Product> getBySeller(int sellerId) throws DataAccessException {
        return jdbcTemplate.query("SELECT * FROM Product WHERE sellerId = ?", new Object[]{sellerId}, MAPPER);
    }

    public boolean add(Product product) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("date", product.getDate());
        params.put("name", product.getName());
        params.put("description", product.getDescription());
        params.put("currentPrice", product.getCurrentPrice());
        params.put("allowOrder", product.isAllowOrder());
        params.put("categoryId", product.getCategory().getId());
        params.put("sellerId", product.getSeller().getId());
        Number id = simpleJdbcInsert.withTableName("Product").usingGeneratedKeyColumns("id").executeAndReturnKey(params);
        if (id != null) {
            product.setId(id.intValue());
            return true;
        } else {
            return false;
        }
    }

    public boolean update(Product product) throws DataAccessException {
        return jdbcTemplate.update("UPDATE Product SET date = ?, name = ?, description = ?, currentPrice = ?, allowOrder = ?, categoryId = ?, sellerId = ? WHERE id = ?", product.getDate(), product.getName(), product.getDescription(), product.getCurrentPrice(), product.isAllowOrder(), product.getCategory().getId(), product.getSeller().getId(), product.getId()) > 0;
    }

    public boolean delete(int id) throws DataAccessException {
        return jdbcTemplate.update("DELETE FROM Product WHERE id = ?", id) > 0;
    }

    public List<Product> search(String keyword, Double minPrice, Double maxPrice, Date minDate, Date maxDate, Integer categoryId, String orderColumn, boolean ascending, Integer offsetRecords, Integer fetchRecords) throws DataAccessException {
        String sql = "SELECT * FROM Product WHERE 1 = 1";
        List args = new ArrayList();
        if (keyword != null) {
            sql += " AND LOWER(name) LIKE '%' + ? + '%'";
            args.add(keyword.toLowerCase());
        }
        if (minPrice != null) {
            sql += " AND currentPrice >= ?";
            args.add(minPrice);
        }
        if (maxPrice != null) {
            sql += " AND currentPrice <= ?";
            args.add(maxPrice);
        }
        if (minDate != null) {
            sql += " AND date >= ?";
            args.add(minDate);
        }
        if (maxDate != null) {
            sql += " AND date <= ?";
            args.add(maxDate);
        }
        if (categoryId != null) {
            sql += " AND categoryId = ?";
            args.add(categoryId);
        }
        if (orderColumn != null) {
            sql += String.format(" ORDER BY %s %s", orderColumn, ascending ? "ASC" : "DESC");
        }
        if (offsetRecords != null) {
            sql += " OFFSET ? ROWS";
            args.add(offsetRecords);
        }
        if (fetchRecords != null) {
            sql += " FETCH NEXT ? ROWS ONLY";
            args.add(fetchRecords);
        }
        return jdbcTemplate.query(sql, args.toArray(), MAPPER);
    }
}
