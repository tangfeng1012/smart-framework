package org.smart4j.framework.mvc.bean;

import java.util.Map;

/**
 * @author tf
 * @create 2017-07-01 16:52
 **/
public class Params {

    private Map<String, Object> paramMap;

    public Params(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
}
