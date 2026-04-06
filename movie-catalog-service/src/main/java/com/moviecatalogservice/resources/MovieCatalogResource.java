package com.moviecatalogservice.resources;

import com.moviecatalogservice.GrpcClient;
import com.moviecatalogservice.models.*;
import com.moviecatalogservice.services.MovieInfoService;
import com.moviecatalogservice.services.UserRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@Slf4j
public class MovieCatalogResource {

    private final RestTemplate restTemplate;

    private final MovieInfoService movieInfoService;

    private final UserRatingService userRatingService;
    private final GrpcClient grpcClient;

    public MovieCatalogResource(RestTemplate restTemplate,
                                MovieInfoService movieInfoService,
                                UserRatingService userRatingService, GrpcClient grpcClient) {

        this.restTemplate = restTemplate;
        this.movieInfoService = movieInfoService;
        this.userRatingService = userRatingService;
        this.grpcClient = grpcClient;
    }

    /**
     * Makes a call to MovieInfoService to get movieId, name and description,
     * Makes a call to RatingsService to get ratings
     * Accumulates both data to create a MovieCatalog
     * @param userId
     * @return CatalogItem that contains name, description and rating
     */
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        List<Rating> ratings = userRatingService.getUserRating(userId).getRatings();
        return ratings.stream().map(movieInfoService::getCatalogItem).collect(Collectors.toList());
    }
    @GetMapping("/trendy")
    public List<MovieRating> getTrendyMovies() {
        log.info("Received request for trendy movies");
        return grpcClient.getTrendyMovies();
        }
    }


