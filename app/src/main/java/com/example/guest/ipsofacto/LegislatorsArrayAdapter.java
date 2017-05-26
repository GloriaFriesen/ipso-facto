package com.example.guest.ipsofacto;

import android.content.Context;
import android.widget.ArrayAdapter;

public class LegislatorsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mNames;
    private String[] mParties;
    private String[] mPhones;

    public LegislatorsArrayAdapter(Context mContext, int resource, String[] mNames, String[] mParties, String[] mPhones) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mNames = mNames;
        this.mParties = mParties;
        this.mPhones = mPhones;
    }

    @Override
    public Object getItem(int position) {
        String name = mNames[position];
        String party = mParties[position];
        String phone = mPhones[position];
        return String.format("%s \n %s \n %s", name, party, phone);
    }

    @Override
    public int getCount() {
        return mNames.length;
    }
}
