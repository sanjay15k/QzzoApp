package com.qzzo.android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @Expose
    @SerializedName("poster_path")
    private String posterUrl;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("overview")
    private String description;
    @Expose
    @SerializedName("release_date")
    private String releaseDate;
    @Expose
    @SerializedName("vote_average")
    private String rating;

    public Movie(String posterUrl, String title, String description, String releaseDate, String rating) {
        this.posterUrl = posterUrl;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
