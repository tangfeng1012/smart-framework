package org.smart4j.framework.mvc.util;

import org.apache.commons.lang.StringUtils;
import org.smart4j.framework.core.ClassScanner;
import org.smart4j.framework.core.ConfigHelper;
import org.smart4j.framework.core.impl.DefaultClassScanner;
import org.smart4j.framework.mvc.HandlerMapping;
import org.smart4j.framework.mvc.impl.DefaultHandlerMapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tf
 * @create 2017-06-29 15:05
 **/
public class InstanceFactory {

    private static final Map<String, Object> cacheMap = new ConcurrentHashMap<String, Object>();

    private static final String CLASS_SCANNER = "smart.framework.custom.class_scanner";

    private static final String HANDLER_MAPPING = "smart.framework.custom.handler_mapping";

    public static ClassScanner getClassScanner() {
        return getInstance(CLASS_SCANNER, DefaultClassScanner.class);
    }

    public static HandlerMapping getHandlerMapping() {
        return getInstance(HANDLER_MAPPING, DefaultHandlerMapping.class);
    }

    private static <T> T getInstance(String cacheKey, Class<T> defaultImplClass) {
        if (cacheMap.containsKey(cacheKey)) {
            return (T)cacheMap.get(cacheKey);
        }

        String implementClassName = ConfigHelper.getString(cacheKey);
        if (StringUtils.isEmpty(implementClassName)) {
            implementClassName = defaultImplClass.getName();
        }
        T instance = ObjectUtils.newInstance(implementClassName);
        if (instance != null) {
            cacheMap.put(cacheKey, instance);
        }
        return instance;
    }
}
