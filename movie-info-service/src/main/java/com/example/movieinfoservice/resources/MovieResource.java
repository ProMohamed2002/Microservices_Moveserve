package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    private RestTemplate restTemplate;
    private MovieCacheRepository movieCacheRepository;

    public MovieResource(RestTemplate restTemplate, MovieCacheRepository movieCacheRepository) {
        this.restTemplate = restTemplate;
        this.movieCacheRepository = movieCacheRepository;
    }


    @GetMapping("noCache/{movieId}")
    public Movie getMovieInfoWithoutCache(@PathVariable("movieId") String movieId) {
        // Get the movie info from TMDB
        final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
        MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);

        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }


    @GetMapping("cached/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        // Get the movie info from TMDB
        final String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;

        Optional<MovieSummary> cachedMovie = movieCacheRepository.findById(movieId);

        MovieSummary movieSummary = null;

        if (cachedMovie.isPresent()) {
            movieSummary = cachedMovie.get();
            log.info("Movie info for movieId {} found in cache", movieId);
        } else {
            movieSummary = restTemplate.getForObject(url, MovieSummary.class);
            movieCacheRepository.save(movieSummary);
        }
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());

    }
}
