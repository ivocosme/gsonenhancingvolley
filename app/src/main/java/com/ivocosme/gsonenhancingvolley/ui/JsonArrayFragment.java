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
import com.ivocosme.gsonenhancingvolley.requests.GsonRequestArray;
import com.ivocosme.gsonenhancingvolley.requests.GsonRequestArrayListener;
import com.ivocosme.gsonenhancingvolley.requests.objects.JSONResponseObject;

import org.json.JSONArray;

import java.util.List;

public class JsonArrayFragment extends Fragment {
    private static final String TEST_LINK_TO_JSON_ARRAY = "https://dl.dropbox.com/s/2dogurmo7051anm/simple_json_array_example.json";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_json, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        String uri = Uri.parse(TEST_LINK_TO_JSON_ARRAY).buildUpon().build().toString();
        MyApplication.getInstance().addToRequestQueue(
            new GsonRequestArray<>(JSONResponseObject.class, Request.Method.GET, uri, new GsonRequestArrayListener<JSONResponseObject>() {

            @Override
            public void onGsonResponse(List<JSONResponseObject> parsedResponse) {
                //We got what we expected!
                String toAppend = "";
                for(JSONResponseObject response : parsedResponse) {
                    toAppend += response + "\n";
                }
                ((TextView) getView().findViewById(R.id.response)).setText(toAppend);
            }

            @Override
            public void onResponse(JSONArray jsonArray) {
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
