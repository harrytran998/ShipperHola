/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import model.Account;
import model.Order;
import model.Product;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author PC
 */
public class OrderDao extends AbstractGenericDao<Order, Integer> {

    private static final RowMapper<Order> MAPPER = (rs, rowNum) -> new Order(
            rs.getInt("id"),
            rs.getDate("date"),
            rs.getInt("quantity"),
            rs.getDouble("price"),
            rs.getString("buyerAddress"),
            rs.getString("buyerPhoneNumber"),
            rs.getString("paymentMethod"),
            rs.getString("status"),
            new Account(rs.getInt("buyerId")),
            new Product(rs.getInt("productId"))
    );

    public OrderDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Order> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Order getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM [Order] WHERE id = ?", new Object[]{id}, MAPPER);
    }

    @Override
      public boolean add(Order order) {
        Map<String, Object> params = new HashMap<>();
        params.put("date", order.getDate());
        params.put("quantity", order.getQuantity());
        params.put("price", order.getPrice());
        params.put("buyerAddress", order.getBuyerAddress());
        params.put("buyerPhoneNumber", order.getBuyerPhoneNumber());
        params.put("paymentMethod", order.getPaymentMethod());
        params.put("status", order.getStatus());
        params.put("buyerId", order.getBuyer().getId());
        params.put("product", order.getProduct().getId());
      
        
        Number id = simpleJdbcInsert.withTableName("Order").usingGeneratedKeyColumns("id").executeAndReturnKey(params);
        if (id != null) {
            order.setId(id.intValue());
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean update(Order order) {
        return jdbcTemplate.update("UPDATE [Order] SET date = ?,quantity = ?,price = ?,buyerAddress = ?,buyerPhoneNumber = ?,paymentMethod = ?,status = ?,buyerId = ? ,productId = ?  WHERE id =? ", order.getDate(),order.getQuantity(),order.getPrice(), order.getBuyerAddress(),order.getBuyerPhoneNumber(),order.getPaymentMethod(),order.getStatus(), order.getBuyer().getId(),order.getProduct().getId())>0;
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

}
