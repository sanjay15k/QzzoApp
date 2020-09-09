package com.qzzo.android.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.qzzo.android.models.Trailer;
import com.qzzo.android.repository.MovieDetailRepository;

import java.util.List;

public class MovieDetailViewModel extends ViewModel {

    private final MovieDetailRepository movieDetailRepository;

    public MovieDetailViewModel(){
        movieDetailRepository = MovieDetailRepository.getInstance();
    }

    public LiveData<List<Trailer>> getTrailers(){
        return movieDetailRepository.getTrailers();
    }

    public void getTrailerList(String movieID){
        movieDetailRepository.getTrailerList(movieID);
    }

    public void onBackPressed(boolean isBackPressed){
        movieDetailRepository.cancelRequest(isBackPressed);
    }
}
