package com.ra.service;

import com.ra.model.dto.MovieDTO;
import com.ra.model.entity.Movie;
import com.ra.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public Movie addMovie(MovieDTO movieDTO) {
        try {
            return movieRepository.save(convertMovieDTOToMovie(movieDTO));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Movie updateMovie(Long id, MovieDTO movieDTO) {
        Movie movie = getMovieById(id);
        if (movie != null) {
            Movie newMovie = convertMovieDTOToMovie(movieDTO);
            newMovie.setId(id);
            try {
                return movieRepository.save(newMovie);

            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }

    public boolean deleteMovie(Long id) {
        Movie movie = getMovieById(id);
        if (movie != null) {
            try {
                movieRepository.delete(movie);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else {
            return false;
        }
    }

    public Movie convertMovieDTOToMovie(MovieDTO movieDTO) {
        return Movie
                .builder()
                .description(movieDTO.getDescription())
                .duration(movieDTO.getDuration())
                .title(movieDTO.getTitle())
                .build();
    }
}
