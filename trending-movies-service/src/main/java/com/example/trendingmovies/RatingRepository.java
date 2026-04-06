package com.example.trendingmovies;

import com.example.trendingmovies.model.MovieRating;
import com.example.trendingmovies.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT new com.example.trendingmovies.model.MovieRating(r.movieId, AVG(r.rating)) " +
            "FROM Rating r " +
            "GROUP BY r.movieId " +
            "ORDER BY AVG(r.rating) DESC")
    List<MovieRating> findTop10MoviesByAverageRating();
}