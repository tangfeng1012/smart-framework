package org.smart4j.framework.mvc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
*  应用上下文
*  @author tf
*  @create 2017/6/27 15:02
**/
public class ApplicationContext {

    private static final ThreadLocal<ApplicationContext> applicationContextContainer = new ThreadLocal<ApplicationContext>();

    private HttpServletRequest request;
    private HttpServletResponse response;

    private ApplicationContext() {}

    public static void init(HttpServletRequest request, HttpServletResponse response) {
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.request = request;
        applicationContext.response = response;
        applicationContextContainer.set(applicationContext);
    }

    public static void destory() {
        applicationContextContainer.remove();
    }

    public static ApplicationContext getInstance() {
        return applicationContextContainer.get();
    }

    public static HttpServletRequest getRequest() {
        return getInstance().request;
    }

    public static HttpServletResponse getResponse() {
        return getInstance().response;
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletContext getServletContext() {
        return getRequest().getServletContext();
    }


}
