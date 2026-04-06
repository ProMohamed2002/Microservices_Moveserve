package com.moviecatalogservice;

import com.example.trendingmovies.TrendingMoviesGrpc;
import com.example.trendingmovies.TrendingMoviesProto;
import com.example.trendingmovies.TrendingMoviesProto.TrendingMoviesResponse;
import com.moviecatalogservice.models.MovieRating;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GrpcClient {
    private final TrendingMoviesGrpc.TrendingMoviesBlockingStub trendingServiceStub;

    public GrpcClient(
            @Value("${grpc.billing.service.host:localhost}") String host,
            @Value("${grpc.billing.service.port:9091}") int port
    ) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        this.trendingServiceStub = TrendingMoviesGrpc.newBlockingStub(channel);
    }

    public List<MovieRating> getTrendyMovies() {
        TrendingMoviesProto.Empty request = TrendingMoviesProto.Empty.newBuilder().build();
        TrendingMoviesResponse response = trendingServiceStub.getTrendingMovies(request);

        return response.getMoviesList().stream()
                .map(movie -> new MovieRating(
                        movie.getId(),
                        movie.getRating()
                )).collect(Collectors.toList());
    }
}
