package com.example.trendingmovies.grpc;

import com.example.trendingmovies.RatingRepository;

import com.example.trendingmovies.TrendingMoviesGrpc;
import com.example.trendingmovies.TrendingMoviesProto;
import com.example.trendingmovies.model.MovieRating;
import net.devh.boot.grpc.server.service.GrpcService;
import io.grpc.stub.StreamObserver;

import java.util.List;

@GrpcService
public class TrendingMoviesGrpcService extends TrendingMoviesGrpc.TrendingMoviesImplBase {

    private final RatingRepository ratingRepository;

    public TrendingMoviesGrpcService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void getTrendingMovies(TrendingMoviesProto.Empty request,
                                  StreamObserver<TrendingMoviesProto.TrendingMoviesResponse> responseObserver) {

        List<MovieRating> topMovies = ratingRepository.findTop10MoviesByAverageRating();

        TrendingMoviesProto.TrendingMoviesResponse.Builder responseBuilder =
                TrendingMoviesProto.TrendingMoviesResponse.newBuilder();

        for (MovieRating movieRating : topMovies) {
            TrendingMoviesProto.Movie grpcMovie = TrendingMoviesProto.Movie.newBuilder()
                    .setId(movieRating.getMovieId())
                    .setRating(movieRating.getAverageRating())
                    .build();
            responseBuilder.addMovies(grpcMovie);
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}