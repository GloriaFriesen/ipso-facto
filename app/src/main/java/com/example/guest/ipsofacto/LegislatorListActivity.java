package com.example.guest.ipsofacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LegislatorListActivity extends AppCompatActivity {
    private String[] names = new String[] {"Abby", "Jack", "Teddy"};
    private String[] parties = new String[] {"Democrat", "Independent", "Republican"};
    private String[] phones = new String[] {"202-555-1234", "503-867-5309", "347-489-4608"};

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
        mStateTextView.setText("This is the state you selected " + state);
    }
}