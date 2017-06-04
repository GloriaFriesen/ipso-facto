package com.example.guest.ipsofacto.services;

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


public class LegislatorService {

    public static void findLegislators(String state, String chamber, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addPathSegment(chamber);
        urlBuilder.addPathSegment(state);
        urlBuilder.addPathSegment(Constants.CURRENT_JSON_PATH);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header(Constants.HEADER, Constants.API_KEY)
                .build();

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
                    String url = legislatorJSON.getString("api_uri");

                    Legislator legislator = new Legislator(name, role, party, url);
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

    public static void findDetailLegislator(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .header(Constants.HEADER, Constants.API_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public Legislator processDetailResults(Legislator legislator, Response response) {
        Legislator detailLegislator = new Legislator();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject responseJSON = new JSONObject(jsonData);

                JSONArray resultsJSON = responseJSON.getJSONArray("results");
                JSONObject legislatorJSON = resultsJSON.getJSONObject(0);
                String website = legislatorJSON.getString("url");
                String timesWebsite = legislatorJSON.getString("times_topics_url");
                if (timesWebsite.equals("")) {
                    timesWebsite = "None listed";
                }
                JSONArray rolesJSON = legislatorJSON.getJSONArray("roles");
                JSONObject legislatorDetailJSON = rolesJSON.getJSONObject(0);
                String phone = legislatorDetailJSON.getString("phone");
                String startDate = legislatorDetailJSON.getString("start_date");
                String votePercent = legislatorDetailJSON.getString("votes_with_party_pct");

                detailLegislator = new Legislator(legislator.getName(), legislator.getRole(), legislator.getParty(), legislator.getDetailURL(), phone, website, timesWebsite, startDate, votePercent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return detailLegislator;
    }
}
