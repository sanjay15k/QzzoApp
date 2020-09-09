package com.qzzo.android.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.potyvideo.library.AndExoPlayerView;
import com.qzzo.android.R;

import org.jetbrains.annotations.NotNull;

public class TrailerListViewHolder extends RecyclerView.ViewHolder {

    public AndExoPlayerView videoPlayer;

    public TrailerListViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        this.videoPlayer = itemView.findViewById(R.id.videoPlayer);
    }
}
