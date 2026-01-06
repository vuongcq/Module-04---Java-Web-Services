package com.ra.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponse {
    private long id ;
    private String salesAgent ;
    private double totalAmount ;
    Map<String, Integer> foods ;
    Map<String, Integer> tickets ;
    private LocalDateTime orderDate ;
}
