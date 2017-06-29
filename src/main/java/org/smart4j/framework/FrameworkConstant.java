package org.smart4j.framework;

import org.smart4j.framework.core.ConfigHelper;

/**
 * @author tf
 * @create 2017-06-27 16:44
 **/
public interface FrameworkConstant {
    String ENCODING = "UTF-8";

    String CONFIG_PROPS = "smart.properties";

    String HOME_PAGE_PATH = ConfigHelper.getString("smart.framework.app.homepage_path", "/index.html");

    String JSP_PATH = ConfigHelper.getString("smart.framework.app.jsp_path", "/WEB-INF/JSP");

    String RESOURCE_PATH = ConfigHelper.getString("smart.framework.app.resources_path", "/resources/");


}
