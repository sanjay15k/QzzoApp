package com.qzzo.android.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import com.qzzo.android.BaseActivity;
import com.qzzo.android.R;
import com.qzzo.android.adapters.MovieListRecyclerViewAdapter;
import com.qzzo.android.listener.CreateApiCall;
import com.qzzo.android.models.Movie;
import com.qzzo.android.viewmodels.HomeScreenViewModel;

import java.util.ArrayList;
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

//        List<Movie> movies = new ArrayList<>();
//        initMovieList(movies);

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
        System.out.println("List initially : "+ ((MovieListRecyclerViewAdapter) Objects.requireNonNull(movieListRecyclerView.getAdapter())).getMovies());
        showRetryButton(false, null);
        showProgressBar(true);
    }

    private void addDataToRecyclerView(List<Movie> movieList){
        if(movieList !=null && movieList.size()>0) {
            ((MovieListRecyclerViewAdapter) Objects.requireNonNull(movieListRecyclerView.getAdapter())).setMovies(movieList);
            System.out.println("Recipe List Finally : "+movieList);
            showProgressBar(false);
        }
    }

    private void initRecyclerView(){
        MovieListRecyclerViewAdapter adapter = new MovieListRecyclerViewAdapter();
        movieListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieListRecyclerView.setAdapter(adapter);
//        MultiSnapHelper snapHelper = new MultiSnapHelper(SnapGravity.CENTER, 2, 100);
//        snapHelper.attachToRecyclerView(movieListRecyclerView);
    }

    private void initMovieList(List<Movie> movies){
        Movie movie = new Movie("","Title 1","This is desc 1","2020-10-11","4.5");
        movies.add(movie);
        movie = new Movie("","Title 2","This is desc 2","2020-10-11","8.5");
        movies.add(movie);
        movie = new Movie("","Title 3","This is desc 3","2020-10-11","3.5");
        movies.add(movie);
        movie = new Movie("","Title 4","This is desc 4","2020-10-11","5.5");
        movies.add(movie);
        movie = new Movie("","Title 5","This is desc 5","2020-10-11","6.5");
        movies.add(movie);
        movie = new Movie("","Title 6","This is desc 6","2020-10-11","1.5");
        movies.add(movie);
        movie = new Movie("","Title 2","This is desc 2","2020-10-11","8.5");
        movies.add(movie);
        movie = new Movie("","Title 3","This is desc 3","2020-10-11","3.5");
        movies.add(movie);
        movie = new Movie("","Title 4","This is desc 4","2020-10-11","5.5");
        movies.add(movie);
        movie = new Movie("","Title 5","This is desc 5","2020-10-11","6.5");
        movies.add(movie);
        movie = new Movie("","Title 6","This is desc 6","2020-10-11","1.5");
        movies.add(movie);
    }

}