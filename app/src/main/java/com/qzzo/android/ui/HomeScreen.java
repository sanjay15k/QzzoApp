package com.qzzo.android.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import com.qzzo.android.BaseActivity;
import com.qzzo.android.R;
import com.qzzo.android.adapters.MovieRecyclerViewAdapter;
import com.qzzo.android.listener.CreateApiCall;
import com.qzzo.android.models.Movie;
import com.qzzo.android.viewmodels.HomeScreenViewModel;

import java.util.List;
import java.util.Objects;

public class HomeScreen extends BaseActivity implements CreateApiCall {

    private RecyclerView movieListRecyclerView;
    private HomeScreenViewModel homeScreenViewModel;
    private LiveData<List<Movie>> movieList;

    @Override
    public void onBackPressed() {
        homeScreenViewModel.onBackPressed(true);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setTheme(R.style.Theme_QzzoApp);
        setContentView(R.layout.activity_home_screen);

        movieListRecyclerView = findViewById(R.id.movieListRecyclerView);

        homeScreenViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(HomeScreenViewModel.class);

        initRecyclerView();
        subscribeObserver();
        sendApiRequest();
    }

    private void subscribeObserver(){
        movieList = homeScreenViewModel.getMovies();
        if(movieList.getValue() != null) {
            movieList.getValue().clear();
        }
        System.out.println("Movie List initially : "+movieList);
        movieList.observe(this, this::addDataToRecyclerView);
    }

    @Override
    public void sendApiRequest() {
        new Handler().postDelayed(() -> homeScreenViewModel.getMovieList(), 0);
        System.out.println("List initially : "+ ((MovieRecyclerViewAdapter) Objects.requireNonNull(movieListRecyclerView.getAdapter())).getMovies());
        showRetryButton(false, null);
        showProgressBar(true);
    }

    private void addDataToRecyclerView(List<Movie> movieList){
        if(movieList !=null && movieList.size()>0) {
            ((MovieRecyclerViewAdapter) Objects.requireNonNull(movieListRecyclerView.getAdapter())).setMovies(movieList);
            System.out.println("Recipe List Finally : "+movieList);
            showProgressBar(false);
        }
    }

    private void initRecyclerView(){
        MovieRecyclerViewAdapter adapter = new MovieRecyclerViewAdapter(0);
        movieListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieListRecyclerView.setAdapter(adapter);
//        MultiSnapHelper snapHelper = new MultiSnapHelper(SnapGravity.CENTER, 2, 100);
//        snapHelper.attachToRecyclerView(movieListRecyclerView);
    }

}