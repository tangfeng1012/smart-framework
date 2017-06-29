package org.smart4j.framework.mvc.bean;

import org.smart4j.framework.mvc.util.RequestMethod;

/**
 * @author tf
 * @create 2017-06-29 14:36
 **/
public class Requester {
    private String[] requestUrls;
    private RequestMethod[] requestMethods;

    public Requester(String[] requestUrls, RequestMethod[] requestMethods) {
        this.requestUrls = requestUrls;
        this.requestMethods = requestMethods;
    }

    public String[] getRequestUrls() {
        return requestUrls;
    }

    public void setRequestUrls(String[] requestUrls) {
        this.requestUrls = requestUrls;
    }

    public RequestMethod[] getRequestMethods() {
        return requestMethods;
    }

    public void setRequestMethods(RequestMethod[] requestMethods) {
        this.requestMethods = requestMethods;
    }
}
