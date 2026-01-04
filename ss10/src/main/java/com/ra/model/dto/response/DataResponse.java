package com.ra.model.dto.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DataResponse<T> {
    private T data;
    private String message;
    private int status;
}
