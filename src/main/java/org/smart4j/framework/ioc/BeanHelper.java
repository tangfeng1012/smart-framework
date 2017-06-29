package org.smart4j.framework.ioc;

import org.apache.commons.collections.CollectionUtils;
import org.smart4j.framework.core.ClassHelper;
import org.smart4j.framework.ioc.annotation.Component;
import org.smart4j.framework.mvc.Exception.InitializationException;
import org.smart4j.framework.mvc.annotation.Controller;
import org.smart4j.framework.mvc.annotation.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tf
 * @create 2017-06-27 17:59
 **/
public class BeanHelper {
    private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();

    static {
        try {
            List<Class<?>> basePackageClassList = ClassHelper.getBasePackageClassList();
            if (CollectionUtils.isNotEmpty(basePackageClassList)) {
                for (Class<?> cls : basePackageClassList) {
                    if (cls.isAnnotationPresent(Controller.class) || cls.isAnnotationPresent(Service.class)
                            || cls.isAnnotationPresent(Component.class)){
                        Object instance = cls.newInstance();
                        beanMap.put(cls, instance);
                    }
                }
            }
        } catch (Exception e) {
            throw new InitializationException("初始化BeanHelper异常", e);
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    /**
     * 获取bean 实例
     * @param cls
     * @return
     */
    public static <T> T getBean(Class<T> cls) {
        if (!beanMap.containsKey(cls)) {
            throw new RuntimeException("无法获取bean 实例");
        }
        return (T)beanMap.get(cls);
    }
}
