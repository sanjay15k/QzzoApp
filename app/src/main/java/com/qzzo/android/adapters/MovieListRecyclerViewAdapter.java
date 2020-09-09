package com.qzzo.android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qzzo.android.R;
import com.qzzo.android.models.Movie;
import com.qzzo.android.utils.GlideUtils;
import com.qzzo.android.viewholders.MovieListViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int MOVIE_LIST_VIEW_TYPE = 0;
    private List<Movie> movies;

    public MovieListRecyclerViewAdapter(List<Movie> movies){
        this.movies = movies;
    }

    @NonNull
    @NotNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        System.out.println(viewType);
        if (viewType == MOVIE_LIST_VIEW_TYPE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout, parent, false);
            return new MovieListViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MovieListViewHolder){
            MovieListViewHolder movieListViewHolder = (MovieListViewHolder) holder;
            Movie movie = movies.get(position);
//            GlideUtils.loadImageFromUrl(movieListViewHolder.moviePosterIv.getContext(), movieListViewHolder.moviePosterIv, movie.getPosterUrl(), R.drawable.ic_launcher_background);
            movieListViewHolder.titleTv.setText(movie.getTitle());
            movieListViewHolder.descTv.setText(movie.getDescription());
            movieListViewHolder.releaseDateTv.setText(movie.getReleaseDate());
            movieListViewHolder.ratingTv.setText(movie.getRating());
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
