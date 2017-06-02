package com.example.guest.ipsofacto.services;

import android.util.Log;

import com.example.guest.ipsofacto.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Guest on 6/2/17.
 */

public class LegislatorService {

    public static void findLegislators(String state, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addPathSegment("OR");
        urlBuilder.addPathSegment(Constants.CURRENT_JSON_PATH);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header(Constants.HEADER, Constants.API_KEY)
                .build();

        Log.v("url", url);

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
