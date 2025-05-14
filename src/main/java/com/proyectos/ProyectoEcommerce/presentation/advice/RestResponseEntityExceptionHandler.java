package com.proyectos.ProyectoEcommerce.presentation.advice;

import com.proyectos.ProyectoEcommerce.service.exception.*;
import com.proyectos.ProyectoEcommerce.presentation.Response.ApiResponse;
import com.proyectos.ProyectoEcommerce.presentation.Response.CustomResponseBuilder;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Hidden
@RestControllerAdvice
//@ControllerAdvice
public class RestResponseEntityExceptionHandler /*extends ResponseEntityExceptionHandler*/ {

    private final CustomResponseBuilder customResponseBuilder;

    @Autowired
    public RestResponseEntityExceptionHandler(CustomResponseBuilder customResponseBuilder) {
        this.customResponseBuilder = customResponseBuilder;
    }

    //, IllegalArgumentException.class, ValidationException.class
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<?> handleDataIntegrity(DataIntegrityViolationException e, WebRequest webRequest) {

        ApiResponse<String> apiResponse = new ApiResponse<>(new Date(), HttpStatus.BAD_REQUEST, e.getMessage(), webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler({UserException.class, ProveedorException.class, ProductoException.class, OrdenVentaException.class, DetalleUsuarioException.class, CarritoException.class})
    public ResponseEntity<?> handleDataIntegrity(RuntimeException e, WebRequest webRequest) {

        //ApiResponse<String> apiResponse = new ApiResponse<>(new Date(), HttpStatus.BAD_REQUEST, e.getMessage(), webRequest.getDescription(false));

        ResponseEntity<?> response = customResponseBuilder.crearResponse(e);

        return response;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, Object> message = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            message.put(error.getField(), error.getDefaultMessage());
        });

        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>(new Date(), HttpStatus.BAD_REQUEST, request.getDescription(false), message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

/*
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> message = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            message.put(error.getField(), error.getDefaultMessage());
        });

        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>(new Date(), status, request.getDescription(false), message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
/*
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
*/

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest webRequest) {

        ApiResponse<String> apiResponse = new ApiResponse<>(new Date(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest.getDescription(false), exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
/**/
}
