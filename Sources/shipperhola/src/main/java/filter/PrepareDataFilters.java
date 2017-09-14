/*
 * Copyright © 2017 XVideos Team
 */
package filter;

import static app.Application.getCategoryDao;
import java.util.List;
import model.Category;
import spark.Filter;

/**
 *
 * @author Le Cao Nguyen
 */
public class PrepareDataFilters {
    public static final Filter EMBED_CATEGORIES_TO_REQUEST = (request, response) -> {
        List<Category> categories = getCategoryDao().getAll();
        request.attribute("categories", categories);
    };
}
