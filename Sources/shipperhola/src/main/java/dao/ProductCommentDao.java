/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.List;
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
        return jdbcTemplate.update("INSERT INTO ProductComment(id, date, content, accountId, repliedCommentId, productId) Values (?, ?, ?, ?, ?, ?)", productComment.getId(), productComment.getDate(), productComment.getContent(), productComment.getAccount().getId(), productComment.getRepliedComment().getId(), productComment.getProduct().getId()) > 0;
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
