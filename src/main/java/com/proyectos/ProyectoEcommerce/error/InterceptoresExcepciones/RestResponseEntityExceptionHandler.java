package com.proyectos.ProyectoEcommerce.error.InterceptoresExcepciones;

import com.proyectos.ProyectoEcommerce.error.dto.ErrorMessage;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


//@ControllerAdvice
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DataIntegrityViolationException.class/*, IllegalArgumentException.class, ValidationException.class*/})
    public ResponseEntity<?> handleDataIntegrity(DataIntegrityViolationException exception) {

        ErrorMessage errorMessage = new ErrorMessage("BAD_REQUEST", exception.getMessage(), new Date());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        /*
        Map<String, Object> errors = new HashMap<>();

        errors.put("status", "BAD_REQUEST");
*/
       Map<String, Object> message = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            //message.put(error.getField(), error.getDefaultMessage());
        });
/*
        errors.put("message", message);

        errors.put("fecha", new Date());
*/
        ErrorMessage nuevoError = new ErrorMessage("BAD_REQUEST", message, new Date());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nuevoError);
    }

}
