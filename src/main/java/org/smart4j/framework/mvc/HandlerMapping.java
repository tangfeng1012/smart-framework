package org.smart4j.framework.mvc;

import org.smart4j.framework.mvc.bean.Handler;

/**
 * @author tf
 * @create 2017-06-29 15:55
 **/
public interface HandlerMapping {

    Handler getHandler(String requestPath, String requestMethod);

}
