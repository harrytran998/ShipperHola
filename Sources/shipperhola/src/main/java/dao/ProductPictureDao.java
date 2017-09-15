/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import model.Product;
import model.ProductPicture;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class ProductPictureDao extends BaseDao {

    private static final RowMapper<ProductPicture> MAPPER = (rs, rowNum) -> new ProductPicture(
            rs.getInt("id"),
            rs.getString("fileName"),
            rs.getString("extension"),
            new Product(rs.getInt("productId"))
    );

    public ProductPictureDao(DataSource dataSource) {
        super(dataSource);
    }

    public ProductPicture getById(int id) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ProductPicture WHERE id = ?", new Object[]{id}, MAPPER);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    public List<ProductPicture> getByProduct(int productId) throws DataAccessException {
        return jdbcTemplate.query("SELECT * FROM ProductPicture WHERE productId = ?", new Object[]{productId}, MAPPER);
    }

    public boolean add(ProductPicture productPicture) {
        Map<String, Object> params = new HashMap<>();
        params.put("fileName", productPicture.getFileName());
        params.put("extension", productPicture.getExtension());
        params.put("productId", productPicture.getProduct().getId());
        Number id = simpleJdbcInsert.withTableName("ProductPicture").usingGeneratedKeyColumns("id").executeAndReturnKey(params);
        if (id != null) {
            productPicture.setId(id.intValue());
            return true;
        } else {
            return false;
        }
    }

    public boolean update(ProductPicture productPicture) throws DataAccessException {
        return jdbcTemplate.update("UPDATE ProductPicture SET fileName = ?, extension = ?, productId = ? WHERE id = ?", productPicture.getFileName(), productPicture.getExtension(), productPicture.getProduct().getId(), productPicture.getId()) > 0;
    }

    public boolean delete(int id) throws DataAccessException {
        return jdbcTemplate.update("DELETE FROM ProductPicture WHERE id = ?", id) > 0;
    }

    public boolean deleteByProduct(int productId) throws DataAccessException {
        return jdbcTemplate.update("DELETE FROM ProductPicture WHERE productId = ?", productId) > 0;
    }
}
