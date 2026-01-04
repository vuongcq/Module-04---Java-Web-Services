package com.ra.controller;

import com.ra.model.dto.MovieResponseDTO;
import com.ra.service.MovieService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<?> getAllMovies
            (@PageableDefault(page = 0, size = 3, sort = "id", direction = Sort.Direction.ASC) @RequestParam Pageable pageable) {
        Page<MovieResponseDTO> movieResponseDTOs = movieService.getMovies(pageable);
        return ResponseEntity.ok(movieResponseDTOs);
    }
}
