package com.example.guest.ipsofacto.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.guest.ipsofacto.Constants;
import com.example.guest.ipsofacto.models.Legislator;
import com.example.guest.ipsofacto.ui.LegislatorDetailActivity;
import com.example.guest.ipsofacto.util.ItemTouchHelperAdapter;
import com.example.guest.ipsofacto.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseLegislatorListAdapter extends FirebaseRecyclerAdapter<Legislator, FirebaseLegislatorViewHolder> implements ItemTouchHelperAdapter{
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Legislator> mLegislators = new ArrayList<>();

    public FirebaseLegislatorListAdapter(Class<Legislator> modelClass, int modelLayout, Class<FirebaseLegislatorViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mLegislators.add(dataSnapshot.getValue(Legislator.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseLegislatorViewHolder viewHolder, Legislator model, int position) {
        viewHolder.bindLegislator(model);
        viewHolder.mIconImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = viewHolder.getAdapterPosition();
                Intent intent = new Intent(mContext, LegislatorDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, viewHolder.getAdapterPosition());
                intent.putExtra(Constants.EXTRA_KEY_LEGISLATORS, Parcels.wrap(mLegislators));
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_SAVED);
                mContext.startActivity(intent);
            }
        });
    }

    private void setIndexInFirebase() {
        for (Legislator legislator : mLegislators) {
            int index = mLegislators.indexOf(legislator);
            DatabaseReference ref = getRef(index);
            legislator.setIndex(Integer.toString(index));
            ref.setValue(legislator);
        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mLegislators, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mLegislators.remove(position);
        getRef(position).removeValue();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}
