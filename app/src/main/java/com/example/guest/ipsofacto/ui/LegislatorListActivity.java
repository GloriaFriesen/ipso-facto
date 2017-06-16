package com.example.guest.ipsofacto.ui;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.example.guest.ipsofacto.adapters.LegislatorListAdapter;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.models.Legislator;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LegislatorListActivity extends AppCompatActivity {
    public ArrayList<Legislator> mLegislators = new ArrayList<>();
    private LegislatorListAdapter mAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislator_list);
        ButterKnife.bind(this);

//        Intent intent = getIntent();
//        String state = intent.getStringExtra("state");
//        String chamber = intent.getStringExtra("chamber");


    }


}