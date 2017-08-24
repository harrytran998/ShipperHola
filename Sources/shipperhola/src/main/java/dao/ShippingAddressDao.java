/*
 * Copyright © 2017 XVideos Team
 */
package dao;


import java.util.List;
import javax.sql.DataSource;
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
        return jdbcTemplate.update("INSERT INTO ShippingAddress(id, address) VALUES(?, ?)", shippingAddress.getId(),shippingAddress.getAddress()) > 0;
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
