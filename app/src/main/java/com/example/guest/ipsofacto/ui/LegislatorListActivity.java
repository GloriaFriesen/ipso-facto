package com.example.guest.ipsofacto.ui;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.guest.ipsofacto.Constants;
import com.example.guest.ipsofacto.adapters.LegislatorListAdapter;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.models.Legislator;
import com.example.guest.ipsofacto.util.OnLegislatorSelectedListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LegislatorListActivity extends AppCompatActivity implements OnLegislatorSelectedListener {
    public ArrayList<Legislator> mLegislators = new ArrayList<>();
    private Integer mPosition;
    private String mSource;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislator_list);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION, mPosition);
            mLegislators = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_LEGISLATORS));

            if (mPosition != null && mLegislators != null) {
                Intent intent = new Intent(this, LegislatorDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                intent.putExtra(Constants.KEY_SOURCE, mSource);
                intent.putExtra(Constants.EXTRA_KEY_LEGISLATORS, Parcels.wrap(mLegislators));
                startActivity(intent);
            }
        }
    }

    @Override
    public void onLegislatorSelected(Integer position, ArrayList<Legislator> legislators, String source) {
        mPosition = position;
        mLegislators = legislators;
        mSource = source;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPosition != null && mLegislators != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_LEGISLATORS, Parcels.wrap(mLegislators));
            outState.putString(Constants.KEY_SOURCE, mSource);
        }
    }
}