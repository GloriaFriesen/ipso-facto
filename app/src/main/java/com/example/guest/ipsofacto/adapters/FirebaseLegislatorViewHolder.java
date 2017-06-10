package com.example.guest.ipsofacto.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.guest.ipsofacto.Constants;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.models.Legislator;
import com.example.guest.ipsofacto.ui.LegislatorDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 6/9/17.
 */

public class FirebaseLegislatorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseLegislatorViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindLegislator(Legislator legislator) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.nameTextView);
        TextView roleTextView = (TextView) mView.findViewById(R.id.roleTextView);
        TextView partyTextView = (TextView) mView.findViewById(R.id.partyTextView);

        nameTextView.setText(legislator.getName());
        roleTextView.setText(legislator.getRole());
        partyTextView.setText(legislator.getParty());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Legislator> legislators = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_LEGISLATORS);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    legislators.add(snapshot.getValue(Legislator.class));
                }
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, LegislatorDetailActivity.class);
                intent.putExtra("position", itemPosition+"");
                intent.putExtra("legislators", Parcels.wrap(legislators));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
