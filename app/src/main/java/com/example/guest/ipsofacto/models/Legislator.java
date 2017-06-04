package com.example.guest.ipsofacto.models;

import org.parceler.Parcel;

@Parcel
public class Legislator {
    private String mName;
    private String mRole;
    private String mParty;
    private String mDetailURL;
    private String mPhone;
    private String mWebsite;
    private String mTimesWebsite;
    private String mStartDate;
    private String mVotePercent;

    public Legislator() {}

    public Legislator(String name, String role, String party, String detailURL) {
        this.mName = name;
        this.mRole = role;
        this.mParty = party;
        this.mDetailURL = detailURL;
    }

    public Legislator(String name, String role, String party, String detailURL, String phone, String website, String timesWebsite, String startDate, String votePercent) {
        this.mName = name;
        this.mRole = role;
        this.mParty = party;
        this.mDetailURL = detailURL;
        this.mPhone = phone;
        this.mWebsite = website;
        this.mTimesWebsite = timesWebsite;
        this.mStartDate = startDate;
        this.mVotePercent = votePercent;
    }

    public String getName() {
        return mName;
    }

    public String getRole() {
        return mRole;
    }

    public String getParty() {
        if (mParty.equals("D")) {
            mParty = "Democratic";
        } else if (mParty.equals("R")) {
            mParty = "Republican";
        } else if (mParty.equals("I")) {
            mParty = "Independent";
        }
        return mParty;
    }

    public String getDetailURL() {
        return mDetailURL;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public String getTimesWebsite() {
        return mTimesWebsite;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public String getVotePercent() {
        return mVotePercent;
    }
}

