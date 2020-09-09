package com.qzzo.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.qzzo.android.ui.MovieDetailActivity;
import com.qzzo.android.R;
import com.qzzo.android.models.Movie;
import com.qzzo.android.models.Trailer;
import com.qzzo.android.utils.Constants;
import com.qzzo.android.utils.GlideUtils;
import com.qzzo.android.viewholders.MovieListViewHolder;
import com.qzzo.android.viewholders.TrailerListViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int MOVIE_LIST_VIEW_TYPE = 0;
    private static final int TRAILER_LIST_VIEW_TYPE = 1;
    private int viewTag;
    private List<Movie> movies;
    private List<Trailer> trailers;

    public MovieRecyclerViewAdapter(int viewTag){
        this.viewTag = viewTag;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        System.out.println(viewType);
        if (viewTag == MOVIE_LIST_VIEW_TYPE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout, parent, false);
            return new MovieListViewHolder(view);
        }
        if (viewTag == TRAILER_LIST_VIEW_TYPE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item_layout, parent, false);
            return new TrailerListViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MovieListViewHolder){
            MovieListViewHolder movieListViewHolder = (MovieListViewHolder) holder;
            Movie movie = movies.get(position);
            Context context = movieListViewHolder.moviePosterIv.getContext();
            String posterPath = Constants.IMAGE_BASE_PATH + movie.getPosterUrl();
            GlideUtils.loadImageFromUrl(context, movieListViewHolder.moviePosterIv, posterPath, R.drawable.default_movie_poster);
            movieListViewHolder.titleTv.setText(movie.getTitle());
            movieListViewHolder.descTv.setText(movie.getDescription());
            movieListViewHolder.releaseDateTv.setText(Html.fromHtml("<b>Release Date : </b>"+movie.getReleaseDate()));
            movieListViewHolder.ratingTv.setText(movie.getRating());

            movieListViewHolder.moviePosterIv.setOnClickListener(view -> {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("movie", movie);
                context.startActivity(intent);
            });
        }
        else if(holder instanceof TrailerListViewHolder){
            TrailerListViewHolder trailerListViewHolder = (TrailerListViewHolder) holder;
            Trailer trailer = trailers.get(position);
            String trailerURL = Constants.TRAILER_BASE_PATH + trailer.getKey();
            System.out.println("PATH IS : "+trailerURL);
            trailerListViewHolder.videoPlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                    youTubePlayer.loadVideo(trailerURL, 0);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(viewTag == MOVIE_LIST_VIEW_TYPE) return movies == null ? 0 : movies.size();
        if(viewTag == TRAILER_LIST_VIEW_TYPE) return trailers == null ? 0 : trailers.size();
        return 0;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        System.out.println("Data : "+trailers.toString());
        notifyDataSetChanged();
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
