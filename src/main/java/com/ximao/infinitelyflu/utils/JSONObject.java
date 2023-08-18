package com.ximao.infinitelyflu.utils;

import java.util.LinkedHashMap;


/**
 * 自实现保序的JSONObject
 * @author ximao
 * @date 2021/8/15
 */
public class JSONObject extends org.json.JSONObject {

    /**
     * 此处将map实现由hashMap改成LinkedHashMap
     */
    public JSONObject() {
        this.map = new LinkedHashMap<String, Object>();
    }

}
