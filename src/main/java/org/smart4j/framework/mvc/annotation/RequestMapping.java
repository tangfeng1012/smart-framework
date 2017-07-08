package org.smart4j.framework.mvc.annotation;

import org.smart4j.framework.mvc.util.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tf
 * @create 2017-06-29 12:02
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String url();
    RequestMethod[] method();
}
