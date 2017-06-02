package com.example.guest.ipsofacto.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guest.ipsofacto.adapters.LegislatorsArrayAdapter;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.models.Legislator;
import com.example.guest.ipsofacto.services.LegislatorService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LegislatorListActivity extends AppCompatActivity {
    private String[] names = new String[] {"Abby", "Jack", "Teddy"};
    private String[] parties = new String[] {"Democrat", "Independent", "Republican"};
    private String[] phones = new String[] {"202-555-1234", "503-867-5309", "347-489-4608"};
    public ArrayList<Legislator> mLegislators = new ArrayList<>();

    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.stateTextView) TextView mStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislator_list);
        ButterKnife.bind(this);

        LegislatorsArrayAdapter adapter = new LegislatorsArrayAdapter(this, android.R.layout.simple_list_item_1, names, parties, phones);
        mListView.setAdapter(adapter);
        Intent intent = getIntent();
        String state = intent.getStringExtra("state");
        mStateTextView.setText("Here's a list of congress members in " + state);

        getLegislators(state);
    }

    private void getLegislators(String state) {
        final LegislatorService legislatorService = new LegislatorService();
        legislatorService.findLegislators(state, new Callback() {

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

                    }
                });
            }
        });
    }
}