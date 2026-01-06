package com.ra.controller;

import com.ra.model.dto.CinemaDTO;
import com.ra.model.entity.Cinema;
import com.ra.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinameController {
    @Autowired
    private CinemaService cinemaService;

    @GetMapping
    public ResponseEntity<List<Cinema>> getAllCinemas() {
        return new ResponseEntity<>(cinemaService.getAllCinemas(), HttpStatus.OK);
    }

    // ResponseEntity<?> ở đây có nghĩa là chưa xác định hoặc chưa biết trước kiểu dữ liệu trả về
    @PostMapping("/add")
    public ResponseEntity<?> addCinema(@RequestBody CinemaDTO cinemaDTO) {
        Cinema cinema =  cinemaService.addCinema(cinemaDTO);
        if (cinema != null) {
            return new ResponseEntity<>(cinema, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("add new cinema failed", HttpStatus.BAD_REQUEST);
        }
    }

    // ResponseEntity<?> ở đây có nghĩa là chưa xác định hoặc chưa biết trước kiểu dữ liệu trả về
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateCinema(@PathVariable Long id, @RequestBody CinemaDTO cinemaDTO) {
        Cinema updatedCinema = cinemaService.updateCinema(id, cinemaDTO);
        if (updatedCinema != null) {
            return new ResponseEntity<>(updatedCinema, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("update cinema failed", HttpStatus.BAD_REQUEST);

        }    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCinema(@PathVariable Long id) {
        boolean rs = cinemaService.deleteCinema(id);
        if (rs){
            return new ResponseEntity<>("delete cinema successful", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("delete cinema failed", HttpStatus.BAD_REQUEST);
        }
    }
}
