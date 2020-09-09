package com.qzzo.android.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qzzo.android.R;

import org.jetbrains.annotations.NotNull;

public class MovieListViewHolder extends RecyclerView.ViewHolder {

    public ImageView moviePosterIv;
    public TextView titleTv;
    public TextView descTv;
    public TextView releaseDateTv;
    public TextView ratingTv;

    public MovieListViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        this.moviePosterIv = itemView.findViewById(R.id.moviePosterIv);
        this.titleTv = itemView.findViewById(R.id.titleTv);
        this.descTv = itemView.findViewById(R.id.descTv);
        this.releaseDateTv = itemView.findViewById(R.id.releaseDateTv);
        this.ratingTv = itemView.findViewById(R.id.ratingTv);
    }
}
