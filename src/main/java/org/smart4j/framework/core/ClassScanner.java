package org.smart4j.framework.core;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author tf
 * @create 2017-06-27 18:42
 **/
public interface ClassScanner {
    //获取指定包名中的所有类
    public List<Class<?>> getClassList(String packageName);

    //
    List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass);

    List<Class<?>> getClassListByAnnotation(String basePackage, Class<? extends Annotation> annotationClass);
}
