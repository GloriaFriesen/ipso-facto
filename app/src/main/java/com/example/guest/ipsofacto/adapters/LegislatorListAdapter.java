package com.example.guest.ipsofacto.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.ipsofacto.Constants;
import com.example.guest.ipsofacto.R;
import com.example.guest.ipsofacto.models.Legislator;
import com.example.guest.ipsofacto.ui.LegislatorDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LegislatorListAdapter extends RecyclerView.Adapter<LegislatorListAdapter.LegislatorViewHolder> {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private ArrayList<Legislator> mLegislators = new ArrayList<>();
    private Context mContext;
    private Set<String> fetch;

    @Override
    public LegislatorListAdapter.LegislatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.legislator_list_item, parent, false);
        LegislatorViewHolder viewHolder = new LegislatorViewHolder(view);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        fetch = mSharedPreferences.getStringSet(Constants.PREFERENCES_SEARCHED_KEY, null);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LegislatorListAdapter.LegislatorViewHolder holder, int position) {
        holder.bindLegislator(mLegislators.get(position));
    }

    @Override
    public int getItemCount() {
        return mLegislators.size();
    }

    public LegislatorListAdapter(Context context, ArrayList<Legislator> legislators) {
        mContext = context;
        mLegislators = legislators;
    }

    public class LegislatorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.nameTextView) TextView mNameTextView;
        @Bind(R.id.partyTextView) TextView mPartyTextView;
        @Bind(R.id.roleTextView) TextView mRoleTextView;

        private Context mContext;

        public LegislatorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindLegislator(Legislator legislator) {
            mNameTextView.setText(legislator.getName());
            mPartyTextView.setText(legislator.getParty());
            mRoleTextView.setText(legislator.getRole());

            if (fetch != null) {
                if (fetch.contains(legislator.getName())) {
                    mNameTextView.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                }
            }

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();

            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            mEditor = mSharedPreferences.edit();
            Set<String> set = new HashSet<>();
            set.add(mLegislators.get(itemPosition).getName());
            mEditor.putStringSet(Constants.PREFERENCES_SEARCHED_KEY, set).apply();

            Intent intent = new Intent(mContext, LegislatorDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("legislators", Parcels.wrap(mLegislators));
            mContext.startActivity(intent);
        }
    }
}
