package com.qzzo.android.requests.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.qzzo.android.models.Movie;
import com.qzzo.android.models.Trailer;

import java.util.List;

public class TrailerListResponse {

    @SerializedName("results")
    @Expose
    private List<Trailer> trailerList;

    public List<Trailer> getTrailerList() {
        return trailerList;
    }
}
