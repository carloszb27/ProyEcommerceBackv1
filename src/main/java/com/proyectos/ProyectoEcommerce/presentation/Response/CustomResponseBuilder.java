package com.proyectos.ProyectoEcommerce.presentation.Response;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
public class CustomResponseBuilder {

    public <T> ResponseEntity<ApiResponse<T>> crearResponse(T objeto){
        ApiResponse<T> respuesta = new ApiResponse<>();
        respuesta.setFecha(new Date());

        if(objeto instanceof Iterable<?> iterable) {
            return handleIterableResponse(iterable, respuesta);
        }

        if(objeto instanceof RuntimeException ex) {
            respuesta.setMensaje( ex.getMessage());
            respuesta.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
        return handleSingleObjectResponse(objeto, respuesta);
    }

    public <T> ResponseEntity<ApiResponse<T>> crearResponse(T objeto, boolean isCreated, Long id){
        ApiResponse<T> respuesta = new ApiResponse();

        respuesta.setFecha(new Date());
        respuesta.setStatus(HttpStatus.CREATED);
        respuesta.setMensaje("Registro de " + getNombreAcortado(objeto) + " exitoso");
        respuesta.setContenido(objeto);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.created(ubicacion).body(respuesta);
    }

    public <T> ResponseEntity<ApiResponse<T>> handleIterableResponse(Iterable<?> iterable, ApiResponse respuesta) {

        if( iterable.iterator().hasNext()) {
            Object primerElemento = iterable.iterator().next();
            String nombreAcortado = getNombreAcortado(primerElemento);
            respuesta.setStatus(HttpStatus.OK);
            respuesta.setMensaje("Lista de " + nombreAcortado + "s encontrados exitosamente");
            if(iterable instanceof Page<?>) {
                respuesta.setMensaje("Paginacion de " + nombreAcortado + "s encontrados exitosamente");
            }
            respuesta.setContenido((T) iterable);

            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } else {
            respuesta.setStatus(HttpStatus.NOT_FOUND);
            respuesta.setMensaje("No existen registros");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    public <T> ResponseEntity<ApiResponse<T>> handleSingleObjectResponse(T objeto, ApiResponse<T> respuesta) {
        respuesta.setStatus(HttpStatus.OK);
        respuesta.setMensaje(getNombreAcortado(objeto) + " obtenido exitosamente");
        respuesta.setContenido(objeto);

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    public String getNombreAcortado(Object objeto) {
        String nombre = objeto.getClass().getSimpleName();
        return nombre.endsWith("DTO") ? nombre.substring(0, nombre.length()-3) : nombre;
    }

}
