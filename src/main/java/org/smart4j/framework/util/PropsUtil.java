package org.smart4j.framework.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author tf
 * @create 2017-06-27 16:52
 **/
public class PropsUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String configPath) {
        Properties props = new Properties();
        InputStream inputStream = null;
        try {
            if (StringUtils.isEmpty(configPath)) {
                throw new IllegalArgumentException("properties config is empty !!!");
            }
            String suffix = ".properties";
            if (configPath.lastIndexOf(suffix) == -1) {
                configPath += suffix;
            }
            inputStream = ClassUtil.getClassLoader().getResourceAsStream(configPath);
            if (inputStream != null) {
                props.load(inputStream);
            }
        } catch (Exception e) {
            logger.error("加载属性文件出错", e);
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return props;
    }

    public static String getString(Properties props, String key) {
        String value = "";
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    public static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    public static void main(String[] args) {
        Properties properties = loadProps("smart");
        System.out.println(properties.getProperty("smart.framework.app.base_package"));
    }
}
