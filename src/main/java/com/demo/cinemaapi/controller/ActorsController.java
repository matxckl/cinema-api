package com.demo.cinemaapi.controller;

import com.demo.cinemaapi.model.Actor;
import com.demo.cinemaapi.model.AppearanceConverter;
import com.demo.cinemaapi.model.AppearanceDTO;
import com.demo.cinemaapi.repository.ActorRepository;
import com.demo.cinemaapi.repository.AppearanceRepository;
import com.demo.cinemaapi.util.PageSizeConstants;
import com.demo.cinemaapi.util.PageSizeValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ActorsController {

    private final ActorRepository actorRepository;
    private final AppearanceRepository appearanceRepository;

    public ActorsController(ActorRepository actorRepository, AppearanceRepository appearanceRepository) {
        this.actorRepository = actorRepository;
        this.appearanceRepository = appearanceRepository;
    }

    @GetMapping("/actors")
    public List<Actor> getActors(@RequestParam(defaultValue = "") String query,
                                 @RequestParam(defaultValue = PageSizeConstants.DEFAULT_PAGE) Integer page,
                                 @RequestParam(defaultValue = PageSizeConstants.DEFAULT_PAGE_SIZE) Integer size) {
        PageSizeValidator.validatePageSize(size);
        return actorRepository.findByNameContains(query, PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/actors/{actorId}")
    public Actor getActor(@PathVariable Integer actorId) {
        return actorRepository.findById(actorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/actors/{actorId}/appearances")
    public List<AppearanceDTO> getAppearances(@PathVariable Integer actorId,
                                              @RequestParam(defaultValue = PageSizeConstants.DEFAULT_PAGE) Integer page,
                                              @RequestParam(defaultValue = PageSizeConstants.DEFAULT_PAGE_SIZE) Integer size) {
        PageSizeValidator.validatePageSize(size);
        return appearanceRepository.findByActorId(actorId, PageRequest.of(page, size)).getContent().stream()
                .map(AppearanceConverter::toDTO)
                .collect(Collectors.toList());
    }
}
