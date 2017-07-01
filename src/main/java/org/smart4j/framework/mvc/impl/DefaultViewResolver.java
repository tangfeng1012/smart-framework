package org.smart4j.framework.mvc.impl;

import org.smart4j.framework.mvc.ViewResolver;
import org.smart4j.framework.mvc.bean.Result;
import org.smart4j.framework.mvc.bean.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                View vew = (View) invorkResult;


            } else {
                Result result = (Result) invorkResult;

            }
        }

    }
}
