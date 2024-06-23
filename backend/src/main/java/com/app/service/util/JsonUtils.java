package com.app.service.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static JSONObject stringToJsonObject(String data) {
        JSONObject jsonObject = null;
        try
        {
            jsonObject = new JSONObject(data);
        }
        catch (JSONException e)
        {
            System.err.println("Error converting String to JSON Array");
            e.printStackTrace();
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
            System.err.println("Error converting String to JSON Array");
            e.printStackTrace();
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
            System.out.println("Error parsing JSON Array");
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Double> getDoubleArrayList(JSONArray jsonArray) {
        ArrayList<Double> result = new ArrayList<>();
        try
        {
            for(int i = 0; i < jsonArray.length(); i++)
                result.add(jsonArray.getDouble(i));
        }
        catch(JSONException e)
        {
            System.out.println("Error parsing JSON Array");
            e.printStackTrace();
        }
        return result;
    }
}
