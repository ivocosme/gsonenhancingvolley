package com.ivocosme.gsonenhancingvolley.requests;


import com.android.volley.Response;

import org.json.JSONObject;

public interface GsonRequestListener<T> extends Response.Listener<JSONObject> {

    void onGsonResponse(T response);
}
