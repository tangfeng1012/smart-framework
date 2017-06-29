package org.smart4j.framework.mvc.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tf
 * @create 2017-06-29 16:30
 **/
public class WebUtil {

    public static String getRequestUrl(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        return servletPath + pathInfo;
    }

    public static void RedirectRequest(String pagePath, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + pagePath);
        } catch (IOException e) {
            throw new RuntimeException("重定向出错", e);
        }
    }
}
