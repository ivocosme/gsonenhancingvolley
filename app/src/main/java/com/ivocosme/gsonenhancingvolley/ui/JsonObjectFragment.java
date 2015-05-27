package com.ivocosme.gsonenhancingvolley.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ivocosme.gsonenhancingvolley.MyApplication;
import com.ivocosme.gsonenhancingvolley.R;
import com.ivocosme.gsonenhancingvolley.requests.GsonRequest;
import com.ivocosme.gsonenhancingvolley.requests.GsonRequestListener;
import com.ivocosme.gsonenhancingvolley.requests.objects.JSONResponseObject;

import org.json.JSONObject;

public class JsonObjectFragment extends Fragment {
    private static final String TEST_LINK_TO_JSON_OBJECT = "https://dl.dropbox.com/s/3c3xf81r1dgf197/simple_json_object_example.json";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_json, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        String uri = Uri.parse(TEST_LINK_TO_JSON_OBJECT).buildUpon().build().toString();
        MyApplication.getInstance().addToRequestQueue(
                new GsonRequest<>(JSONResponseObject.class, Request.Method.GET, uri, new GsonRequestListener<JSONResponseObject>() {

                    @Override
                    public void onGsonResponse(JSONResponseObject parsedResponse) {
                        //We got what we expected!
                        ((TextView) getView().findViewById(R.id.response)).setText(parsedResponse.toString());
                    }

                    @Override
                    public void onResponse(JSONObject jsonArray) {
                        //Something went wrong, but we might still have the response
                        ((TextView) getView().findViewById(R.id.response)).setText(R.string.error);
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //An error occurred
                        ((TextView) getView().findViewById(R.id.response)).setText(R.string.error);
                    }

                }), getClass().getSimpleName());
    }

    @Override
    public void onStop() {
        MyApplication.getInstance().cancelPendingRequests(getClass().getSimpleName());
        super.onStop();
    }
}
