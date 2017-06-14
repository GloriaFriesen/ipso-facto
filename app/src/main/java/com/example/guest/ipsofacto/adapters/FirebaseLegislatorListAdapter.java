package com.example.guest.ipsofacto.adapters;


import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.example.guest.ipsofacto.models.Legislator;
import com.example.guest.ipsofacto.util.ItemTouchHelperAdapter;
import com.example.guest.ipsofacto.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class FirebaseLegislatorListAdapter extends FirebaseRecyclerAdapter<Legislator, FirebaseLegislatorViewHolder> implements ItemTouchHelperAdapter{
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseLegislatorListAdapter(Class<Legislator> modelClass, int modelLayout, Class<FirebaseLegislatorViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
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
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}
