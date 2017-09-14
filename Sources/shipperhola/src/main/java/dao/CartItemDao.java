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
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class CartItemDao extends AbstractGenericDao<CartItem, Integer> {

    private static final RowMapper<CartItem> MAPPER = (rs, rowNum) -> new CartItem(
            new Account(rs.getInt("accountId")),
            new Product(rs.getInt("productId")),
            rs.getInt("quantity"),
            rs.getString("note")
    );

    public CartItemDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<CartItem> getAll() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public List<CartItem> getbyAccountId(int accountId) throws DataAccessException {
        return jdbcTemplate.query("SELECT * FROM CartItem WHERE accountId = ?", new Object[]{accountId}, MAPPER);
    }

    @Override
    public CartItem getById(Integer id) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(CartItem cartItem) throws DataAccessException {
        return jdbcTemplate.update("INSERT INTO CartItem(accountId, productId, quantity, note) VALUES(?, ?, ?, ?)", cartItem.getAccount().getId(), cartItem.getProduct().getId(), cartItem.getQuantity(), cartItem.getNote()) > 0;
    }

    @Override
    public boolean update(CartItem cartItem) throws DataAccessException {
        return jdbcTemplate.update("UPDATE CartItem SET quantity = ?, note  = ? WHERE accountId = ? AND productId = ?", cartItem.getQuantity(), cartItem.getNote(), cartItem.getAccount().getId(), cartItem.getProduct().getId()) > 0;
    }

    @Override
    public boolean delete(Integer id) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public boolean deleteByAccountAndProduct(int accountId, int productId) throws DataAccessException {
        return jdbcTemplate.update("DELETE FROM CartItem WHERE accountId = ? AND productId = ?", accountId, productId) > 0;
    }

}
