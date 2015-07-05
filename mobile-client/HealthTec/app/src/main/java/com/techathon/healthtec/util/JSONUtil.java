package com.techathon.healthtec.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.techathon.healthtec.model.Exercise;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Paptimus on 4/7/2015.
 */
public class JSONUtil {
    public static String ObjectToJSON(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static List<Exercise> JSONToObject(String json) {
        Type listType = new TypeToken<ArrayList<Exercise>>() {}.getType();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
                    JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        return builder.create().fromJson(new JsonParser().parse(json), listType);
    }
}
