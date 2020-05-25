package com.jaden.webflux.ex2.service;

import com.jaden.webflux.ex2.model.Movie;
import reactor.core.publisher.Mono;

public interface MovieClientService {
    Mono<Movie> searchMovieByTitle(String apiKey, String title);
    Mono<Movie> searchMovieById(String apiKey, String imdbId);
}
