package com.qzzo.android.ui;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.qzzo.android.BaseActivity;
import com.qzzo.android.R;
import com.qzzo.android.adapters.MovieListRecyclerViewAdapter;
import com.qzzo.android.models.Movie;
import com.takusemba.multisnaprecyclerview.MultiSnapHelper;
import com.takusemba.multisnaprecyclerview.SnapGravity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setTheme(R.style.Theme_QzzoApp);
        setContentView(R.layout.activity_main);

        RecyclerView movieListRecyclerView = findViewById(R.id.movieListRecyclerView);

        List<Movie> movies = new ArrayList<>();
        initMovieList(movies);

        MovieListRecyclerViewAdapter adapter = new MovieListRecyclerViewAdapter(movies);
        movieListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieListRecyclerView.setAdapter(adapter);

        MultiSnapHelper snapHelper = new MultiSnapHelper(SnapGravity.CENTER, 1, 100);
        snapHelper.attachToRecyclerView(movieListRecyclerView);

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