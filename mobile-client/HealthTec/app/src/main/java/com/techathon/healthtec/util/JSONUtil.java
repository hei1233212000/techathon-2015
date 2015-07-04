package com.techathon.healthtec.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


/**
 * Created by Paptimus on 4/7/2015.
 */
public class JSONUtil {
    public String ObjectToJSON(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public Object JSONToObject(String json, String className) {
        JsonElement jsonElement = (JsonElement) new JsonParser().parse(json);
        Gson gson = new Gson();
        try {
            Object object = gson.fromJson(jsonElement, Class.forName(className));
            return object;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
