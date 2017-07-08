package org.smart4j.framework.ioc;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.core.ClassHelper;
import org.smart4j.framework.ioc.annotation.Component;
import org.smart4j.framework.mvc.Exception.InitializationException;
import org.smart4j.framework.mvc.annotation.Controller;
import org.smart4j.framework.mvc.annotation.Service;

import java.util.*;

/**
 * @author tf
 * @create 2017-06-27 17:59
 **/
public class BeanHelper {
    private static final Logger logger = LoggerFactory.getLogger(BeanHelper.class);

    private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();

    static {
        try {
            List<Class<?>> basePackageClassList = ClassHelper.getBasePackageClassList();
            List<Class<?>> beanClassList = new ArrayList<Class<?>>();
            if (CollectionUtils.isNotEmpty(basePackageClassList)) {
                for (int i = 0; i < basePackageClassList.size(); i++) {
                    Class<?> cls = basePackageClassList.get(i);
                    if (cls.isAnnotationPresent(Controller.class) || cls.isAnnotationPresent(Service.class)
                            || cls.isAnnotationPresent(Component.class)){
                        beanClassList.add(cls);
                        Object instance = cls.newInstance();
                        beanMap.put(cls, instance);
                    }
                }
            }
            //打印所有的bean
            printAllBeans(beanClassList);
        } catch (Exception e) {
            throw new InitializationException("初始化BeanHelper异常", e);
        }
    }

    private static void printAllBeans(List<Class<?>> beanClassList) {
        StringBuilder builder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(beanClassList)) {
            for (int i = 0; i < beanClassList.size(); i++) {
                Class<?> beanClass = beanClassList.get(i);
                builder.append(beanClass.getSimpleName());
                if (i < beanClassList.size() - 1) {
                    builder.append("、");
                }
            }
        }
        logger.debug("bean name: [{}]", builder.toString());
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
