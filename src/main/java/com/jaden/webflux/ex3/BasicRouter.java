package com.jaden.webflux.ex3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BasicRouter {
    private final BasicService basicService;

    public BasicRouter(BasicService basicService) {
        this.basicService = basicService;
    }

    @Bean
    RouterFunction<ServerResponse> empRouterList() {
        return route()
                .GET("/reactive-list", serverRequest ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(basicService.findReactorList(), String.class))
                .GET("/normal-list", serverRequest ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                .body(basicService.findNormalList(), String.class))
                .GET("/load", serverRequest -> {
                    basicService.loadData();
                    return ServerResponse.ok().body(BodyInserters.fromValue("Load Data Completed"));
                })
                .build();
    }
}

