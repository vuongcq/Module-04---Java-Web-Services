package com.ra.service;

import com.ra.model.Movie;
import com.ra.model.dto.MovieResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    Page<MovieResponseDTO> getMovies(Pageable pageable);
    MovieResponseDTO saveMovie (Movie movie);
    Boolean isExistsMovie(Long id);
    Movie findMovieById(Long id);
    Boolean deleteMovie(Long id);
    MovieResponseDTO updateMovie (Long id, Movie movie);
}
