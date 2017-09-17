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
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author PC
 */
public class AccountDao extends BaseDao {

    private static final RowMapper<Account> MAPPER = (rs, rowNum) -> new Account(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("fullName"),
            rs.getBoolean("gender"),
            rs.getDate("dateOfBirth"),
            rs.getString("email"),
            rs.getString("phoneNumber"),
            rs.getString("address"),
            rs.getString("facebookUrl"),
            rs.getString("role")
    );

    public AccountDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<Account> getAll() throws UnsupportedOperationException {
        return jdbcTemplate.query("SELECT * FROM Account", MAPPER);
    }

    public Account getById(int id) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Account WHERE id = ?", new Object[]{id}, MAPPER);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    public boolean isUsernameExist(String username) throws DataAccessException {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Account WHERE username = ?", new Object[]{username}, Long.class) > 0;
    }
    
    public Account getByUsername(String username) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Account WHERE username = ?", new Object[]{username}, MAPPER);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    public boolean add(Account account) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("username", account.getUsername());
        params.put("password", account.getPassword());
        params.put("fullName", account.getFullName());
        params.put("gender", account.isGender());
        params.put("dateOfBirth", account.getDateOfBirth());
        params.put("email", account.getEmail());
        params.put("phoneNumber", account.getPhoneNumber());
        params.put("address", account.getAddress());
        params.put("facebookUrl", account.getFacebookUrl());
        params.put("role", account.getRole());

        Number id = new SimpleJdbcInsert(jdbcTemplate).withTableName("Account").usingGeneratedKeyColumns("id").executeAndReturnKey(params);
        if (id != null) {
            account.setId(id.intValue());
            return true;
        } else {
            return false;
        }
    }

    public boolean update(Account account) throws DataAccessException {
        return jdbcTemplate.update("UPDATE Account SET username = ?, password = ?, fullName =  ?, gender = ?, dateOfBirth = ?, email = ?, phoneNumber = ?, address = ?, facebookUrl = ?, role = ? WHERE id = ?", account.getUsername(), account.getPassword(), account.getFullName(), account.isGender(), account.getDateOfBirth(), account.getEmail(), account.getPhoneNumber(), account.getAddress(), account.getFacebookUrl(), account.getRole()) > 0;
    }
}
