/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.List;
import javax.sql.DataSource;
import model.Product;
import model.ProductPicture;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class ProductPictureDao extends AbstractGenericDao<ProductPicture, Integer> {

    private static final RowMapper<ProductPicture> MAPPER = (rs, rowNum) -> new ProductPicture(
            rs.getInt("id"),
            rs.getString("fileName"),
            rs.getString("extension"),
            new Product(rs.getInt("productId"))
    );

    public ProductPictureDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<ProductPicture> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductPicture getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ProductPicture WHERE id = ?", new Object[]{id}, MAPPER);
    }

    @Override
    public boolean add(ProductPicture productPicture) {
        return jdbcTemplate.update("INSERT INTO ProductPicture(id, fileName, extension, productId) Values (?, ?, ?, ?)", productPicture.getId(), productPicture.getFileName(), productPicture.getExtension(), productPicture.getProduct().getId()) > 0;
    }

    @Override
    public boolean update(ProductPicture productPicture) {
        return jdbcTemplate.update("UPDATE ProductPicture SET  fileName = ?, extension = ? , productId = ? WHERE id = ?", productPicture.getFileName(), productPicture.getExtension(), productPicture.getProduct().getId(), productPicture.getId()) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM ProductPicture Where id = ? ", id) > 0;
    }

}
