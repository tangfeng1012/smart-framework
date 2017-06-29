package org.smart4j.framework;

import org.smart4j.framework.ioc.BeanHelper;
import org.smart4j.framework.ioc.IocHelper;
import org.smart4j.framework.mvc.ActionHelper;
import org.smart4j.framework.util.ClassUtil;

/**
 * @author tf
 * @create 2017-06-27 17:55
 **/
public class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
            ActionHelper.class,
            BeanHelper.class,
            IocHelper.class
        };

        for(Class<?> clz : classList) {
            ClassUtil.loadClass(clz.getName());
        }
    }
}
