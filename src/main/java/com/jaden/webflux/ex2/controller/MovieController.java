package com.jaden.webflux.ex2.controller;

import com.jaden.webflux.ex2.model.Movie;
import com.jaden.webflux.ex2.service.MovieClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    private final MovieClientService movieClientService;
    private final Environment env;

    public MovieController(MovieClientService movieClientService, Environment env) {
        this.movieClientService = movieClientService;
        this.env = env;
    }

    @GetMapping("/movies/title/{name}")
    public Mono<Movie> getMovieByTitle(@PathVariable String name) {
        return movieClientService.searchMovieByTitle(env.getProperty("app.api.key"), name);
    }

    @GetMapping("/movies/id/{imdbId}")
    public Mono<Movie> getMovieById(@PathVariable String imdbId) {
        return movieClientService.searchMovieById(env.getProperty("app.api.key"), imdbId);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        logger.error("Error from WebClient - Status {}, Body {}", ex.getRawStatusCode(),
                ex.getResponseBodyAsString(), ex);
        return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
    }
}
