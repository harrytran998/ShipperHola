/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.List;
import javax.sql.DataSource;
import model.Account;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author PC
 */
public class AccountDao extends AbstractGenericDao<Account, Integer> {

    private static final RowMapper<Account> MAPPER = (rs, rowNum) -> new Account(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("fullname"),
            rs.getBoolean("gender"),
            rs.getDate("dateOfBirth"),
            rs.getString("email"),
            rs.getString("phoneNumber"),
            rs.getString("address"),
            rs.getString("facebookId"),
            rs.getString("role")
    );

    public AccountDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Account> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public Account getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Account WHERE id = ?", new Object[]{id}, MAPPER);
    }

    @Override
    public boolean add(Account account) {
        return jdbcTemplate.update("INSERT INTO Account(id,username,password,fullName,gender,dateOfBirth,email,phoneNumber,address,facebookId,role) Values (?,?,?,?,?,?,?,?,?,?,?)", account.getId(), account.getUsername(), account.getPassword(), account.getFullName(), account.isGender(), account.getDateOfBirth(), account.getEmail(), account.getPhoneNumber(), account.getAddress(), account.getFacebookId(), account.getRole()) > 0;
    }

    @Override
    public boolean update(Account account) {
        return jdbcTemplate.update("UPDATE Account SET username=?, password=?, fullName=?, gender=?, dateOfBirth=?, email=? , phoneNumber=?, address=?, facebookId=?, role=? WHERE id=?", account.getUsername(), account.getPassword(), account.getFullName(), account.isGender(), account.getDateOfBirth(), account.getEmail(), account.getPhoneNumber(), account.getAddress(), account.getFacebookId(), account.getRole()) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException(); 
    }

}
