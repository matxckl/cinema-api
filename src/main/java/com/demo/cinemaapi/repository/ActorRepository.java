package com.demo.cinemaapi.repository;

import com.demo.cinemaapi.model.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActorRepository extends PagingAndSortingRepository<Actor, Integer> {
    Page<Actor> findByNameContains(String query, Pageable pageable);
}
