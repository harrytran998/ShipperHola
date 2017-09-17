/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import model.Account;
import model.Product;
import model.ProductReview;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Quang Hiep
 */
public class ProductReviewDao extends BaseDao {

    private static final RowMapper<ProductReview> MAPPER = (rs, rowNum) -> new ProductReview(
            rs.getInt("id"),
            rs.getTimestamp("date"),
            rs.getDouble("rating"),
            rs.getString("content"),
            new Account(rs.getInt("accountId")),
            new Product(rs.getInt("productId"))
    );

    public ProductReviewDao(DataSource dataSource) {
        super(dataSource);
    }

    public ProductReview getById(int id) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ProductReview WHERE id = ?", new Object[]{id}, MAPPER);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    public List<ProductReview> getByProduct(int productId) throws DataAccessException {
        return jdbcTemplate.query("SELECT * FROM ProductReview WHERE productId = ?", new Object[]{productId}, MAPPER);
    }
    
    public boolean add(ProductReview productReview) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("date", productReview.getDate());
        params.put("rating", productReview.getRating());
        params.put("content", productReview.getContent());
        params.put("accountId", productReview.getAccount().getId());
        params.put("productId", productReview.getProduct().getId());
        Number id = simpleJdbcInsert.withTableName("ProductReview").usingGeneratedKeyColumns("id").executeAndReturnKey(params);
        if (id != null) {
            productReview.setId(id.intValue());
            return true;
        } else {
            return false;
        }
    }

    public boolean update(ProductReview productReview) throws DataAccessException {
        return jdbcTemplate.update("UPDATE ProductReview SET date = ?, rating = ?, content = ?, accountId = ?, productId = ? WHERE id = ?", productReview.getDate(), productReview.getRating(), productReview.getContent(), productReview.getAccount().getId(), productReview.getProduct().getId(), productReview.getId()) > 0;
    }

    public boolean delete(int id) throws DataAccessException {
        return jdbcTemplate.update("DELETE FROM ProductReview WHERE id = ?", id) > 0;
    }
    
    public boolean deleteByProduct(int productId) throws DataAccessException {
        return jdbcTemplate.update("DELETE FROM ProductReview WHERE productId = ?", productId) > 0;
    }
}
