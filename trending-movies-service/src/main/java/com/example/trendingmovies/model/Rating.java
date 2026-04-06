package com.example.trendingmovies.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;   // NEW field
    private String movieId;
    private int rating;

    public Rating() {}

    public Rating(String userId, String movieId, int rating) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
    }

    public Long getId() { return id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) { this.movieId = movieId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
}