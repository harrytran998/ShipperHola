/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import model.ShippingAddress;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class ShippingAddressDao extends BaseDao {

    private static final RowMapper<ShippingAddress> MAPPER = (rs, rowNum) -> new ShippingAddress(
            rs.getInt("id"),
            rs.getString("address")
    );

    public ShippingAddressDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<ShippingAddress> getAll() throws DataAccessException {
        return jdbcTemplate.query("SELECT * FROM ShippingAddress", MAPPER);
    }

    public ShippingAddress getById(int id) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ShippingAddress WHERE id = ?", new Object[]{id}, MAPPER);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    public List<ShippingAddress> getByProduct(int productId) {
        return jdbcTemplate.query("SELECT SA.id, SA.address FROM Product_ShippingAddress PSA INNER JOIN ShippingAddress SA ON PSA.shippingAddressId = SA.id WHERE PSA.productId = ?", new Object[]{productId}, MAPPER);
    }

    public boolean add(ShippingAddress shippingAddress) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("address", shippingAddress.getAddress());
        Number id = simpleJdbcInsert.withTableName("ShippingAddress").usingGeneratedKeyColumns("id").executeAndReturnKey(params);
        if (id != null) {
            shippingAddress.setId(id.intValue());
            return true;
        } else {
            return false;
        }
    }

    public boolean update(ShippingAddress shippingAddress) throws DataAccessException {
        return jdbcTemplate.update("UPDATE ShippingAddress SET address = ? WHERE id = ?", shippingAddress.getAddress(), shippingAddress.getId()) > 0;
    }

}
