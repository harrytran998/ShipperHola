/*
 * Copyright © 2017 XVideos Team
 */
package util.view;

import java.util.Map;
import spark.Request;

/**
 * Interface for view manager.
 *
 * @author Le Cao Nguyen
 */
public interface IViewManager {

    /**
     * Render a view with model.
     *
     * @param model The model.
     * @param viewName The view name.
     * @return The rendering result.
     */
    public String render(Map<String, Object> model, String viewName);

    /**
     * Render a view, using request object as model. The model used for
     * rendering process will be constructed from the attributes embedded in the
     * request object.
     *
     * @param request The request object.
     * @param viewName The view name.
     * @return The rendering result.
     */
    public String renderForRequest(Request request, String viewName);

    /**
     * Get the relative file path of the view.
     *
     * @param viewName The view name.
     * @return The relative file path of the view.
     */
    public String getViewFilePath(String viewName);

    /**
     * Check if a view exist.
     *
     * @param viewName The view name.
     * @return True if the corresponding view file exists, otherwise false.
     */
    public boolean isViewFileExist(String viewName);
}
