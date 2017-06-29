package org.smart4j.framework.mvc;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.smart4j.framework.core.ClassHelper;
import org.smart4j.framework.mvc.Exception.InitializationException;
import org.smart4j.framework.mvc.annotation.Controller;
import org.smart4j.framework.mvc.annotation.RequestMapping;
import org.smart4j.framework.mvc.bean.Handler;
import org.smart4j.framework.mvc.bean.Requester;
import org.smart4j.framework.mvc.util.RequestMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tf
 * @create 2017-06-27 17:58
 **/
public class ActionHelper {
    private static final Map<Requester, Handler> actionMap = new HashMap<Requester, Handler>();
    static {
        try {
            List<Class<?>> actionClassList = ClassHelper.getClassListByAnnotation(Controller.class);
            Map<Requester, Handler> commonActionMap = new HashMap<Requester, Handler>();
            if (CollectionUtils.isNotEmpty(actionClassList)) {
                for (Class<?> actionClass : actionClassList) {
                    Method[] actionMethods = actionClass.getDeclaredMethods();
                    if (ArrayUtils.isEmpty(actionMethods)) {
                        for (Method actionMethod : actionMethods) {
                            HandleActionMethod(actionClass, actionMethod, commonActionMap);
                        }
                    }
                }
                actionMap.putAll(commonActionMap);
            }
        } catch (Exception e) {
            throw new InitializationException("Action初始化失败", e);
        }
    }

    private static void HandleActionMethod(Class<?> actionClass, Method actionMethod, Map<Requester, Handler> actionMap) {
        if (actionMethod.isAnnotationPresent(RequestMapping.class)) {
            String[] requestUrl = actionMethod.getAnnotation(RequestMapping.class).url();
            RequestMethod[] requestMethods = actionMethod.getAnnotation(RequestMapping.class).method();
            putActionMap(requestMethods, requestUrl, actionClass, actionMethod, actionMap);
        }
    }

    private static void putActionMap(RequestMethod[] requestMethods, String[] requestUrl, Class<?> actionClass, Method actionMethod, Map<Requester, Handler> actionMap) {
        if (actionMap != null) {
            actionMap.put(new Requester(requestUrl, requestMethods), new Handler(actionClass, actionMethod));
        }
    }
}
