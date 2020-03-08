package com.demo.cinemaapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonProperty("id")
    private Integer movieId;
    private String title;
    private String year;

    public Movie(){
    }

    public Movie(Integer movieId, String title, String year) {
        this.movieId = movieId;
        this.title = title;
        this.year = year;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public String getName() {
        return title;
    }

    public String getYear() {
        return year;
    }
}
