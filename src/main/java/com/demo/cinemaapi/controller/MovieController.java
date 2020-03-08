package com.demo.cinemaapi.controller;

import com.demo.cinemaapi.model.Movie;
import com.demo.cinemaapi.repository.MovieRepository;
import com.demo.cinemaapi.util.PageSizeConstants;
import com.demo.cinemaapi.util.PageSizeValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies")
    public List<Movie> getMovies(@RequestParam(required = false) String query,
                                 @RequestParam(defaultValue = PageSizeConstants.DEFAULT_PAGE) Integer page,
                                 @RequestParam(defaultValue = PageSizeConstants.DEFAULT_PAGE_SIZE) Integer size) {
        PageSizeValidator.validatePageSize(size);
        return movieRepository.findByTitleContains(query, PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/movies/{movieId}")
    public Movie getMovie(@PathVariable String movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
