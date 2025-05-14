package com.proyectos.ProyectoEcommerce.presentation.controller;

import com.proyectos.ProyectoEcommerce.presentation.dto.Producto.ProductoCreateDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Producto.ProductoDTO;
import com.proyectos.ProyectoEcommerce.presentation.dto.Producto.ProductoUpdateDTO;
import com.proyectos.ProyectoEcommerce.persistence.enums.Categoria;
import com.proyectos.ProyectoEcommerce.service.aop.AdminOnly;
import com.proyectos.ProyectoEcommerce.service.interfaces.ProductoService;
import com.proyectos.ProyectoEcommerce.presentation.Response.CustomResponseBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Product resource")
public class ProductoController {

    private final ProductoService productoService;
    private final CustomResponseBuilder customResponseBuilder;

    //@SaveInFile
    @GetMapping("")
    public ResponseEntity<?> listadoProductos(){
        List<ProductoDTO> lista = productoService.listarProductos();
        return customResponseBuilder.crearResponse(lista);
    }

    @GetMapping("/paginado")
    public ResponseEntity<?> listadoProductos(@PageableDefault(page = 0, size = 10, sort = "nombre") Pageable pageable){
        Page<ProductoDTO> paginaProductosDTO = productoService.listarProductosPaginado(pageable);
        return customResponseBuilder.crearResponse(paginaProductosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarProductoPorId(@PathVariable Long id){
        ProductoDTO productoDTO = productoService.listarProductoPorId(id);
        return customResponseBuilder.crearResponse(productoDTO);
    }

    @GetMapping("/empiezaPor/{nombre}")
    public ResponseEntity<?> listadoProductosQueEmpiecenPor(@PathVariable String nombre){
        List<ProductoDTO> listaDTO =productoService.listarProductosQueEmpiecenPor(nombre);
        return customResponseBuilder.crearResponse(listaDTO);
    }


    @GetMapping("/masComprados")
    public ResponseEntity<?> listadoProductosMasComprados(){
        List<ProductoDTO> productoDTOS = productoService.listarProductosMasComprados();
        return customResponseBuilder.crearResponse(productoDTOS);
    }


    @PostMapping("")
    public ResponseEntity<?> registrarProducto(@Valid @RequestBody ProductoCreateDTO producto){
        ProductoDTO productoDTO = productoService.registrarProducto(producto);
        return customResponseBuilder.crearResponse(productoDTO, true, productoDTO.id());
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarProducto(@Valid @RequestBody ProductoUpdateDTO producto){
        ProductoDTO productoDTO = productoService.actualizarProducto(producto);
        return customResponseBuilder.crearResponse(productoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        String mensaje = productoService.eliminarProducto(id);
        return customResponseBuilder.crearResponse(mensaje);
    }

    @GetMapping("/precio")
    public ResponseEntity<?> listarProductosPorPrecioEntre(@RequestParam double p1, @RequestParam double p2){
        List<ProductoDTO> listaDTO = productoService.listarProductosPorPrecioEntre(p1, p2);
        return customResponseBuilder.crearResponse(listaDTO);
    }

    @GetMapping("/cantidad")
    public ResponseEntity<?> listarProductosPorCantidadEntre(@RequestParam int c1, @RequestParam int c2){
        List<ProductoDTO> listaDTO = productoService.listarProductosPorCantidadEntre(c1, c2);
        return customResponseBuilder.crearResponse(listaDTO);
    }

    @GetMapping("/muyPocaCantidad")
    public ResponseEntity<?> listarProductosPorMuyPocaCantidad(){
        List<ProductoDTO> listaDTO = productoService.listarProductosPorMuyPocaCantidad();
        return customResponseBuilder.crearResponse(listaDTO);
    }

    @GetMapping("/todaviaNoVencen")
    public ResponseEntity<?> listarProductosQueTodaviaNoVencen(){
        List<ProductoDTO> listaDTO = productoService.listarProductosQueTodaviaNoVencen();
        return customResponseBuilder.crearResponse(listaDTO);
    }

    @GetMapping("/vencenEnMenosUnMes")
    public ResponseEntity<?> listarProductosVencenMenosDeUnMes(){
        List<ProductoDTO> listaDTO = productoService.listarProductosVencenMenosDeUnMes();
        return customResponseBuilder.crearResponse(listaDTO);
    }

    @GetMapping("/vencidos")
    public ResponseEntity<?> listarProductosVencidos(){
        List<ProductoDTO> listaDTO = productoService.listarProductosVencidos();
        return customResponseBuilder.crearResponse(listaDTO);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<?> listarProductosPorCategoria(@PathVariable Categoria categoria){
       List<ProductoDTO> listaDTO = productoService.listarProductosPorCategoria(categoria);
        return customResponseBuilder.crearResponse(listaDTO);
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<?> listarProductosPorProveedor(@PathVariable Long id){
        List<ProductoDTO> listaDTO = productoService.listarProductosPorProveedor(id);
        return customResponseBuilder.crearResponse(listaDTO);
    }

    @GetMapping("/lote/{id}")
    public ResponseEntity<?> listarProductoPorLote(@PathVariable Long id){
        ProductoDTO productoDTO = productoService.listarProductoPorLote(id);
        return customResponseBuilder.crearResponse(productoDTO);
    }

    @PutMapping("/aumentarPrecioProds/{porcentaje}")
    public ResponseEntity<?> actualizarPrecioProductos(@PathVariable Double porcentaje){
        String mensaje = productoService.actualizarPrecioProductos(porcentaje);
        return customResponseBuilder.crearResponse(mensaje);
    }

    @PutMapping("/actualizarPreciosIniciales")
    public ResponseEntity<?> actualizarPreciosOriginal(){
        List<ProductoDTO> listaProductoDTO = productoService.actualizarProductosAPrecioOriginal();
        return customResponseBuilder.crearResponse(listaProductoDTO);
    }
}
