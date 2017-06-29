package org.smart4j.framework.mvc.util;

import org.smart4j.framework.mvc.bean.Requester;
import org.smart4j.framework.util.ClassUtil;

/**
 * @author tf
 * @create 2017-06-29 15:16
 **/
public class ObjectUtils {

    public static <T> T newInstance(String className) {
        T t ;
        try {
            Class<?> loadClass = ClassUtil.loadClass(className);
            t = (T)loadClass.newInstance();
        }  catch (Exception e) {
            throw new RuntimeException("实例化" + className + "失败", e);
        }
        return t;
    }

}
