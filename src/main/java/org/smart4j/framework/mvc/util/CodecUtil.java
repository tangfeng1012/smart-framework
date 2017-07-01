package org.smart4j.framework.mvc.util;

import org.smart4j.framework.FrameworkConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author tf
 * @create 2017-07-01 16:24
 **/
public class CodecUtil {

    public static String decodeUrl(String url) {
        String targetUrl = null;
        try {
            targetUrl = URLDecoder.decode(url, FrameworkConstant.ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("解码错误", e);
        }
        return targetUrl;
    }

    public static String encodeUrl(String url) {
        String target = null;
        try {
            target = URLEncoder.encode(url, FrameworkConstant.ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("", e);
        }
        return target;
    }

}
