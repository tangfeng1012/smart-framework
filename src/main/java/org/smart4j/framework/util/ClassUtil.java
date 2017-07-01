package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tf
 * @create 2017-06-27 17:04
 **/
public class ClassUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className) {
        return loadClass(className, true, getClassLoader());
    }

    public static Class<?> loadClass(String className, boolean isInit) {
        return loadClass(className, isInit, getClassLoader());
    }

    private static Class<?> loadClass(String className, boolean isInit, ClassLoader classLoader) {
        Class<?> cls = null;
        try {
            cls = Class.forName(className, isInit, classLoader);
        } catch (ClassNotFoundException e) {
            logger.error("class not found Exception", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    public static boolean isInt(Class<?> cls) {
        return cls == int.class || cls == Integer.class;
    }

    public static boolean isLong(Class<?> cls) {
        return cls == long.class || cls == Long.class;
    }

    public static boolean isDouble(Class<?> cls) {
        return cls == double.class || cls == Double.class;
    }

    public static boolean isString(Class cls) {
        return cls == String.class;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> loadClass = ClassUtil.loadClass("org.smart4j.framework.HelperLoader");
        System.out.println(loadClass.getName());
        Class<?> clzz = ClassUtil.class.getClassLoader().loadClass("org.smart4j.framework.HelperLoader");
        System.out.println(clzz.getName());
//        Class<?> clz = Class.forName("org.smart4j.framework.HelperLoader");
//        System.out.println(clz.getName());
    }
}
