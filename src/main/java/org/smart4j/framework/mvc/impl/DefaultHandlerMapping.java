package org.smart4j.framework.mvc.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.smart4j.framework.mvc.ActionHelper;
import org.smart4j.framework.mvc.HandlerMapping;
import org.smart4j.framework.mvc.bean.Handler;
import org.smart4j.framework.mvc.bean.Requester;
import org.smart4j.framework.mvc.util.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tf
 * @create 2017-06-29 15:57
 **/

public class DefaultHandlerMapping implements HandlerMapping {

    @Override
    public Handler getHandler(String requestPath, String requestMethod) {
        Map<Requester, Handler> actionMap = ActionHelper.getActionMap();
        Handler handler = null;
        if (MapUtils.isNotEmpty(actionMap)) {
            for (Map.Entry<Requester, Handler> entry : actionMap.entrySet()) {
                Requester requester = entry.getKey();

                String requestUrl = requester.getRequestUrls();
                RequestMethod[] reqMethods = requester.getRequestMethods();

                Matcher matcher = Pattern.compile(requestUrl).matcher(requestPath);
                // 判断请求路径和请求方法是否同时匹配
                if (matcher.matches() && isMatchRequestMethod(reqMethods, requestMethod)) {
                    handler = entry.getValue();
                    if (handler != null) {
                        handler.setRequestMatcher(matcher);
                    }
                    break;
                }
            }
        }
        return handler;
    }

    private boolean isMatchRequestMethod(RequestMethod[] reqMehods, String requestMethod) {
        boolean isMatch = false;
        List<String> reqMethodList = getRequestMethods(reqMehods);
        for(String reqMethod : reqMethodList) {
            if (reqMethod.equalsIgnoreCase(requestMethod)) {
                isMatch = true;
                break;
            }
        }
        return isMatch;
    }

    private List<String> getRequestMethods(RequestMethod[] reqMethods) {
        List<String> methodList = new ArrayList<String>();
        if (!ArrayUtils.isEmpty(reqMethods)) {
            for (int i = 0; i < reqMethods.length; i++) {
                String requestMethod = reqMethods[i].getRequestMethod();
                methodList.add(requestMethod);
            }
        }
        return methodList;
    }

    public static void main(String[] args) {
        String requestPath = "/user/getUserInfo/{uid}/{aid}";
//        System.out.println(requestPath.matches(".+\\{\\w+}.*"));
        requestPath = requestPath.replaceAll("\\{\\w+\\}", "(\\\\w+\\)");
        System.out.println(requestPath);

//        requestPath = replaceAll(requestPath, "\\{\\w+\\}", "(\\\\w+)");
//        System.out.println(requestPath);

        String requestUrl = "/user/getUserInfo/333/xxxx";
        Matcher matcher = Pattern.compile(requestPath).matcher(requestUrl);
        System.out.println(matcher.matches());

        for (int i = 1; i <= matcher.groupCount(); i++) {
            System.out.println("matcher counter :" + matcher.groupCount());
            String group = matcher.group(i);
            System.out.println(group + "==========");
        }
    }

    private static String replaceAll(String str, String regex, String replacement) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, replacement);
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
