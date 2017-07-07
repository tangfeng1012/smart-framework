package org.smart4j.framework.ioc;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(BeanHelper.class);

    private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();

    static {
        try {
            StringBuilder builder = new StringBuilder();
            List<Class<?>> basePackageClassList = ClassHelper.getBasePackageClassList();
            if (CollectionUtils.isNotEmpty(basePackageClassList)) {
                for (Class<?> cls : basePackageClassList) {
                    if (cls.isAnnotationPresent(Controller.class) || cls.isAnnotationPresent(Service.class)
                            || cls.isAnnotationPresent(Component.class)){
                        Object instance = cls.newInstance();
                        beanMap.put(cls, instance);
                        builder.append(cls.getSimpleName() + " ");
                    }
                }
            }
            System.out.println(builder.toString());
            logger.info("bean name: " + builder.toString());
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
