package com.example.guest.ipsofacto.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guest.ipsofacto.Constants;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.adapters.FirebaseLegislatorViewHolder;
import com.example.guest.ipsofacto.models.Legislator;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedLegislatorListActivity extends AppCompatActivity {
    private DatabaseReference mLegislatorReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislator_list);
        ButterKnife.bind(this);

        mLegislatorReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_LEGISLATORS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Legislator, FirebaseLegislatorViewHolder>(Legislator.class, R.layout.legislator_list_item, FirebaseLegislatorViewHolder.class, mLegislatorReference) {
            @Override
            protected  void populateViewHolder(FirebaseLegislatorViewHolder viewHolder, Legislator model, int position) {
                viewHolder.bindLegislator(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
