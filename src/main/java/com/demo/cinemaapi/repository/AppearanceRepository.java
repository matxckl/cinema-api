package com.demo.cinemaapi.repository;

import com.demo.cinemaapi.model.Appearance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppearanceRepository extends PagingAndSortingRepository<Appearance, Integer> {
    Page<Appearance> findByActorId(Integer actorId, Pageable pageable);
}
