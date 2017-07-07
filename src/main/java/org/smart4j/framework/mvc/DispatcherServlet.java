package org.smart4j.framework.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.FrameworkConstant;
import org.smart4j.framework.mvc.bean.Handler;
import org.smart4j.framework.mvc.util.InstanceFactory;
import org.smart4j.framework.mvc.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 核心控制器
 *
 * @author tf
 * @create 2017-06-27 15:03
 **/
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet{

    private Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private HandlerMapping handlerMapping = InstanceFactory.getHandlerMapping();

    private HandlerInvoker handlerInvoker = InstanceFactory.getHandlerInvoker();

    private ViewResolver viewResolver = InstanceFactory.getViewResolver();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(FrameworkConstant.ENCODING);

        String reqMethod = request.getMethod();
        String requestPath = WebUtil.getRequestUrl(request);
        logger.debug("[smart framework: {}, {}]", requestPath, reqMethod);
        //将"/"重定向到首页
        if (requestPath.equals("/")) {
            WebUtil.redirectRequest(FrameworkConstant.HOME_PAGE_PATH, request, response);
        }
        if (requestPath.endsWith("/")) {
            requestPath = requestPath.substring(0, requestPath.length() - 1);
        }

        Handler handler = handlerMapping.getHandler(requestPath, reqMethod);
        //未找到Action，则跳转到404页面
        if (handler == null) {
            WebUtil.sendError(response, HttpServletResponse.SC_NOT_FOUND, "url和method没有匹配上");
            return;
        }

        ApplicationContext.init(request, response);
        try {
            // 调用Handler
            Object invorkResult = handlerInvoker.invokeHandler(request, handler);
            //视图解析
            viewResolver.resolveView(request, response, invorkResult);
        } catch (Exception e) {
            //异常解析
        } finally {
            ApplicationContext.destory();
        }
    }
}
