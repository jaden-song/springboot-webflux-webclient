package com.jaden.webflux.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Github github = new Github();

    @Getter
    @Setter
    public static class Github {
        private String username;
        private String token;
    }

    public Github getGithub() {
        return github;
    }
}
