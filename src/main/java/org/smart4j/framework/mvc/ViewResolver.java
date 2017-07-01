package org.smart4j.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tf
 * @create 2017-07-01 17:04
 **/
public interface ViewResolver {

    void resolveView(HttpServletRequest request, HttpServletResponse response, Object invorkResult);
}
