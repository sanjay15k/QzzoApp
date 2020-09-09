package com.qzzo.android.models;

public class Movie {

    private String posterUrl;
    private String title;
    private String description;
    private String releaseDate;
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
