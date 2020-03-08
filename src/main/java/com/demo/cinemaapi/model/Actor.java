package com.demo.cinemaapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonProperty("id")
    private Integer actorId;
    private String name;

    public Actor(){
    }

    public Actor(Integer actorId, String name) {
        this.actorId = actorId;
        this.name = name;
    }

    public Integer getActorId() {
        return actorId;
    }

    public String getName() {
        return name;
    }
}
