package org.smart4j.framework.mvc.util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * @author tf
 * @create 2017-07-03 16:32
 **/
public class JSONUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJSON(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException("bean转json失败", e);
        }
    }

    public static <T> T fromJSON(String json, Class<T> cls) {
        try {
            return OBJECT_MAPPER.readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException("json转bean失败", e);
        }
    }
}
