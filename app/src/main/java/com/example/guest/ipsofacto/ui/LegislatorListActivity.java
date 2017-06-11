package com.example.guest.ipsofacto.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guest.ipsofacto.Constants;
import com.example.guest.ipsofacto.adapters.LegislatorListAdapter;
import com.example.guest.ipsofacto.adapters.LegislatorsArrayAdapter;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.models.Legislator;
import com.example.guest.ipsofacto.services.LegislatorService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LegislatorListActivity extends AppCompatActivity {
    public ArrayList<Legislator> mLegislators = new ArrayList<>();
    private LegislatorListAdapter mAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislator_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String state = intent.getStringExtra("state");
        String chamber = intent.getStringExtra("chamber");

        getLegislators(state, chamber);
    }

    private void getLegislators(String state, String chamber) {
        final LegislatorService legislatorService = new LegislatorService();
        legislatorService.findLegislators(state, chamber, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mLegislators = legislatorService.processResults(response);

                LegislatorListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new LegislatorListAdapter(getApplicationContext(), mLegislators);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LegislatorListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}