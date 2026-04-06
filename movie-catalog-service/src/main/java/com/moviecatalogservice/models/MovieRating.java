package com.moviecatalogservice.models;

public class MovieRating {
    private String  movieId;
    private Double averageRating;

    public MovieRating(String movieId, Double averageRating) {
        this.movieId = movieId;
        this.averageRating = averageRating;
    }

    public String getMovieId() {
        return movieId;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
