package com.example.guest.ipsofacto.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.adapters.LegislatorListAdapter;
import com.example.guest.ipsofacto.models.Legislator;
import com.example.guest.ipsofacto.services.LegislatorService;
import com.example.guest.ipsofacto.util.OnLegislatorSelectedListener;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class LegislatorListFragment extends Fragment {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private LegislatorListAdapter mAdapter;
    public ArrayList<Legislator> mLegislators = new ArrayList<>();
    private OnLegislatorSelectedListener mOnLegislatorSelectedListener;

    public LegislatorListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnLegislatorSelectedListener = (OnLegislatorSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + e.getMessage());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_legislator_list, container, false);
        ButterKnife.bind(this, view);

        String state = getActivity().getIntent().getStringExtra("state");
        String chamber = getActivity().getIntent().getStringExtra("chamber");

        getLegislators(state, chamber);

        return view;
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

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new LegislatorListAdapter(getActivity(), mLegislators, mOnLegislatorSelectedListener);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

}
