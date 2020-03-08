package com.demo.cinemaapi.model;

import org.springframework.stereotype.Component;

@Component
public class AppearanceConverter {
    public static AppearanceDTO toDTO(Appearance appearance) {
        if (appearance == null) {
            return null;
        }
        String characterName = appearance.getCharacterName();
        Movie movie = appearance.getMovie();
        Integer movieId = null;
        String movieName = null;
        if (movie != null) {
            movieId = movie.getMovieId();
            movieName = movie.getName();
        }
        return new AppearanceDTO(characterName, movieId, movieName);
    }
}
