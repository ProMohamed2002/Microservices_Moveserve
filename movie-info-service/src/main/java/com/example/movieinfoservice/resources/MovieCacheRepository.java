package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.MovieSummary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieCacheRepository extends MongoRepository<MovieSummary, String> {


}