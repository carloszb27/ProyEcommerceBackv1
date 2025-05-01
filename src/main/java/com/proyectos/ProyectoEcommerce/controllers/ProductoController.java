package com.proyectos.ProyectoEcommerce.controllers;

import com.proyectos.ProyectoEcommerce.dtos.Producto.ProductoCreateDTO;
import com.proyectos.ProyectoEcommerce.dtos.Producto.ProductoDTO;
import com.proyectos.ProyectoEcommerce.dtos.Producto.ProductoUpdateDTO;
import com.proyectos.ProyectoEcommerce.enums.Categoria;
import com.proyectos.ProyectoEcommerce.service.ProductoService;
import com.proyectos.ProyectoEcommerce.util.CreateResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;
    private final CreateResponse createResponse;

    @Autowired
    public ProductoController(ProductoService productoService, CreateResponse createResponse) {
        this.productoService = productoService;
        this.createResponse = createResponse;
    }

    @GetMapping("")
    public ResponseEntity<?> listadoProductos(){
        List<ProductoDTO> lista = productoService.listarProductos();
        return createResponse.crearResponse(lista);
    }

    @GetMapping("/paginado")
    public ResponseEntity<?> listadoProductos(@PageableDefault(page = 0, size = 10, sort = "nombre") Pageable pageable){
        Page<ProductoDTO> paginaProductosDTO = productoService.listarProductosPaginado(pageable);
        return createResponse.crearResponse(paginaProductosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarProductoPorId(@PathVariable Long id){
        ProductoDTO productoDTO = productoService.listarProductoPorId(id);
        return createResponse.crearResponse(productoDTO);
    }

    @GetMapping("/empiezaPor/{nombre}")
    public ResponseEntity<?> listadoProductosQueEmpiecenPor(@PathVariable String nombre){
        List<ProductoDTO> listaDTO =productoService.listarProductosQueEmpiecenPor(nombre);
        return createResponse.crearResponse(listaDTO);
    }


    @GetMapping("/masComprados")
    public ResponseEntity<?> listadoProductosMasComprados(){
        List<ProductoDTO> productoDTOS = productoService.listarProductosMasComprados();
        return createResponse.crearResponse(productoDTOS);
    }


    @PostMapping("")
    public ResponseEntity<?> registrarProducto(@Valid @RequestBody ProductoCreateDTO producto){
        ProductoDTO productoDTO = productoService.registrarProducto(producto);
        return createResponse.crearResponse(productoDTO, true, productoDTO.id());
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarProducto(@Valid @RequestBody ProductoUpdateDTO producto){
        ProductoDTO productoDTO = productoService.actualizarProducto(producto);
        return createResponse.crearResponse(productoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        String mensaje = productoService.eliminarProducto(id);
        return createResponse.crearResponse(mensaje);
    }

    @GetMapping("/precio")
    public ResponseEntity<?> listarProductosPorPrecioEntre(@RequestParam double p1, @RequestParam double p2){
        List<ProductoDTO> listaDTO = productoService.listarProductosPorPrecioEntre(p1, p2);
        return createResponse.crearResponse(listaDTO);
    }

    @GetMapping("/cantidad")
    public ResponseEntity<?> listarProductosPorCantidadEntre(@RequestParam int c1, @RequestParam int c2){
        List<ProductoDTO> listaDTO = productoService.listarProductosPorCantidadEntre(c1, c2);
        return createResponse.crearResponse(listaDTO);
    }

    @GetMapping("/muyPocaCantidad")
    public ResponseEntity<?> listarProductosPorMuyPocaCantidad(){
        List<ProductoDTO> listaDTO = productoService.listarProductosPorMuyPocaCantidad();
        return createResponse.crearResponse(listaDTO);
    }

    @GetMapping("/todaviaNoVencen")
    public ResponseEntity<?> listarProductosQueTodaviaNoVencen(){
        List<ProductoDTO> listaDTO = productoService.listarProductosQueTodaviaNoVencen();
        return createResponse.crearResponse(listaDTO);
    }

    @GetMapping("/vencenEnMenosUnMes")
    public ResponseEntity<?> listarProductosVencenMenosDeUnMes(){
        List<ProductoDTO> listaDTO = productoService.listarProductosVencenMenosDeUnMes();
        return createResponse.crearResponse(listaDTO);
    }

    @GetMapping("/vencidos")
    public ResponseEntity<?> listarProductosVencidos(){
        List<ProductoDTO> listaDTO = productoService.listarProductosVencidos();
        return createResponse.crearResponse(listaDTO);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<?> listarProductosPorCategoria(@PathVariable Categoria categoria){
       List<ProductoDTO> listaDTO = productoService.listarProductosPorCategoria(categoria);
        return createResponse.crearResponse(listaDTO);
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<?> listarProductosPorProveedor(@PathVariable Long id){
        List<ProductoDTO> listaDTO = productoService.listarProductosPorProveedor(id);
        return createResponse.crearResponse(listaDTO);
    }

    @GetMapping("/lote/{id}")
    public ResponseEntity<?> listarProductoPorLote(@PathVariable Long id){
        ProductoDTO productoDTO = productoService.listarProductoPorLote(id);
        return createResponse.crearResponse(productoDTO);
    }

    @PutMapping("/aumentarPrecioProds/{porcentaje}")
    public ResponseEntity<?> actualizarPrecioProductos(@PathVariable Double porcentaje){
        String mensaje = productoService.actualizarPrecioProductos(porcentaje);
        return createResponse.crearResponse(mensaje);
    }

    @PutMapping("/actualizarPreciosIniciales")
    public ResponseEntity<?> actualizarPreciosOriginal(){
        List<ProductoDTO> listaProductoDTO = productoService.actualizarProductosAPrecioOriginal();
        return createResponse.crearResponse(listaProductoDTO);
    }
}
