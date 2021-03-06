package com.example.guest.ipsofacto.ui;

import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guest.ipsofacto.Constants;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.adapters.LegislatorPagerAdapter;
import com.example.guest.ipsofacto.models.Legislator;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LegislatorDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private LegislatorPagerAdapter adapterViewPager;
    ArrayList<Legislator> mLegislators = new ArrayList<>();
    private String mSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislator_detail);
        ButterKnife.bind(this);

        mLegislators = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_LEGISLATORS));
        int startingPosition = getIntent().getIntExtra(Constants.EXTRA_KEY_POSITION, 0);
        mSource = getIntent().getStringExtra(Constants.KEY_SOURCE);

        adapterViewPager = new LegislatorPagerAdapter(getSupportFragmentManager(), mLegislators, mSource);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(startingPosition);
    }
}
