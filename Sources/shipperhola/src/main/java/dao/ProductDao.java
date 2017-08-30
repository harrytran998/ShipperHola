/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import model.Account;
import model.Category;
import model.Product;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Quang Hiep
 */
public class ProductDao extends AbstractGenericDao<Product, Integer> {

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

    @Override
    public List<Product> getAll() {
        return jdbcTemplate.query("SELECT * FROM Product", MAPPER);
    }

    @Override
    public Product getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Product WHERE id = ?", new Object[]{id}, MAPPER);
    }

    @Override
    public boolean add(Product product) {
        return jdbcTemplate.update("INSERT INTO Product(id, date, name, description, currentPrice, allowOrder, categoryId, sellerId) Values (?, ?, ?, ?, ?, ?, ?, ?)", product.getId(), product.getDate(), product.getName(), product.getDescription(), product.getCurrentPrice(), product.isAllowOrder(), product.getCategory().getId(), product.getSeller()) > 0;
    }

    @Override
    public boolean update(Product product) {
        return jdbcTemplate.update("UPDATE Product SET  date = ?, name = ?, description = ?, currentPrice = ?, allowOrder = ?, categoryId = ?, sellerId = ? WHERE id = ?", product.getDate(), product.getName(), product.getDescription(), product.getCurrentPrice(), product.isAllowOrder(), product.getCategory().getId(), product.getSeller(), product.getId() ) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM Product Where id = ? ", id) > 0;
    }
    
    public List<Product> search(String keyword, Double minPrice, Double maxPrice) {
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
        return jdbcTemplate.query(sql, args.toArray(), MAPPER);
    }

}
