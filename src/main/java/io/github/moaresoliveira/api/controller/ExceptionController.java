package io.github.moaresoliveira.api.controller;

import feign.FeignException;
import io.github.moaresoliveira.api.exception.ClienteNotFoundException;
import io.github.moaresoliveira.api.exception.EnderecoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({ClienteNotFoundException.class, EnderecoNotFoundException.class})
    public ResponseEntity<String> handleClienteNotFoundException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleFeignException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
