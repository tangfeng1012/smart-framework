package org.smart4j.framework.mvc.impl;

import org.apache.commons.collections.MapUtils;
import org.smart4j.framework.mvc.ActionHelper;
import org.smart4j.framework.mvc.HandlerMapping;
import org.smart4j.framework.mvc.bean.Handler;
import org.smart4j.framework.mvc.bean.Requester;
import org.smart4j.framework.mvc.util.RequestMethod;

import java.util.Map;

/**
 * @author tf
 * @create 2017-06-29 15:57
 **/

public class DefaultHandlerMapping implements HandlerMapping {

    @Override
    public Handler getHandler(String requestPath, String requestMethod) {
        Map<Requester, Handler> actionMap = ActionHelper.getActionMap();
        if (MapUtils.isNotEmpty(actionMap)) {
            for (Map.Entry<Requester, Handler> entry : actionMap.entrySet()) {
                Requester requester = entry.getKey();
                Handler handler = entry.getValue();

                String[] requestUrls = requester.getRequestUrls();
                RequestMethod[] requestMethods = requester.getRequestMethods();

                
            }
        }

        return null;
    }
}
