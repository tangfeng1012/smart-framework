package org.smart4j.framework.mvc.util;

import java.util.Map;

/**
 * @author tf
 * @create 2017-06-29 12:22
 **/
public enum RequestMethod {
    GET("GET", 0), POST("POST", 1), PUT("PUT", 2), DELETE("DELETE", 3);

    private String name;
    private Integer value;

    RequestMethod(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getRequestMethod() {
        return this.name;
    }
}
