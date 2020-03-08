package com.demo.cinemaapi.repository;

import com.demo.cinemaapi.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer> {
    Page<Movie> findByTitleContains(String query, Pageable pageable);
}
