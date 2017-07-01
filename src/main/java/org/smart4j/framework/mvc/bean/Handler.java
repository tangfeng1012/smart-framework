package org.smart4j.framework.mvc.bean;

import java.lang.reflect.Method;
import java.util.regex.Matcher;

/**
 * @author tf
 * @create 2017-06-29 14:39
 **/
public class Handler {
    private Class<?> actionClass;
    private Method actionMethod;
    private Matcher requestMatcher;

    public Handler(Class<?> actionClass, Method actionMethod) {
        this.actionClass = actionClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getActionClass() {
        return actionClass;
    }

    public void setActionClass(Class<?> actionClass) {
        this.actionClass = actionClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public void setActionMethod(Method actionMethod) {
        this.actionMethod = actionMethod;
    }

    public Matcher getRequestMatcher() {
        return requestMatcher;
    }
    public void setRequestMatcher(Matcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }
}
