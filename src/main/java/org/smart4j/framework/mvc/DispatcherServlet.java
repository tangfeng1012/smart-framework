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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(FrameworkConstant.ENCODING);

        String reqMethod = req.getMethod();
        String requestPath = WebUtil.getRequestUrl(req);
        logger.debug("[smart framework: {}, {}]", requestPath, reqMethod);
        //将"/"重定向到首页
        if (requestPath.equals("/")) {
            WebUtil.RedirectRequest(FrameworkConstant.HOME_PAGE_PATH, req, resp);
        }
        if (requestPath.endsWith("/")) {
            requestPath = requestPath.substring(0, requestPath.length() - 1);
        }

        Handler handler = handlerMapping.getHandler(requestPath, reqMethod);

        super.service(req, resp);
    }
}
