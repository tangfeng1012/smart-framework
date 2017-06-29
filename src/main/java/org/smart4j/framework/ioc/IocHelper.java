package org.smart4j.framework.ioc;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.smart4j.framework.core.ClassHelper;
import org.smart4j.framework.ioc.annotation.Impl;
import org.smart4j.framework.ioc.annotation.Inject;
import org.smart4j.framework.mvc.Exception.InitializationException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author tf
 * @create 2017-06-27 18:00
 **/
public class IocHelper {
    static {
        try {
            Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
            if (MapUtils.isNotEmpty(beanMap)) {
                for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
                    Class<?> clz = entry.getKey();
                    Object beanInstance = entry.getValue();

                    Field[] declaredFields = clz.getDeclaredFields();
                    if (!ArrayUtils.isEmpty(declaredFields)) {
                        for (Field field : declaredFields) {
                            if (field.isAnnotationPresent(Inject.class)) {
                                // 获取bean字段对应的类型
                                Class<?> interfaceType = field.getType();
                                //根据bean字段的类型找到具体的实现类类型
                                Class<?> implementsClass = findImplementsClass(interfaceType);
                                if (implementsClass != null) {
                                    Object implementsInstance = beanMap.get(implementsClass);
                                    if (implementsInstance != null) {
                                        // 设置字段的初始值
                                        field.set(beanInstance, implementsInstance);
                                    } else {
                                        throw new InitializationException("注入失败，基础类名为: " + clz + ", 字段类型为: " + interfaceType.getSimpleName() + "字段名为:" + field.getName());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new InitializationException("IOC 容器初始化失败", e);
        }
    }

    private static Class<?> findImplementsClass(Class<?> interfaceType) {
        Class<?> implementClass = interfaceType;
        // 判断接口上是否有Impl注解
        if (interfaceType.isAnnotationPresent(Impl.class)) {
            implementClass = interfaceType.getAnnotation(Impl.class).value();
        } else {
            // 当一个接口有多个实现类时，默认取第一个
            List<Class<?>> superClassList = ClassHelper.getClassListBySuper(implementClass);
            if (CollectionUtils.isNotEmpty(superClassList)) {
                implementClass = superClassList.get(0);
            }
        }
        return implementClass;
    }
}
