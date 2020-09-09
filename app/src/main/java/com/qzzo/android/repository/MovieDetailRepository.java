package com.qzzo.android.repository;

import androidx.lifecycle.LiveData;

import com.qzzo.android.models.Movie;
import com.qzzo.android.models.Trailer;
import com.qzzo.android.requests.networkClient.MovieDetailClient;
import com.qzzo.android.requests.networkClient.MovieListClient;

import java.util.List;

public class MovieDetailRepository {

    private static MovieDetailRepository instance;
    private final MovieDetailClient movieDetailClient;

    public static MovieDetailRepository getInstance(){
        if(instance == null){
            instance = new MovieDetailRepository();
        }
        return instance;
    }

    private MovieDetailRepository(){
        movieDetailClient = MovieDetailClient.getInstance();
    }

    public LiveData<List<Trailer>> getTrailers(){ return movieDetailClient.getTrailers(); }

    public void getTrailerList(String movieID){
        movieDetailClient.getTrailerList(movieID);
    }

    public void cancelRequest(boolean isCancel){
        movieDetailClient.cancelRequest(isCancel);
    }

}