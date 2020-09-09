package com.qzzo.android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trailer {

    @Expose
    @SerializedName("id")
    private String trailerId;
    @Expose
    @SerializedName("key")
    private String key;
    @Expose
    @SerializedName("site")
    private String site;

    public Trailer(String trailerId, String key, String site) {
        this.trailerId = trailerId;
        this.key = key;
        this.site = site;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "trailerId='" + trailerId + '\'' +
                ", key='" + key + '\'' +
                ", site='" + site + '\'' +
                '}';
    }
}
