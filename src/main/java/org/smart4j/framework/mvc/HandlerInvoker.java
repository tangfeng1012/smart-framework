package org.smart4j.framework.mvc;

import org.smart4j.framework.mvc.bean.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

/**
 * @author tf
 * @create 2017-06-30 11:46
 **/
public interface HandlerInvoker {
    Object invokeHandler(HttpServletRequest request, Handler handler) throws InvocationTargetException, IllegalAccessException;
}
