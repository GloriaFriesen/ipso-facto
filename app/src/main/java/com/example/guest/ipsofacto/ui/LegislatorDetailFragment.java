package com.example.guest.ipsofacto.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.adapters.LegislatorListAdapter;
import com.example.guest.ipsofacto.models.Legislator;
import com.example.guest.ipsofacto.services.LegislatorService;

import org.parceler.Parcels;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


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

        getDetailLegislator(mLegislator);
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

    private void getDetailLegislator(final Legislator legislator) {
        final LegislatorService legislatorService = new LegislatorService();
        legislatorService.findDetailLegislator(legislator.getDetailURL(), new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mLegislator = legislatorService.processDetailResults(legislator, response);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    mPhoneView.setText(mLegislator.getPhone());
                    mStartDateView.setText(mLegislator.getStartDate());
                    mTimesURLView.setText(mLegislator.getTimesWebsite());
                    mVotePartyPercentView.setText(mLegislator.getVotePercent());
                    mWebsiteView.setText(mLegislator.getWebsite());
                    }
                });
            }
        });
    }

}
