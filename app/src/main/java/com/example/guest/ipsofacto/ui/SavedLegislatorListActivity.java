package com.example.guest.ipsofacto.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.guest.ipsofacto.Constants;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.adapters.FirebaseLegislatorListAdapter;
import com.example.guest.ipsofacto.adapters.FirebaseLegislatorViewHolder;
import com.example.guest.ipsofacto.models.Legislator;
import com.example.guest.ipsofacto.util.OnStartDragListener;
import com.example.guest.ipsofacto.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedLegislatorListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mLegislatorReference;
    private FirebaseLegislatorListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_legislator_list);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mLegislatorReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_LEGISLATORS)
                .child(uid);

        mFirebaseAdapter = new FirebaseLegislatorListAdapter(Legislator.class,
                R.layout.legislator_list_item_drag, FirebaseLegislatorViewHolder.class,
                mLegislatorReference, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
