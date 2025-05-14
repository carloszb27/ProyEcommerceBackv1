package com.proyectos.ProyectoEcommerce.service.implementation;

import com.proyectos.ProyectoEcommerce.persistence.entity.Carrito;
import com.proyectos.ProyectoEcommerce.persistence.entity.CarritoItem;
import com.proyectos.ProyectoEcommerce.persistence.entity.Producto;
import com.proyectos.ProyectoEcommerce.persistence.entity.User;
import com.proyectos.ProyectoEcommerce.persistence.enums.EstadoCarrito;
import com.proyectos.ProyectoEcommerce.presentation.dto.Carrito.CarritoCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Carrito.CarritoDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Carrito.CarritoUpdateDTO;
import com.proyectos.ProyectoEcommerce.service.exception.UserException;
import com.proyectos.ProyectoEcommerce.service.exception.CarritoException;
import com.proyectos.ProyectoEcommerce.service.exception.ProductoException;
import com.proyectos.ProyectoEcommerce.util.mapper.CarritoMapper;
import com.proyectos.ProyectoEcommerce.persistence.repository.CarritoRepository;
import com.proyectos.ProyectoEcommerce.persistence.repository.ProductoRepository;
import com.proyectos.ProyectoEcommerce.service.interfaces.CarritoService;
import com.proyectos.ProyectoEcommerce.util.user.SessionUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

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

            Optional<Carrito> carritoExiste = listarCarritoPorUserActual();

            if(carritoExiste.isPresent()) {
                carrito.setId(carritoExiste.get().getId());
                carrito.setEstado(EstadoCarrito.ACTUALIZADO);
            } else {
                carrito.setEstado(EstadoCarrito.CREADO);
            }

             User userActual = SessionUser.getUserAutenticado();
             carrito.setUser(userActual);

            // Variable para asignar el precio total del Carrito
            double precioTotal = 0.0;
            // Una lista de detalleCarrito que sera utilizada para guardar los valores
            List<CarritoItem> carritoItems = carrito.getCarritoItems();
            for (CarritoItem carritoItem : carritoItems) {
                // Validar si los productos existen
                Producto producto = productoRepository.findById(carritoItem.getProducto().getId())
                        .orElseThrow(() -> new ProductoException(ProductoException.NotFoundException(carritoItem.getProducto().getId())));

                int cantidadCarritoItem = carritoItem.getCantidad();

                int stock = producto.getLote().getStock();

                if(cantidadCarritoItem <= 0) {
                    throw new CarritoException("La cantidad no puede ser menor o igual a cero");
                }

                if(stock < 5 || stock - cantidadCarritoItem < 5 ) {
                    throw new CarritoException("No hay suficiente stock");
                }

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

    @Override
    public Optional<Carrito> listarCarritoPorUserActual() {
        User userActual = SessionUser.getUserAutenticado();
        return carritoRepository.findByUserId(userActual.getId());
    }

}
