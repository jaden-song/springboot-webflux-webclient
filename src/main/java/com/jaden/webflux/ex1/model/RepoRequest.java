package com.jaden.webflux.ex1.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepoRequest {

    private String name;

    private String description;

    @JsonProperty("private")
    private Boolean isPrivate;

    public RepoRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
