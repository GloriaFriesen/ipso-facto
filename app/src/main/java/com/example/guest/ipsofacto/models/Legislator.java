package com.example.guest.ipsofacto.models;

/**
 * Created by Guest on 6/2/17.
 */

public class Legislator {
    private String mName;
    private String mRole;
    private String mParty;

//    public Legislator() {}

    public Legislator(String name, String role, String party) {
        this.mName = name;
        this.mRole = role;
        this.mParty = party;
    }

    public String getName() {
        return mName;
    }

    public String getRole() {
        return mRole;
    }

    public String getParty() {
        return mParty;
    }
}

