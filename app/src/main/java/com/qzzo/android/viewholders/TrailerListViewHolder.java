package com.qzzo.android.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.qzzo.android.R;

import org.jetbrains.annotations.NotNull;

public class TrailerListViewHolder extends RecyclerView.ViewHolder {

    public YouTubePlayerView videoPlayer;

    public TrailerListViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        this.videoPlayer = itemView.findViewById(R.id.videoPlayer);
    }
}
