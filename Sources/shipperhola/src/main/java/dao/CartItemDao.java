/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.List;
import javax.sql.DataSource;
import model.Account;
import model.CartItem;
import model.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class CartItemDao extends BaseDao {

    private static final RowMapper<CartItem> MAPPER = (rs, rowNum) -> new CartItem(
            new Account(rs.getInt("accountId")),
            new Product(rs.getInt("productId")),
            rs.getInt("quantity"),
            rs.getString("note")
    );

    public CartItemDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<CartItem> getByAccount(int accountId) throws DataAccessException {
        return jdbcTemplate.query("SELECT * FROM CartItem WHERE accountId = ?", new Object[]{accountId}, MAPPER);
    }

    public CartItem getByAccountAndProduct(int accountId, int productId) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM CartItem WHERE accountId = ? AND productId = ?", new Object[]{accountId, productId}, MAPPER);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    public boolean add(CartItem cartItem) throws DataAccessException {
        return jdbcTemplate.update("INSERT INTO CartItem(accountId, productId, quantity, note) VALUES(?, ?, ?, ?)", cartItem.getAccount().getId(), cartItem.getProduct().getId(), cartItem.getQuantity(), cartItem.getNote()) > 0;
    }

    public boolean update(CartItem cartItem) throws DataAccessException {
        return jdbcTemplate.update("UPDATE CartItem SET quantity = ?, note = ? WHERE accountId = ? AND productId = ?", cartItem.getQuantity(), cartItem.getNote(), cartItem.getAccount().getId(), cartItem.getProduct().getId()) > 0;
    }

    public boolean deleteByAccountAndProduct(int accountId, int productId) throws DataAccessException {
        return jdbcTemplate.update("DELETE FROM CartItem WHERE accountId = ? AND productId = ?", accountId, productId) > 0;
    }

    public boolean deleteByAccount(int accountId) throws DataAccessException {
        return jdbcTemplate.update("DELETE FROM CartItem WHERE accountId = ?", accountId) > 0;
    }

}
