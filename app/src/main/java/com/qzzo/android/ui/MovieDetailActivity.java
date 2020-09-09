package com.qzzo.android.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.qzzo.android.BaseActivity;
import com.qzzo.android.R;
import com.qzzo.android.adapters.MovieRecyclerViewAdapter;
import com.qzzo.android.listener.CreateApiCall;
import com.qzzo.android.models.Movie;
import com.qzzo.android.models.Trailer;
import com.qzzo.android.utils.Constants;
import com.qzzo.android.utils.GlideUtils;
import com.qzzo.android.viewmodels.HomeScreenViewModel;
import com.qzzo.android.viewmodels.MovieDetailViewModel;

import java.util.List;
import java.util.Objects;

public class MovieDetailActivity extends BaseActivity implements CreateApiCall {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView trailerListRecyclerView;
    private MovieDetailViewModel movieDetailViewModel;
    private LiveData<List<Trailer>> trailerList;
    private Movie movie;

    @Override
    public void onBackPressed() {
        movieDetailViewModel.onBackPressed(true);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_movie_detail);

        movie = getIntent().getParcelableExtra("movie");

        movieDetailViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MovieDetailViewModel.class);

        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        trailerListRecyclerView = findViewById(R.id.trailerListRecyclerView);
        ImageView moviePosterExpandedIv = findViewById(R.id.moviePosterExpandedIv);
        TextView ratingTv = findViewById(R.id.ratingTv);
        TextView detailedDescTv = findViewById(R.id.detailedDescTv);

        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

        String posterURL = Constants.IMAGE_BASE_PATH + movie.getPosterUrl();
        GlideUtils.loadImageFromUrl(this, moviePosterExpandedIv, posterURL, -1);
        collapsingToolbarLayout.setTitle(movie.getTitle());
        detailedDescTv.setText(movie.getDescription());
        ratingTv.setText(movie.getRating());


        initRecyclerView();
        subscribeObserver();
        sendApiRequest();
    }

    private void subscribeObserver(){
        trailerList = movieDetailViewModel.getTrailers();
        if(trailerList.getValue() != null) {
            trailerList.getValue().clear();
        }
        System.out.println("Movie List initially : "+trailerList);
        trailerList.observe(this, this::addDataToRecyclerView);
    }

    @Override
    public void sendApiRequest() {
        new Handler().postDelayed(() -> movieDetailViewModel.getTrailerList(movie.getMovieId()), 0);
        System.out.println("List initially : "+ ((MovieRecyclerViewAdapter) Objects.requireNonNull(trailerListRecyclerView.getAdapter())).getMovies());
        showRetryButton(false, null);
        showProgressBar(true);
    }

    private void addDataToRecyclerView(List<Trailer> trailerList){
        if(trailerList !=null && trailerList.size()>0) {
            ((MovieRecyclerViewAdapter) Objects.requireNonNull(trailerListRecyclerView.getAdapter())).setTrailers(trailerList);
            System.out.println("Trailer List Finally : "+trailerList);
            showProgressBar(false);
        }
    }

    private void initRecyclerView(){
        MovieRecyclerViewAdapter adapter = new MovieRecyclerViewAdapter(1);
        trailerListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        trailerListRecyclerView.setAdapter(adapter);
//        MultiSnapHelper snapHelper = new MultiSnapHelper(SnapGravity.CENTER, 2, 100);
//        snapHelper.attachToRecyclerView(movieListRecyclerView);
    }

}