package com.qzzo.android.repository;

import com.qzzo.android.models.Movie;
import com.qzzo.android.requests.networkClient.MovieListClient;

import java.util.List;

import androidx.lifecycle.LiveData;

public class HomeScreenRepository {

    private static HomeScreenRepository instance;
    private final MovieListClient movieListClient;

    public static HomeScreenRepository getInstance(){
        if(instance == null){
            instance = new HomeScreenRepository();
        }
        return instance;
    }

    private HomeScreenRepository(){
        movieListClient = MovieListClient.getInstance();
    }

    public LiveData<List<Movie>> getMovies(){
        return movieListClient.getMovies();
    }

    public void getMovieList(){
        movieListClient.getMovieList();
    }

    public void cancelRequest(boolean isCancel){
        movieListClient.cancelRequest(isCancel);
    }

}