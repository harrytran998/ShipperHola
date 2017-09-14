/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
    public List<Account> getAll() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Account getById(Integer id) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Account WHERE id = ?", new Object[]{id}, MAPPER);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    public Account getByUsername(String username) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Account WHERE username = ?", new Object[]{username}, MAPPER);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    @Override
    public boolean add(Account account) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", account.getUsername());
        params.put("password", account.getPassword());
        params.put("fullName", account.getFullName());
        params.put("gender", account.isGender());
        params.put("dateOfBirth", account.getDateOfBirth());
        params.put("email", account.getEmail());
        params.put("phoneNumber", account.getPhoneNumber());
        params.put("address", account.getAddress());
        params.put("facebookId", account.getFacebookId());
        params.put("role", account.getRole());

        Number id = simpleJdbcInsert.withTableName("Account").usingGeneratedKeyColumns("id").executeAndReturnKey(params);
        if (id != null) {
            account.setId(id.intValue());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Account account) throws DataAccessException {
        return jdbcTemplate.update("UPDATE Account SET username = ?, password= ?, fullName=  ?, gender= ?, dateOfBirth= ?, email= ? , phoneNumber= ?, address= ?, facebookId= ?, role= ? WHERE id= ?", account.getUsername(), account.getPassword(), account.getFullName(), account.isGender(), account.getDateOfBirth(), account.getEmail(), account.getPhoneNumber(), account.getAddress(), account.getFacebookId(), account.getRole()) > 0;
    }

    @Override
    public boolean delete(Integer id) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

}
