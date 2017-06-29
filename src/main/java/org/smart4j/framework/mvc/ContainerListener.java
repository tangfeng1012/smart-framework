package org.smart4j.framework.mvc;

import org.apache.commons.lang.StringUtils;
import org.smart4j.framework.FrameworkConstant;
import org.smart4j.framework.HelperLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

/**
 *  容器监听器
 * @author tf
 * @create 2017-06-27 15:04
 **/
@WebListener
public class ContainerListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        //初始化相关Helper
        HelperLoader.init();

        //添加servlet映射
        addServletMapping(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void addServletMapping(ServletContext servletContext) {
        //用DefaultServlet映射所有静态资源
        registerDefaultServlet(servletContext);
        //用JspServlet映射所有jsp请求
        registerJspServlet(servletContext);
    }

    private void registerJspServlet(ServletContext servletContext) {
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        String jspPath = FrameworkConstant.JSP_PATH;
        if (StringUtils.isNotEmpty(jspPath)) {
            jspServlet.addMapping(jspPath);
        }
    }

    private void registerDefaultServlet(ServletContext servletContext) {
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping("/index.html");
        defaultServlet.addMapping("/favicon.ico");
        String resourcePath = FrameworkConstant.RESOURCE_PATH;
        if (StringUtils.isNotEmpty(resourcePath)) {
            defaultServlet.addMapping(resourcePath);
        }
    }

}
