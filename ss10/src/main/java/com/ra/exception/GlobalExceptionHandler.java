package com.ra.exception;

import com.ra.model.dto.response.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.View;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // xu ly ngoai le cua CustomHandlerException
    @ExceptionHandler(CustomHandlerException.class)
    public ResponseEntity<String> customHandlerException(CustomHandlerException ex, HttpStatus status){
        return new  ResponseEntity<>(ex.getMessage(), status);
    }

    // xu ly ngoai le cho NoSuchElementException
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementException(NoSuchElementException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // xu ly ngoai le cho HttpServerErrorException
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> httpServerErrorException(HttpServerErrorException ex){
        return new  ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // xu ly ngoai le cho MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex){
        List<String> errorMessages = ex.getBindingResult().getFieldError().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return new  ResponseEntity.badRequest().body(DataResponse.<List<String>>builder())
                .status(400)
                .message("Loi validation")
                .data(errorMessages)
                .build();
    }
}
