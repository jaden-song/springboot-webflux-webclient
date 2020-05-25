package com.jaden.webflux.ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieClientServiceImplTest {
    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        webTestClient = webTestClient
                .mutate()
                .responseTimeout(Duration.ofMillis(36000))
                .build();
    }

    @Test
    public void testGetMovieById() {
        webTestClient.get()
                .uri("/api/v1/movies/id/{imdbId}", "tt3896198")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response -> Assertions.assertNotNull(response.getResponseBody()));
    }

    @Test
    public void testGetMovieByName() {
        webTestClient.get()
                .uri("/api/v1/movies/title/{name}", "Superman")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response -> Assertions.assertNotNull(response.getResponseBody()));
    }
}
