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
import model.ProductComment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Quang Hiep
 */
public class ProductCommentDao extends BaseDao {

    private static final RowMapper<ProductComment> MAPPER = (rs, rowNum) -> new ProductComment(
            rs.getInt("id"),
            rs.getDate("date"),
            rs.getString("content"),
            new Account(rs.getInt("accountId")),
            new ProductComment(rs.getInt("repliedCommentId")),
            new Product(rs.getInt("productId"))
    );

    public ProductCommentDao(DataSource dataSource) {
        super(dataSource);
    }

    public ProductComment getById(int id) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ProductComment WHERE id = ?", new Object[]{id}, MAPPER);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    public List<ProductComment> getByProduct(int productId) throws DataAccessException {
        return jdbcTemplate.query("SELECT * FROM ProductComment WHERE productId = ?", new Object[]{productId}, MAPPER);
    }

    public boolean add(ProductComment productComment) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("date", productComment.getDate());
        params.put("content", productComment.getContent());
        params.put("accountId", productComment.getAccount().getId());
        params.put("repliedCommentId", productComment.getRepliedComment().getId());
        params.put("productId", productComment.getProduct().getId());
        Number id = simpleJdbcInsert.withTableName("ProductComment").usingGeneratedKeyColumns("id").executeAndReturnKey(params);
        if (id != null) {
            productComment.setId(id.intValue());
            return true;
        } else {
            return false;
        }
    }

    public boolean update(ProductComment productComment) throws DataAccessException {
        return jdbcTemplate.update("UPDATE ProductComment SET date = ?, content = ?, accountId = ?, repliedCommentId = ?, productId = ? WHERE id = ?", productComment.getDate(), productComment.getContent(), productComment.getAccount().getId(), productComment.getRepliedComment().getId(), productComment.getProduct().getId(), productComment.getId()) > 0;
    }

    public boolean delete(Integer id) throws DataAccessException {
        return jdbcTemplate.update("DELETE FROM ProductComment WHERE id = ?", id) > 0;
    }

}
