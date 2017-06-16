package com.example.guest.ipsofacto.util;


import com.example.guest.ipsofacto.models.Legislator;

import java.util.ArrayList;

public interface OnLegislatorSelectedListener {
    public void onLegislatorSelected(Integer position, ArrayList<Legislator> legislators, String source);
}

