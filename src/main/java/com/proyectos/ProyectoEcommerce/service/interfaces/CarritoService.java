package com.proyectos.ProyectoEcommerce.service.interfaces;


import com.proyectos.ProyectoEcommerce.persistence.entity.Carrito;
import com.proyectos.ProyectoEcommerce.presentation.dto.Carrito.CarritoCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Carrito.CarritoDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Carrito.CarritoUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface CarritoService {

    public List<CarritoDTO> listarCarritos();
    public CarritoDTO listarCarritoPorId(Long id);
    public CarritoDTO registrarCarrito(CarritoCreateDTO carrito);
    public CarritoDTO actualizarCarrito(CarritoUpdateDTO carrito);
    public String eliminarCarrito(Long id);
    CarritoDTO listarCarritoPorUserActual();
}
