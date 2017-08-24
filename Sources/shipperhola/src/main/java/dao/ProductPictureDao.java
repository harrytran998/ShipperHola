/*
 * Copyright © 2017 XVideos Team
 */
package dao;


import javax.sql.DataSource;
import model.Product;
import model.ProductPicture;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class ProductPictureDao extends AbstractGenericDao<ProductPicture, Integer>{
    private static final RowMapper<ProductPicture> MAPPER = (rs, rowNum) -> new ProductPicture(
            rs.getInt("id"),
            rs.getString("fileName"),
            rs.getString("extension"),
            rs.get
    );
    
    public ProductPictureDao(DataSource dataSource) {
        super(dataSource);
    }
}
