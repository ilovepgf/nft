package org.inlighting.common;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * 
 * @author pugaofei
 * @version $Id: JsonHelper.java, v 0.1 2015年8月24日 下午12:30:58 pugaofei Exp $
 */
public class JsonHelper {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        //config.configEnumAsJavaBean(OnOffEnum.class);
    }

    private static final SerializerFeature[] features = { SerializerFeature.WriteMapNullValue, // 输出空置字段
                                                          SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
                                                          //SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
                                                          SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
                                                          SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null

    };

    /**
     * list 转换成json 空字段需要显示
     * 
     * @param list
     * @return json字符串
     */
    public static String list2json(List list) {
        String json = JSON.toJSONString(list, config, features);
        return json;
    }

    /**
     * 空字段不显示
     * 
     * @param list
     * @return
     */
    public static String list2jsonNull(List list) {
        String json = JSON.toJSONString(list);
        return json;
    }

    /**
     * 
     * @Title: json2list
     * @Description: 把json字符串转换为list
     * @param json
     * @return
     */
    public static List<Object> json2list(String json) {
        return JSON.parseArray(json);
    }

    /**
     * 把json字符串转换为list
     * 
     * @param json
     *            json字符串
     * @param s
     *            类名
     * @return list
     */
    public static <T> List<T> json2list(String json, Class s) {
        return JSON.parseArray(json, s);
    }

    /**
     * 把map格式的json字符串转换成map
     * 
     * @param json
     *            map格式的json字符串
     * @return map格式的数据
     */
    public static Map<Object, Object> json2Map(String json) {
        Map<Object, Object> map = (Map) JSON.parse(json);
        return map;
    }

    /**
     * 把map格式的json字符串转换成map
     * 
     * @param <T>
     * 
     * @param json
     *            map格式的json字符串
     * @return map格式的数据
     */
    public static <T> T json2Class(String json, Class<T> clazz) {

        return JSONObject.parseObject(json, clazz);

    }

    /**
     * 把map转换成 json字符串
     * 
     * @param map
     * @return json字符串
     */
    public static String map2json(Map<Object, Object> map) {
        String json = JSON.toJSONString(map, config, features);
        return json;
    }

    /**
     * java 对象转换成JSON格式
     * 
     * @param o
     * @return
     */
    public static String object2json(Object o) {
        String json = JSON.toJSONString(o, config, features);
        return json;
    }

    public static void main(String[] args) {
        Map dataMap = new HashMap();
        dataMap.put("type", "1");
        Map data = new HashMap();
        data.put("name", "123");
        data.put("age", "123");
        dataMap.put("data", data);
        String json = JsonHelper.map2json(dataMap);
        Map map = JsonHelper.json2Map(json);
        // System.out.println(map.get("data"));
    }

}
