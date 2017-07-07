package org.smart4j.framework.mvc.bean;

import java.util.Map;

/**
 * @author tf
 * @create 2017-07-01 17:30
 **/
public class View {
    // 视图路径
    private String path;
    // 返回数据
    private Map<String, Object> data;

    public View(String path) {
        this.path = path;
    }

    public View(String path, Map<String, Object> data) {
        this.path = path;
        this.data = data;
    }

    public boolean isRedirect() {
        return this.path.startsWith("/");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
