package com.example.guest.ipsofacto.services;

import android.util.Log;

import com.example.guest.ipsofacto.Constants;
import com.example.guest.ipsofacto.models.Legislator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    public ArrayList<Legislator> processResults(Response response) {
        ArrayList<Legislator> legislators = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject responseJSON = new JSONObject(jsonData);
                JSONArray legislatorListJSON = responseJSON.getJSONArray("results");
                for (int i = 0; i < legislatorListJSON.length(); i++) {
                    JSONObject legislatorJSON = legislatorListJSON.getJSONObject(i);
                    String name = legislatorJSON.getString("name");
                    String role = legislatorJSON.getString("role");
                    String party = legislatorJSON.getString("party");

                    Legislator legislator = new Legislator(name, role, party);
                    legislators.add(legislator);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return legislators;
    }
}
