package com.example.guest.ipsofacto.ui;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.guest.ipsofacto.Constants;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.adapters.FirebaseLegislatorListAdapter;
import com.example.guest.ipsofacto.adapters.FirebaseLegislatorViewHolder;
import com.example.guest.ipsofacto.models.Legislator;
import com.example.guest.ipsofacto.util.OnStartDragListener;
import com.example.guest.ipsofacto.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import butterknife.BindView;
import butterknife.ButterKnife;


public class SavedLegislatorListFragment extends Fragment implements OnStartDragListener {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private FirebaseLegislatorListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    public SavedLegislatorListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_saved_legislator_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_LEGISLATORS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mFirebaseAdapter = new FirebaseLegislatorListAdapter(Legislator.class,
                R.layout.legislator_list_item_drag, FirebaseLegislatorViewHolder.class,
                query, this, getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

}
