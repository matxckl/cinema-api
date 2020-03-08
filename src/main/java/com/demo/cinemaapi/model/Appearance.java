package com.demo.cinemaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Appearance {

    @Id
    private Long index;
    private Integer actorId;
    private String characterName;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Appearance() {
    }

    public Appearance(Long index, Integer actorId, String characterName, Movie movie) {
        this.index = index;
        this.actorId = actorId;
        this.characterName = characterName;
        this.movie = movie;
    }

    public Long getIndex() {
        return index;
    }

    public Integer getActorId() {
        return actorId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public Movie getMovie() {
        return movie;
    }
}
