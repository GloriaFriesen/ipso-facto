package com.example.guest.ipsofacto.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.ipsofacto.Constants;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.models.Legislator;
import com.example.guest.ipsofacto.ui.LegislatorDetailActivity;
import com.example.guest.ipsofacto.util.ItemTouchHelperViewHolder;
import com.example.guest.ipsofacto.util.OnStartDragListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseLegislatorViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    View mView;
    Context mContext;
    public ImageView mIconImageView;

    public FirebaseLegislatorViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindLegislator(Legislator legislator) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.nameTextView);
        TextView roleTextView = (TextView) mView.findViewById(R.id.roleTextView);
        TextView partyTextView = (TextView) mView.findViewById(R.id.partyTextView);
        mIconImageView = (ImageView) mView.findViewById(R.id.iconImageView);

        nameTextView.setText(legislator.getName());
        roleTextView.setText(legislator.getRole());
        partyTextView.setText(legislator.getParty());
    }

    @Override
    public void onItemSelected() {
        itemView.animate()
                .alpha(0.8f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }

}
