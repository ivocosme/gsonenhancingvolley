package com.ivocosme.gsonenhancingvolley.requests;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GsonRequestArray<T> extends JsonArrayRequest {
    private GsonRequestArrayListener<T> mListener;
    private Class<T> mClass;

    public GsonRequestArray(Class<T> returnClass, int method, String url, GsonRequestArrayListener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, null, listener, errorListener);
        mListener = listener;
        mClass = returnClass;
    }

    @Override
    protected void deliverResponse(JSONArray jsonArray) {
        if(mListener != null && jsonArray != null) {
            List<T> responseArray = new ArrayList<T>();
            for(int i=0;i<jsonArray.length(); i++) {
                try {
                    JSONObject entry = jsonArray.getJSONObject(i);
                    T parsedResponse = new Gson().fromJson(entry.toString(), mClass);
                    if(parsedResponse != null) {
                        responseArray.add(parsedResponse);
                    }
                } catch (JSONException e) {
                    Log.d(getClass().getSimpleName(), "Error parsing JSON Object: "+e.getMessage());
                    mListener.onResponse(jsonArray);
                }
            }
            mListener.onGsonResponse(responseArray);
        }
    }
}
