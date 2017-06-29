package org.smart4j.framework.core;

import org.smart4j.framework.FrameworkConstant;
import org.smart4j.framework.util.PropsUtil;

import java.util.Properties;

/**
 * @author tf
 * @create 2017-06-27 16:49
 **/
public class ConfigHelper {

    private static final Properties configProps = PropsUtil.loadProps(FrameworkConstant.CONFIG_PROPS);

    public static String getString(String key) {
        return PropsUtil.getString(configProps, key);
    }

    public static String getString(String key, String defaultValue) {
        return PropsUtil.getString(configProps, key, defaultValue);
    }
}
