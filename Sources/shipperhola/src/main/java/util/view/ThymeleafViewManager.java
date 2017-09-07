/*
 * Copyright © 2017 XVideos Team
 */
package util.view;

import java.util.Map;
import java.util.stream.Collectors;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import spark.Request;

/**
 * A view manager using Thymeleaf as the underlying template engine.
 * @author Le Cao Nguyen
 */
public class ThymeleafViewManager implements IViewManager {
    private static final String DEFAULT_PREFIX = "/templates/";
    private static final String DEFAULT_SUFFIX = ".html";
    private static final TemplateMode DEFAULT_TEMPLATE_MODE = TemplateMode.HTML;
    private static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
    private static final long DEFAULT_CACHE_TTL_MS = 3600000L;
    private static final boolean DEFAULT_CACHEABLE = true;
    
    private final ClassLoader classLoader;
    private final ClassLoaderTemplateResolver templateResolver;
    private final TemplateEngine templateEngine;

    public ThymeleafViewManager() {
        this(DEFAULT_PREFIX, DEFAULT_SUFFIX, DEFAULT_CACHEABLE);
    }
    
    public ThymeleafViewManager(String prefix, String suffix, boolean cacheable) {
        this.classLoader = getClass().getClassLoader();
        this.templateResolver = createDefaultTemplateResolver(this.classLoader, prefix, suffix, cacheable);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(this.templateResolver);
    }
    
    private static ClassLoaderTemplateResolver createDefaultTemplateResolver(ClassLoader classLoader, String prefix, String suffix, Boolean cacheable) {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver(classLoader);
        templateResolver.setTemplateMode(DEFAULT_TEMPLATE_MODE);
        templateResolver.setCacheTTLMs(DEFAULT_CACHE_TTL_MS);
        templateResolver.setCharacterEncoding(DEFAULT_CHARACTER_ENCODING);
        templateResolver.setPrefix(prefix != null ? prefix : DEFAULT_PREFIX);
        templateResolver.setSuffix(suffix != null ? suffix : DEFAULT_SUFFIX);
        templateResolver.setCacheable(cacheable != null ? cacheable : DEFAULT_CACHEABLE);
        return templateResolver;
    }

    @Override
    public String render(Map<String, Object> model, String viewName) {
        Context context = new Context();
        context.setVariables(model);
        return templateEngine.process(viewName, context);
    }

    @Override
    public String getViewFilePath(String viewName) {
        return templateResolver.getPrefix() + viewName + templateResolver.getSuffix();
    }

    @Override
    public boolean isViewFileExist(String viewName) {
        return classLoader.getResource(getViewFilePath(viewName)) != null;
    }

    @Override
    public String renderForRequest(Request request, String viewName) {
        Map<String, Object> model = request.attributes().stream().collect(Collectors.toMap(attr -> attr, attr -> request.attribute(attr)));
        return render(model, viewName);
    }
}
