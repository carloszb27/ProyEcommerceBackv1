package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.entities.*;
import com.proyectos.ProyectoEcommerce.error.exceptions.UserException;
import com.proyectos.ProyectoEcommerce.error.exceptions.CarritoException;
import com.proyectos.ProyectoEcommerce.error.exceptions.ProductoException;
import com.proyectos.ProyectoEcommerce.repositories.UserRepository;
import com.proyectos.ProyectoEcommerce.repositories.CarritoItemRepository;
import com.proyectos.ProyectoEcommerce.repositories.CarritoRepository;
import com.proyectos.ProyectoEcommerce.repositories.ProductoRepository;
import com.proyectos.ProyectoEcommerce.service.CarritoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final CarritoItemRepository carritoItemRepository;
    private final ProductoRepository productoRepository;
    private final UserRepository userRepository;

    @Autowired
    public CarritoServiceImpl(CarritoRepository carritoRepository, CarritoItemRepository carritoItemRepository, ProductoRepository productoRepository, UserRepository userRepository) {
        this.carritoRepository = carritoRepository;
        this.carritoItemRepository = carritoItemRepository;
        this.productoRepository = productoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Carrito> listarCarritos() {
        List<Carrito> lista = carritoRepository.findAllByActiveTrue();

        return lista.size()>0 ? lista : new ArrayList<Carrito>();
    }

    @Override
    public Carrito listarCarritoPorId(Long id) throws CarritoException {
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new CarritoException(CarritoException.NotFoundException(id)));

        return carrito;
    }

    @Override
    public Carrito registrarCarrito(Carrito carrito) throws UserException, ProductoException {

        // Validar si el cliente existe

        User user = userRepository.findById(carrito.getUser().getId())
                .orElseThrow(() -> new UserException(UserException.NotFoundException(carrito.getUser().getId())));

        // Variable para asignar el precio total del Carrito

        double precioTotal = 0.0;

        // Una lista de detalleCarrito que sera utilizada para guardar los valores

        List<CarritoItem> carritoItems = carrito.getCarritoItems();


        for(CarritoItem carritoItem : carritoItems){

            // Validar si los productos existen
            Producto producto = productoRepository.findById(carritoItem.getProducto().getId())
                    .orElseThrow(() -> new ProductoException(ProductoException.NotFoundException(carritoItem.getProducto().getId())));

            carritoItem.setPrecio(Math.round(producto.getPrecio() ));

            precioTotal += carritoItem.getCantidad() * carritoItem.getPrecio();

        }

        carrito.setPrecio(precioTotal);

        return carritoRepository.save(carrito);
    }

    @Override
    public Carrito actualizarCarrito(Carrito carrito) throws CarritoException {
        Carrito carritoExiste = carritoRepository.findById(carrito.getId())
                .orElseThrow(() -> new CarritoException(CarritoException.NotFoundException(carrito.getId())));

        return carritoRepository.save(carrito);
    }

    @Override
    public String eliminarCarrito(Long id) throws CarritoException {
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new CarritoException(CarritoException.NotFoundException(id)));

        return "Se ha eliminado el carrito correctamente";
    }
}
