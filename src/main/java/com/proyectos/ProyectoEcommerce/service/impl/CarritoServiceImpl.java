package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.dtos.Carrito.CarritoCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.Carrito.CarritoDTO;
import com.proyectos.ProyectoEcommerce.dtos.Carrito.CarritoUpdateDTO;
import com.proyectos.ProyectoEcommerce.entities.*;
import com.proyectos.ProyectoEcommerce.error.exceptions.UserException;
import com.proyectos.ProyectoEcommerce.error.exceptions.CarritoException;
import com.proyectos.ProyectoEcommerce.error.exceptions.ProductoException;
import com.proyectos.ProyectoEcommerce.mapper.CarritoMapper;
import com.proyectos.ProyectoEcommerce.repositories.UserRepository;
import com.proyectos.ProyectoEcommerce.repositories.CarritoRepository;
import com.proyectos.ProyectoEcommerce.repositories.ProductoRepository;
import com.proyectos.ProyectoEcommerce.service.CarritoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;
    private final UserRepository userRepository;

    @Autowired
    public CarritoServiceImpl(CarritoRepository carritoRepository, ProductoRepository productoRepository, UserRepository userRepository) {
        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CarritoDTO> listarCarritos() {
        List<Carrito> lista = carritoRepository.findAllByActiveTrue();
        return CarritoMapper.instancia.listaCarritoAListaCarritoDTO(lista);
    }

    @Override
    public CarritoDTO listarCarritoPorId(Long id) throws CarritoException {

        try {
            Carrito carrito = carritoRepository.findById(id)
                    .orElseThrow(() -> new CarritoException(CarritoException.NotFoundException(id)));
            return CarritoMapper.instancia.carritoACarritoDTO(carrito);

        } catch (CarritoException e) {
            throw new CarritoException(e.getMessage());
        }
    }

    @Override
    public CarritoDTO registrarCarrito(CarritoCreateDTO carritoCreateDTO) throws UserException, ProductoException {

        try {
            Carrito carrito = CarritoMapper.instancia.carritoCreateDTOACarrito(carritoCreateDTO);
            // Validar si el cliente existe
            User user = userRepository.findById(carrito.getUser().getId())
                    .orElseThrow(() -> new UserException(UserException.NotFoundException(carrito.getUser().getId())));
            // Variable para asignar el precio total del Carrito
            double precioTotal = 0.0;
            // Una lista de detalleCarrito que sera utilizada para guardar los valores
            List<CarritoItem> carritoItems = carrito.getCarritoItems();
            for (CarritoItem carritoItem : carritoItems) {
                // Validar si los productos existen
                Producto producto = productoRepository.findById(carritoItem.getProducto().getId())
                        .orElseThrow(() -> new ProductoException(ProductoException.NotFoundException(carritoItem.getProducto().getId())));
                carritoItem.setPrecio(Math.round(producto.getPrecio()));
                precioTotal += carritoItem.getCantidad() * carritoItem.getPrecio();
            }
            carrito.setPrecio(precioTotal);
            return CarritoMapper.instancia.carritoACarritoDTO(carritoRepository.save(carrito));
        } catch (CarritoException e) {
            throw new CarritoException(e.getMessage());
        }
    }

    @Override
    public CarritoDTO actualizarCarrito(CarritoUpdateDTO carritoUpdateDTO) throws CarritoException {
        try {
            Carrito carrito = CarritoMapper.instancia.carritoUpdateDTOACarrito(carritoUpdateDTO);
            Carrito carritoExiste = carritoRepository.findById(carrito.getId())
                    .orElseThrow(() -> new CarritoException(CarritoException.NotFoundException(carrito.getId())));
            return CarritoMapper.instancia.carritoACarritoDTO(carritoRepository.save(carrito));
        } catch (CarritoException e) {
            throw new CarritoException(e.getMessage());
        }
    }

    @Override
    public String eliminarCarrito(Long id) throws CarritoException {
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new CarritoException(CarritoException.NotFoundException(id)));
        carritoRepository.updateCarritoSetActiveForId(false, id);
        return "Se ha eliminado el carrito correctamente";
    }
}
