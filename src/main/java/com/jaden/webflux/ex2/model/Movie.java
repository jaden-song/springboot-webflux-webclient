package com.jaden.webflux.ex2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    @JsonProperty("Title")
    private String movieTitle;

    @JsonProperty("Year")
    private String releaseYear;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Poster")
    private String posterUrl;
}
