package com.ivocosme.gsonenhancingvolley.requests;


import com.android.volley.Response;

import org.json.JSONArray;

import java.util.List;

public interface GsonRequestArrayListener<T> extends Response.Listener<JSONArray> {

    void onGsonResponse(List<T> response);
}
