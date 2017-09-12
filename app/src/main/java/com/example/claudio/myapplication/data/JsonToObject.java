package com.example.claudio.myapplication.data;


import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public interface JsonToObject {
    ArrayList<HashMap<String,String>> toArray(String nameOfJsonArray) throws JSONException;
    Object toOject() throws JSONException;
}
