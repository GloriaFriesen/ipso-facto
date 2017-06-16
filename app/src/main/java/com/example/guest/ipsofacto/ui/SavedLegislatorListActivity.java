package com.example.guest.ipsofacto.ui;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.adapters.FirebaseLegislatorListAdapter;
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedLegislatorListActivity extends AppCompatActivity {
    private DatabaseReference mLegislatorReference;
    private FirebaseLegislatorListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_saved_legislator_list);
        ButterKnife.bind(this);
    }
}
