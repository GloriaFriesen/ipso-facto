package com.example.guest.ipsofacto.models;

import org.parceler.Parcel;

@Parcel
public class Legislator {
    private String name;
    private String role;
    private String party;
    private String detailURL;
    private String phone;
    private String website;
    private String timesWebsite;
    private String startDate;
    private String votePercent;
    private String pushId;

    public Legislator() {}

    public Legislator(String name, String role, String party, String detailURL) {
        this.name = name;
        this.role = role;
        this.party = party;
        this.detailURL = detailURL;
    }

    public Legislator(String name, String role, String party, String detailURL, String phone, String website, String timesWebsite, String startDate, String votePercent) {
        this.name = name;
        this.role = role;
        this.party = party;
        this.detailURL = detailURL;
        this.phone = phone;
        this.website = website;
        this.timesWebsite = timesWebsite;
        this.startDate = startDate;
        this.votePercent = votePercent;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getParty() {
        if (party.equals("D")) {
            party = "Democratic";
        } else if (party.equals("R")) {
            party = "Republican";
        } else if (party.equals("I")) {
            party = "Independent";
        }
        return party;
    }

    public String getDetailURL() {
        return detailURL;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getTimesWebsite() {
        return timesWebsite;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getVotePercent() {
        return votePercent;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}

