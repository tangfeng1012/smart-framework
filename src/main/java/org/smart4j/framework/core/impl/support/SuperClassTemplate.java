package org.smart4j.framework.core.impl.support;

/**
 * @author tf
 * @create 2017-06-29 10:32
 **/
public abstract class SuperClassTemplate extends ClassTemplate {
    protected final Class<?> superClass;

    public SuperClassTemplate(String packageName, Class<?> cls) {
        super(packageName);
        superClass = cls;
    }
}
