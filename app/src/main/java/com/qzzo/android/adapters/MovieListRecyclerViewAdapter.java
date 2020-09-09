package com.qzzo.android.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qzzo.android.R;
import com.qzzo.android.models.Movie;
import com.qzzo.android.utils.Constants;
import com.qzzo.android.utils.GlideUtils;
import com.qzzo.android.viewholders.MovieListViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int MOVIE_LIST_VIEW_TYPE = 0;
    private List<Movie> movies;

    public MovieListRecyclerViewAdapter(){}

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
            String posterPath = Constants.IMAGE_BASE_PATH + movie.getPosterUrl();
            GlideUtils.loadImageFromUrl(movieListViewHolder.moviePosterIv.getContext(), movieListViewHolder.moviePosterIv, posterPath, R.drawable.default_movie_poster);
            movieListViewHolder.titleTv.setText(movie.getTitle());
            movieListViewHolder.descTv.setText(movie.getDescription());
            movieListViewHolder.releaseDateTv.setText(Html.fromHtml("<b>Release Date : </b>"+movie.getReleaseDate()));
            movieListViewHolder.ratingTv.setText(movie.getRating());
        }
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
