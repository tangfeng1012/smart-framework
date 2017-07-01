package org.smart4j.framework.mvc.annotation;

import org.smart4j.framework.mvc.util.RequestMethod;

/**
 * @author tf
 * @create 2017-06-29 12:02
 **/
public @interface RequestMapping {
    String url();
    RequestMethod[] method();
}
