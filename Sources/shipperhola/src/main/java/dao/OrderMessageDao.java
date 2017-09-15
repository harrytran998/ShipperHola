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
import model.OrderMessage;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author PC
 */
public class OrderMessageDao extends BaseDao {

    private static final RowMapper<OrderMessage> MAPPER = (rs, rowNum) -> new OrderMessage(
            rs.getInt("id"),
            rs.getDate("date"),
            rs.getString("content"),
            new Account(rs.getInt("accountId")),
            new OrderMessage(rs.getInt("repliedMessageId")),
            new Order(rs.getInt("orderId"))
    );

    public OrderMessageDao(DataSource dataSource) {
        super(dataSource);
    }

    public OrderMessage getById(int id) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM OrderMessage WHERE id = ?", new Object[]{id}, MAPPER);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    public List<OrderMessage> getByOrder(int orderId) throws DataAccessException {
        return jdbcTemplate.query("SELECT * FROM OrderMessage WHERE orderId = ?", new Object[]{orderId}, MAPPER);
    }

    public boolean add(OrderMessage orderMessage) throws DataAccessException {
        Map<String, Object> params = new HashMap<>();
        params.put("date", orderMessage.getDate());
        params.put("content", orderMessage.getContent());
        params.put("accountId", orderMessage.getAccount().getId());
        params.put("repliedMessageId", orderMessage.getRepliedMessage().getId());
        params.put("orderId", orderMessage.getOrder().getId());
        Number id = simpleJdbcInsert.withTableName("OrderMessage").usingGeneratedKeyColumns("id").executeAndReturnKey(params);
        if (id != null) {
            orderMessage.setId(id.intValue());
            return true;
        } else {
            return false;
        }
    }

    public boolean update(OrderMessage orderMessage) throws DataAccessException {
        return jdbcTemplate.update("UPDATE OrderMessage SET date = ?, content = ?, accountId = ?, repliedMessageId = ?, orderId = ? WHERE id = ?", orderMessage.getDate(), orderMessage.getContent(), orderMessage.getAccount().getId(), orderMessage.getRepliedMessage(), orderMessage.getOrder().getId(), orderMessage.getId()) > 0;
    }

    public boolean delete(int id) throws DataAccessException {
        return jdbcTemplate.update("DELETE FROM OrderMessage WHERE id = ?", id) > 0;
    }
}
