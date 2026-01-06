package com.ra.controller;


import com.ra.model.dto.MovieDTO;
import com.ra.model.entity.Movie;
import com.ra.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies() , HttpStatus.OK);
    }

    // ResponseEntity<?> ở đây có nghĩa là chưa xác định hoặc chưa biết trước kiểu dữ liệu trả về
    @PostMapping("/add")
    public ResponseEntity<?> addMovie(@RequestBody MovieDTO movieDTO) {
        Movie movie = movieService.addMovie(movieDTO);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("add movie failed", HttpStatus.BAD_REQUEST);
        }
    }

    // ResponseEntity<?> ở đây có nghĩa là chưa xác định hoặc chưa biết trước kiểu dữ liệu trả về
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        Movie updatedMovie = movieService.updateMovie(id, movieDTO);
        if (updatedMovie != null) {
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("update movie failed", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        boolean rs =  movieService.deleteMovie(id);
        if (rs) {
            return new ResponseEntity<>("delete movie successful", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("delete movie failed", HttpStatus.BAD_REQUEST);
        }
    }
}
