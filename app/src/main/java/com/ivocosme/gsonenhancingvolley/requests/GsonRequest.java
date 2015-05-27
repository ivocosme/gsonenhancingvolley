package com.ivocosme.gsonenhancingvolley.requests;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

public class GsonRequest<T> extends JsonObjectRequest {
    private GsonRequestListener<T> mListener;
    private Response.ErrorListener mErrorListener;
    private Class<T> mClass;

    public GsonRequest(Class<T> returnClass, int method, String url, GsonRequestListener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, null, listener, errorListener);
        mListener = listener;
        mErrorListener = errorListener;
        mClass = returnClass;
    }

    @Override
    protected void deliverResponse(JSONObject jsonObject) {
        if(mListener != null) {
            String parsingError = "Unable to parse JSONObject";
            if(jsonObject != null) {
                try {
                    T parsedResponse = new Gson().fromJson(jsonObject.toString(), mClass);
                    if(parsedResponse != null) {
                        mListener.onGsonResponse(parsedResponse);
                        return;
                    }
                } catch (IllegalStateException exception) {
                    parsingError = exception.getMessage();
                }
            }
            if(mErrorListener != null) {
                mErrorListener.onErrorResponse(new VolleyError(parsingError));
            }
        }
    }
}
