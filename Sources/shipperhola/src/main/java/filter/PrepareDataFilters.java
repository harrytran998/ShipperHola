/*
 * Copyright © 2017 XVideos Team
 */
package filter;

import static app.Application.*;
import static spark.Spark.*;
import java.util.List;
import model.Account;
import model.Category;
import model.SearchKeyword;
import spark.Filter;
import util.LoginUtil;

/**
 *
 * @author Le Cao Nguyen
 */
public class PrepareDataFilters {
    public static final Filter EMBED_COMMON_DATA_INTO_REQUEST = (request, response) -> {
        Integer accountId = LoginUtil.getCurrentLogin(request);
        Account currentLoginAccount = accountId == null ? null : getAccountDao().getById(accountId);
        List<Category> categories = getCategoryDao().getAll();
        List<SearchKeyword> topSearchKeywords = getSearchKeywordDao().getTopKeywords(10);
        request.attribute("currentLoginAccount", currentLoginAccount);
        request.attribute("categories", categories);
        request.attribute("topSearchKeywords", topSearchKeywords);
    };
    
    public static void setupFilters() {
        before(EMBED_COMMON_DATA_INTO_REQUEST);
    }
}
