package com.qzzo.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    @Expose
    @SerializedName("id")
    private String movieId;
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

    public Movie(String movieId, String posterUrl, String title, String description, String releaseDate, String rating) {
        this.movieId = movieId;
        this.posterUrl = posterUrl;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    protected Movie(Parcel in) {
        movieId = in.readString();
        posterUrl = in.readString();
        title = in.readString();
        description = in.readString();
        releaseDate = in.readString();
        rating = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieId);
        dest.writeString(posterUrl);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(releaseDate);
        dest.writeString(rating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
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
