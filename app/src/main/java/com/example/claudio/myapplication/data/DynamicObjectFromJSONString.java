package com.example.claudio.myapplication.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DynamicObjectFromJSONString implements JsonToObject{

    private final String jsonString;
    private final String attributes[];

    public DynamicObjectFromJSONString(String jsonString, String attributes[]) throws IllegalArgumentException{
        if(jsonString == null){
            throw new IllegalArgumentException("The argument jsonString can't be null");
        }
        this.jsonString = jsonString;
        this.attributes = attributes;
    }

    @Override
    public ArrayList<HashMap<String,String>> toArray(String nameOfJsonArray) throws JSONException{
        ArrayList<HashMap<String,String>> objectArray = new ArrayList<>();
        JSONArray jsonArray;
        if(nameOfJsonArray == null){
            jsonArray = new JSONArray(this.jsonString);
        }else{
            JSONObject jsonObject = new JSONObject(this.jsonString);
            jsonArray = jsonObject.getJSONArray(nameOfJsonArray);
        }
        for(int i = 0; i < jsonArray.length();  i++){
            HashMap<String, String> attributeValue = new HashMap<>();
            for(final String attr : this.attributes){
                JSONObject jo = jsonArray.getJSONObject(i);
                String value = jo.getString(attr);
                attributeValue.put(attr, value);
            }
            objectArray.add(attributeValue);
        }

        return objectArray;
    }

    @Override
    public HashMap<String,String> toOject() throws JSONException{
        HashMap<String, String> attributeValue = new HashMap<>();
        JSONObject jsonObject = new JSONObject(this.jsonString);

        for(final String attr : this.attributes){
            String value = jsonObject.getString(attr);
            attributeValue.put(attr, value);
        }
        return attributeValue;
    }
}
