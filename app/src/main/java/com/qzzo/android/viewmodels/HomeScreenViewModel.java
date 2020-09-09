package com.qzzo.android.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.qzzo.android.models.Movie;
import com.qzzo.android.repository.HomeScreenRepository;

import java.util.List;

public class HomeScreenViewModel extends ViewModel {

    private final HomeScreenRepository homeScreenRepository;

    public HomeScreenViewModel(){
        homeScreenRepository = HomeScreenRepository.getInstance();
    }

    public LiveData<List<Movie>> getMovies(){
        return homeScreenRepository.getMovies();
    }

    public void getMovieList(){
        homeScreenRepository.getMovieList();
    }

    public void onBackPressed(boolean isBackPressed){
        homeScreenRepository.cancelRequest(isBackPressed);
    }
}
