package org.smart4j.framework.core.impl.support;

import java.lang.annotation.Annotation;

/**
 * @author tf
 * @create 2017-06-29 11:45
 **/
public abstract class AnnotationClassTemplate extends ClassTemplate {
    protected final Class<? extends Annotation> annotationClass;
    public AnnotationClassTemplate(String packageName, Class<? extends Annotation> annotationClass) {
        super(packageName);
        this.annotationClass = annotationClass;
    }
}
