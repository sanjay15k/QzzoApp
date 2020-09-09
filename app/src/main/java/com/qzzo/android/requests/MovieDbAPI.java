package com.qzzo.android.requests;

import com.qzzo.android.requests.response.MovieListResponse;
import com.qzzo.android.requests.response.TrailerListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDbAPI {

    @GET("popular/")
    Call<MovieListResponse> getMovieList();

    @GET("{movie_id}/videos")
    Call<TrailerListResponse> getTrailerList(@Path("movie_id") String movieID);

}
