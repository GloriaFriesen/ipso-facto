package com.example.guest.ipsofacto.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.ipsofacto.Constants;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.models.Legislator;
import com.example.guest.ipsofacto.services.LegislatorService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class LegislatorDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.detailNameTextView) TextView mNameView;
    @Bind(R.id.detailPartyTextView) TextView mPartyView;
    @Bind(R.id.detailPhoneTextView) TextView mPhoneView;
    @Bind(R.id.detailBirthDateTextView) TextView mBirthDateTextView;
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

        mPhoneView.setOnClickListener(this);
        mTimesURLView.setOnClickListener(this);
        mWebsiteView.setOnClickListener(this);
        mSaveLegislatorButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mPhoneView) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mLegislator.getPhone()));
            try {
                startActivity(phoneIntent);
            } catch (Exception exception){
                Toast.makeText(v.getContext(), "This device doesn't seem to have a phone.", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == mTimesURLView) {
            if (!mLegislator.getTimesWebsite().equals("None listed")) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mLegislator.getTimesWebsite()));
                startActivity(webIntent);
            }
        }
        if (v == mWebsiteView) {
            if (!mLegislator.getWebsite().equals("")) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mLegislator.getWebsite()));
                startActivity(webIntent);
            }
        }
        if (v == mSaveLegislatorButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference legislatorReference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_LEGISLATORS)
                    .child(uid);
            DatabaseReference databaseReference = legislatorReference.push();
            databaseReference.setValue(mLegislator);

            Intent intent = new Intent(getContext(), SavedLegislatorListActivity.class);
            startActivity(intent);
        }
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
                    mBirthDateTextView.setText(mLegislator.getBirthDate());
                    mTimesURLView.setText(mLegislator.getTimesWebsite());
                    mVotePartyPercentView.setText(mLegislator.getVotePercent() + "%");
                    mWebsiteView.setText(mLegislator.getWebsite());
                    }
                });
            }
        });
    }

}
