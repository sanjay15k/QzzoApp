package com.qzzo.android.requests.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.qzzo.android.models.Movie;

import java.util.List;

public class MovieListResponse {

    @SerializedName("results")
    @Expose
    private List<Movie> movieList;

    public List<Movie> getMovieList() {
        return movieList;
    }
}
