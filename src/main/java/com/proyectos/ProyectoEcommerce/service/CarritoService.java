package com.proyectos.ProyectoEcommerce.service;


import com.proyectos.ProyectoEcommerce.dtos.Carrito.CarritoCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.Carrito.CarritoDTO;
import com.proyectos.ProyectoEcommerce.dtos.Carrito.CarritoUpdateDTO;
import com.proyectos.ProyectoEcommerce.util.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CarritoService {

    public List<CarritoDTO> listarCarritos();
    public CarritoDTO listarCarritoPorId(Long id);
    public CarritoDTO registrarCarrito(CarritoCreateDTO carrito);
    public CarritoDTO actualizarCarrito(CarritoUpdateDTO carrito);
    public String eliminarCarrito(Long id);
}
