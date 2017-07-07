package org.smart4j.framework.mvc.impl;

import org.apache.commons.collections.MapUtils;
import org.smart4j.framework.FrameworkConstant;
import org.smart4j.framework.mvc.ViewResolver;
import org.smart4j.framework.mvc.bean.Result;
import org.smart4j.framework.mvc.bean.View;
import org.smart4j.framework.mvc.util.JSONUtil;
import org.smart4j.framework.mvc.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author tf
 * @create 2017-07-01 17:19
 **/
public class DefaultViewResolver implements ViewResolver{
    @Override
    public void resolveView(HttpServletRequest request, HttpServletResponse response, Object invorkResult) {
        if (invorkResult != null) {
            // Action 返回类型：View、Result
            if (invorkResult instanceof View) {
                // 若为View，则考虑2中视图类型(重定向和转发)
                View view = (View) invorkResult;
                String viewPath = view.getPath();
                if (view.isRedirect()) {
                    WebUtil.sendRedirect(request, response, viewPath);
                } else {
                    String forwardPath = FrameworkConstant.JSP_PATH + viewPath;
                    Map<String, Object> viewData = view.getData();
                    if (MapUtils.isNotEmpty(viewData)) {
                        for (Map.Entry<String, Object> entry : viewData.entrySet()) {
                            request.setAttribute(entry.getKey(), entry.getValue());
                        }
                    }
                    WebUtil.forwardRequest(request, response, forwardPath);
                }
            } else {
                Result result = (Result) invorkResult;
                WebUtil.writeJSON(request, response, JSONUtil.toJSON(result));
            }
        }

    }
}
