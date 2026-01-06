package com.ra.service;

import com.ra.model.dto.CinemaDTO;
import com.ra.model.entity.Cinema;
import com.ra.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    public Cinema getCinemaById(Long id) {
        return cinemaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Cinema not found"));
    }

    public Cinema addCinema(CinemaDTO cinemaDTO) {
        try {
            return cinemaRepository.save(convertCinemaDTOToCinema(cinemaDTO));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Cinema updateCinema(Long id, CinemaDTO cinemaDTO) {
        Cinema cinema = getCinemaById(id);
        if (cinema != null) {
            Cinema newCinema = convertCinemaDTOToCinema(cinemaDTO);
            newCinema.setId(id);
            try {
                return cinemaRepository.save(newCinema);
            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null ;
        }
    }

    public boolean deleteCinema(Long id) {
        Cinema cinema = getCinemaById(id);
        if (cinema != null) {
            try {
                cinemaRepository.delete(cinema);
                return true;
            }catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else {
            return false;
        }

    }

    public Cinema convertCinemaDTOToCinema(CinemaDTO cinemaDTO) {
        return Cinema
                .builder()
                .capacity(cinemaDTO.getCapacity())
                .name(cinemaDTO.getName())
                .location(cinemaDTO.getLocation())
                .build();
    }
}
