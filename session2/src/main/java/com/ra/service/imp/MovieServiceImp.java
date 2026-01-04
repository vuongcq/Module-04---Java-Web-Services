package com.ra.service.imp;

import com.ra.model.Movie;
import com.ra.model.dto.MovieResponseDTO;
import com.ra.repository.MovieRepository;
import com.ra.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImp implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Page<MovieResponseDTO> getMovies(Pageable pageable) {
        Page<Movie> movies = movieRepository.findAll(pageable);
        return movies.map(movie -> MovieResponseDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating()).build());
    }

    @Override
    public MovieResponseDTO saveMovie(Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return MovieResponseDTO.builder()
                .id(savedMovie.getId())
                .title(savedMovie.getTitle())
                .director(savedMovie.getDirector())
                .releaseDate(savedMovie.getReleaseDate())
                .rating(savedMovie.getRating()).build();
    }

    @Override
    public Boolean isExistsMovie(Long id) {
        return null;
    }

    @Override
    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteMovie(Long id) {
        if (findMovieById(id) != null) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public MovieResponseDTO updateMovie(Long id, Movie movie) {
        if (findMovieById(id) != null) {
            Movie updatedMovie = movieRepository.save(movie);
            return MovieResponseDTO.builder()
                    .build();
        }
        return null;
    }
}
