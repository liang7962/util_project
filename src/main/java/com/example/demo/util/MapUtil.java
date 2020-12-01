package com.example.demo.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapUtil {

    /**
     * 复制map对象
     * @explain 将paramsMap中的键值对全部拷贝到resultMap中；
     * paramsMap中的内容不会影响到resultMap（深拷贝）
     * @param paramsMap
     *     被拷贝对象  -
     * @param resultMap
     *     拷贝后的对象
     */
    public static void mapCopy(Map paramsMap, Map resultMap) {
        if (resultMap == null) resultMap = new HashMap();
        if (paramsMap == null) return;

        Iterator it = paramsMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            resultMap.put(key, paramsMap.get(key) != null ? paramsMap.get(key) : "");

        }
    }

    /**
     * 复制map对象
     * @explain 将paramsMap中的键值对全部拷贝到resultMap中；
     *  paramsMap中的内容不会影响到resultMap（深拷贝）
     *  拷贝原则：如果paramsMap的key在resultMap中存在，则不处理，不存在则新增进resultMap
    * */
    public static void mapUpdCopy(Map paramsMap, Map resultMap) {
        if (resultMap == null) resultMap = new HashMap();
        if (paramsMap == null) return;

        Iterator it = paramsMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            if (!resultMap.containsKey(key)){
                resultMap.put(key, paramsMap.get(key) != null ? paramsMap.get(key) : "");
            }

        }
    }
}
