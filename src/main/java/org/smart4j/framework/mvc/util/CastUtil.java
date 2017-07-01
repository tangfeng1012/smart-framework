package org.smart4j.framework.mvc.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tf
 * @create 2017-07-01 14:54
 **/
public class CastUtil {

    private static final Logger logger = LoggerFactory.getLogger(CastUtil.class);

    public static String castString(Object obj) {
        return castString(obj, "");
    }

    public static String castString(Object obj, String defaultValue) {
        return obj == null ? defaultValue : obj.toString();
    }

    public static int castInteger(Object obj) {
        return castInteger(obj, 0);
    }

    public static int castInteger(Object obj, Integer defaultValue) {
        int initValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtils.isNotEmpty(strValue)) {
                try {
                    initValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    initValue = defaultValue;
                }
            }
        }
        return initValue;
    }

    public static long castLong(Object obj) {
        return castLong(obj, 0);
    }

    public static long castLong(Object obj, long defaultValue) {
        long initValue = defaultValue;
        if (obj != null) {
            String str = castString(obj);
            if (StringUtils.isNotEmpty(str)) {
                try {
                    initValue = Long.parseLong(str);
                } catch (NumberFormatException e) {
                    initValue = defaultValue;
                }
            }
        }
        return initValue;
    }

    public static double castDouble(Object obj) {
        return castDouble(obj, 0);
    }

    public static double castDouble(Object obj, double defaultValue) {
        double initValue = defaultValue;
        if (obj != null) {
            String str = castString(obj);
            if (StringUtils.isNotEmpty(str)) {
                try {
                    initValue = Double.parseDouble(str);
                } catch (NumberFormatException e) {
                    initValue = defaultValue;
                }
            }
        }
        return initValue;
    }

}
