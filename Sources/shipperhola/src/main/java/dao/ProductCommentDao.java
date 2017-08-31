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
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Quang Hiep
 */
public class ProductCommentDao extends AbstractGenericDao<ProductComment, Integer> {

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

    @Override
    public List<ProductComment> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProductComment getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ProductComment WHERE id = ?", new Object[]{id}, MAPPER);
    }

    @Override
    public boolean add(ProductComment productComment) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", productComment.getDate());
        params.put("content", productComment.getContent());
        params.put("accountId", productComment.getAccount().getId());
        params.put("repliedComment", productComment.getRepliedComment().getId());
        params.put("productId", productComment.getProduct().getId());
        Number id = simpleJdbcInsert.withTableName("ProductComment").usingGeneratedKeyColumns("id").executeAndReturnKey(params);
        if (id != null) {
            productComment.setId(id.intValue());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(ProductComment productComment) {
        return jdbcTemplate.update("UPDATE ProductComment SET  date = ?, content = ? , accountId = ?, repliedCommentId = ?, productId = ? WHERE id = ?", productComment.getDate(), productComment.getContent(), productComment.getAccount().getId(), productComment.getRepliedComment().getId(), productComment.getProduct().getId(), productComment.getId()) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM ProductComment Where id = ? ", id) > 0;
    }

}
