package com.app.service.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static JSONObject stringToJsonObject(String data) {
        JSONObject jsonObject = null;
        try
        {
            jsonObject = new JSONObject(data);
        }
        catch (JSONException e)
        {
            logger.error("Error converting String to JSON Object");
        }
        return jsonObject;
    }

    public static JSONArray stringToJSONArray(String data) {
        JSONArray jsonArray = null;
        try
        {
            jsonArray = new JSONArray(data);
        }
        catch (JSONException e)
        {
            logger.error("Error converting String to JSON Array");
        }
        return jsonArray;
    }

    public static ArrayList<Integer> getIntegerArrayList(JSONArray jsonArray) {
        ArrayList<Integer> result = new ArrayList<>();
        try
        {
            for(int i = 0; i < jsonArray.length(); i++)
                result.add(jsonArray.getInt(i));
        }
        catch(JSONException e)
        {
            logger.error("Error parsing JSON Array");
        }
        return result;
    }
}
