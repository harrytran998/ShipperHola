/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.List;
import javax.sql.DataSource;
import model.SearchKeyword;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Le Cao Nguyen
 */
public class SearchKeywordDao extends BaseDao {

    private static final RowMapper<SearchKeyword> MAPPER = (rs, rowNum) -> new SearchKeyword(
            rs.getString("keyword"),
            rs.getLong("searchCount")
    );

    public SearchKeywordDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<SearchKeyword> getAll() throws DataAccessException {
        return jdbcTemplate.query("SELECT * FROM SearchKeyword ORDER BY keyword ASC", MAPPER);
    }

    public List<SearchKeyword> getTopKeywords(int count) throws DataAccessException {
        return jdbcTemplate.query("SELECT TOP(?) * FROM SearchKeyword ORDER BY searchCount DESC", new Object[]{count}, MAPPER);
    }

    public SearchKeyword getByKeyword(String keyword) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM SearchKeyword WHERE keyword = ?", new Object[]{keyword}, MAPPER);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    public boolean add(SearchKeyword searchKeyword) throws DataAccessException {
        return jdbcTemplate.update("INSERT INTO SearchKeyword(keyword, searchCount) VALUES (?, ?)", searchKeyword.getKeyword(), searchKeyword.getSearchCount()) > 0;
    }

    public boolean increaseCount(String keyword) {
        return jdbcTemplate.update("UPDATE SearchKeyword SET searchCount = searchCount + 1 WHERE keyword = ?", keyword) > 0;
    }
}
