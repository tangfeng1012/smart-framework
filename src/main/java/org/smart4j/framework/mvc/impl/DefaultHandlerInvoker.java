package org.smart4j.framework.mvc.impl;

import org.apache.commons.collections.MapUtils;
import org.smart4j.framework.ioc.BeanHelper;
import org.smart4j.framework.mvc.HandlerInvoker;
import org.smart4j.framework.mvc.bean.Handler;
import org.smart4j.framework.mvc.bean.Params;
import org.smart4j.framework.mvc.util.CastUtil;
import org.smart4j.framework.mvc.util.WebUtil;
import org.smart4j.framework.util.ClassUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * @author tf
 * @create 2017-06-30 11:51
 **/
public class DefaultHandlerInvoker implements HandlerInvoker {

    @Override
    public Object invokeHandler(HttpServletRequest request, Handler handler) throws InvocationTargetException, IllegalAccessException {
        // 获取Action相关信息
        Class<?> actionClass = handler.getActionClass();
        Method actionMethod = handler.getActionMethod();
        //从BeanHelper中获取Action实例
        Object actionInstance = BeanHelper.getBean(actionClass);
        // 获取参数列表
        List<Object> paramList = createActionMethodParamsList(request, handler);
        // 执行Action中的方法
        Object invorkResult = invorkActionMethod(actionMethod, actionInstance, paramList);
        // 返回结果
        return invorkResult;
    }

    private Object invorkActionMethod(Method actionMethod, Object actionInstance, List<Object> paramList) throws InvocationTargetException, IllegalAccessException {
        actionMethod.setAccessible(true);
        Object actionInvorkResult = actionMethod.invoke(actionInstance, paramList.toArray());
        return actionInvorkResult;
    }

    private List<Object> createActionMethodParamsList(HttpServletRequest request, Handler handler) {
        List<Object> paramList = new ArrayList<Object>();
        //获取参数类型
        Class<?>[] parameterTypes = handler.getActionMethod().getParameterTypes();
        // 获取path下的参数列表
        paramList.addAll(createPathParamList(parameterTypes, handler));
        // 获取普通请求参数列标(包括 request payload、query String、Form Data) form表单下的参数列表
        Map<String, Object> requestParamMap = WebUtil.getRequestParamMap(request);
        if (MapUtils.isNotEmpty(requestParamMap)) {
            paramList.add(new Params(requestParamMap));
        }
        return paramList;
    }

    private List<Object> createPathParamList(Class<?>[] parameterTypes, Handler handler) {
        List<Object> paramList = new ArrayList<Object>();
        Matcher requestMatcher = handler.getRequestMatcher();
        for (int i = 1; i <= requestMatcher.groupCount(); i++) {
            // 获取请求参数
            String paramValue = requestMatcher.group(i);
            // 参数类型
            Class<?> parameterType = parameterTypes[i - 1];
            // 参数转换（支持四种类型：int/Integer、long/Long、double/Double、String）
            if (ClassUtil.isInt(parameterType)) {
                paramList.add(CastUtil.castInteger(paramValue));
            } else if (ClassUtil.isDouble(parameterType)) {
                paramList.add(CastUtil.castDouble(paramValue));
            } else if (ClassUtil.isLong(parameterType)) {
                paramList.add(CastUtil.castLong(paramValue));
            } else if (ClassUtil.isString(parameterType)) {
                paramList.add((paramValue));
            }
        }
        return paramList;
    }
}
