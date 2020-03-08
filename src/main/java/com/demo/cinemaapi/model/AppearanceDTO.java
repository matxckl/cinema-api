package com.demo.cinemaapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppearanceDTO {
    @JsonProperty("character_name")
    private String characterName;
    @JsonProperty("movie_id")
    private Integer movieId;
    @JsonProperty("movie_name")
    private String movieName;

    public AppearanceDTO() {
    }

    public AppearanceDTO(String characterName, Integer movieId, String movieName) {
        this.characterName = characterName;
        this.movieId = movieId;
        this.movieName = movieName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }
}
