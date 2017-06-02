package com.example.guest.ipsofacto.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.models.Legislator;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LegislatorDetailFragment extends Fragment {
    @Bind(R.id.detailNameTextView) TextView mNameView;
    @Bind(R.id.detailPartyTextView) TextView mPartyView;
    @Bind(R.id.detailPhoneTextView) TextView mPhoneView;
    @Bind(R.id.detailStartDateTextView) TextView mStartDateView;
    @Bind(R.id.detailTimesURLTextView) TextView mTimesURLView;
    @Bind(R.id.detailVotePartyPercentageTextView) TextView mVotePartyPercentView;
    @Bind(R.id.detailWebsiteTextView) TextView mWebsiteView;
    @Bind(R.id.saveLegislatorButton) Button mSaveLegislatorButton;

    private Legislator mLegislator;

    public LegislatorDetailFragment() {
        // Required empty public constructor
    }

    public static LegislatorDetailFragment newInstance(Legislator legislator) {
        LegislatorDetailFragment legislatorDetailFragment = new LegislatorDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("legislator", Parcels.wrap(legislator));
        legislatorDetailFragment.setArguments(args);
        return legislatorDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLegislator = Parcels.unwrap(getArguments().getParcelable("legislator"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_legislator_detail, container, false);
        ButterKnife.bind(this, view);

        mNameView.setText(mLegislator.getName());
        mPartyView.setText(mLegislator.getParty());

        return view;
    }

}
