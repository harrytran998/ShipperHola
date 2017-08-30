/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.List;
import javax.sql.DataSource;
import model.Account;
import model.Product;
import model.ProductReview;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Quang Hiep
 */
public class ProductReviewDao extends AbstractGenericDao<ProductReview, Integer> {

    private static final RowMapper<ProductReview> MAPPER = (rs, rowNum) -> new ProductReview(
            rs.getInt("id"),
            rs.getInt("rating"),
            rs.getString("content"),
            new Account(rs.getInt("accountId")),
            new Product(rs.getInt("productId"))
    );

    public ProductReviewDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<ProductReview> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductReview getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ProductReview WHERE id = ?", new Object[]{id}, MAPPER);
    }

    @Override
    public boolean add(ProductReview productReview) {
        return jdbcTemplate.update("INSERT INTO ProductReview(id, rating, content, accountId, productId) Values (?, ?, ?, ?, ?)", productReview.getId(), productReview.getRating(), productReview.getContent(), productReview.getAccount().getId(), productReview.getProduct().getId()) > 0;
    }

    @Override
    public boolean update(ProductReview productReview) {
        return jdbcTemplate.update("UPDATE ProductReview SET  rating = ?, content = ? , accountId = ? , productId = ?  WHERE id = ?", productReview.getRating(), productReview.getContent(), productReview.getAccount().getId(), productReview.getProduct().getId() , productReview.getId()) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM ProductReview Where id = ? ", id) >0;
    }
}
