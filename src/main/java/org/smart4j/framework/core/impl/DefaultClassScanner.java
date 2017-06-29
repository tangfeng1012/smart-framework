package org.smart4j.framework.core.impl;

import org.smart4j.framework.core.ClassScanner;
import org.smart4j.framework.core.impl.support.AnnotationClassTemplate;
import org.smart4j.framework.core.impl.support.ClassTemplate;
import org.smart4j.framework.core.impl.support.SuperClassTemplate;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author tf
 * @create 2017-06-27 18:43
 **/
public class DefaultClassScanner implements ClassScanner {

    @Override
    public List<Class<?>> getClassList(String packageName) {
        return new ClassTemplate(packageName) {
            @Override
            protected boolean checkAddClass(Class<?> clz) {
                String clzName = clz.getName();
                return clzName.startsWith(packageName);
            }
        }.getClassList();
    }

    @Override
    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return new SuperClassTemplate(packageName, superClass) {
            @Override
            protected boolean checkAddClass(Class<?> clz) {
                return superClass.isAssignableFrom(clz);
            }
        }.getClassList();
    }

    @Override
    public List<Class<?>> getClassListByAnnotation(String basePackage, Class<? extends Annotation> annotationClass) {

        return new AnnotationClassTemplate(basePackage, annotationClass) {
            @Override
            protected boolean checkAddClass(Class<?> clz) {
                return clz.isAnnotationPresent(annotationClass);
            }
        }.getClassList();
    }

    public static void main(String[] args) {
        /*ClassScanner scanner = new DefaultClassScanner();
        List<Class<?>> classList = scanner.getClassList("org.smart4j.framework.util");
        for (Class clz : classList) {
            System.out.println(clz.getName());
        }*/

        System.out.println(Runnable.class.isAssignableFrom(Runnable.class));

    }
}
