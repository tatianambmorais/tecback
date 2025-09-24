package br.uniesp.si.techback.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){
        Map<String, String> erros =  new HashMap<>();
        for(FieldError fe : ex.getBindingResult().getFieldErrors()){
            erros.put(fe.getField(), fe.getDefaultMessage());
        }
        return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}