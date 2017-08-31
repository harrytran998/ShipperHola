/*
 * Copyright © 2017 XVideos Team
 */
package dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import model.Category;
import model.ShippingAddress;

import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class ShippingAddressDao  extends AbstractGenericDao<ShippingAddress, Integer>{
    private static final RowMapper<ShippingAddress> MAPPER = (rs, rowNum) -> new ShippingAddress(
            rs.getInt("id"),
            rs.getString("address")      
    );
    
    public ShippingAddressDao(DataSource dataSource) {
        super(dataSource);
    }
    
    @Override
    public List<ShippingAddress> getAll() {
         return jdbcTemplate.query("SELECT * FROM ShippingAddress", MAPPER);
    }

    @Override
    public ShippingAddress getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ShippingAddress WHERE id = ?", new Object[]{id} ,MAPPER);
    }

    @Override
    public boolean add(ShippingAddress shippingAddress) {
        Map<String, Object> params = new HashMap<>();
        params.put("address", shippingAddress.getAddress());
        Number id = simpleJdbcInsert.withTableName("Category").usingGeneratedKeyColumns("id").executeAndReturnKey(params);
        if (id != null) {
            shippingAddress.setId(id.intValue());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(ShippingAddress shippingAddress) {
        return jdbcTemplate.update("UPDATE ShippingAddress SET address = ? WHERE id = ?", shippingAddress.getAddress(), shippingAddress.getId()) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM ShippingAddress WHERE id = ?", id) >0;
    }
    
    
    
}
