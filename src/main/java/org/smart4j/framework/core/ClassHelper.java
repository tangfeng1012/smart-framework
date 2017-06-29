package org.smart4j.framework.core;

import org.smart4j.framework.mvc.util.InstanceFactory;

import java.util.List;

/**
 * @author tf
 * @create 2017-06-27 18:36
 **/
public class ClassHelper {
    //获取基础包名
    private static final String BASE_PACKAGE = ConfigHelper.getString("smart.framework.app.base_package");

    //实例化类扫描器
    private static final ClassScanner classScanner = InstanceFactory.getClassScanner();

    //获取基础包下所有Class
    public static List<Class<?>> getBasePackageClassList() {
        return classScanner.getClassList(BASE_PACKAGE);
    }

    // 查找基础包下指定接口或者父类的实现类
    public static List<Class<?>> getClassListBySuper(Class<?> superClass) {
        return classScanner.getClassListBySuper(BASE_PACKAGE, superClass);
    }

    // 查找基础包下带有指定注解的Class
    public static List<Class<?>> getClassListByAnnotation(Class annotationClass) {
        return classScanner.getClassListByAnnotation(BASE_PACKAGE, annotationClass);
    }

}
