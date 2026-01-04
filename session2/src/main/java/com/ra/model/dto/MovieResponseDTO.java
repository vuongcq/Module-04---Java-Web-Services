package com.ra.model.dto;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieResponseDTO {
    private Long id;
    private String title;
    private String director;
    private LocalDate releaseDate;
    private Double rating;
}
