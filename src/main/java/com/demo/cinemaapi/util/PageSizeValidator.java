package com.demo.cinemaapi.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PageSizeValidator {
    private static final Integer MAX_PAGE_SIZE = 1000;

    public static void validatePageSize(Integer size) {
        if (size > MAX_PAGE_SIZE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page size must be less than " + MAX_PAGE_SIZE);
        }
    }
}
