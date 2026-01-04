package com.ra.repository;

import com.ra.model.Movie;
import com.ra.model.dto.MovieResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findAll(Pageable pageable);
    Boolean existsByTitle(String title);

}
