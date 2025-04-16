package com.proyectos.ProyectoEcommerce.controllers;

import com.proyectos.ProyectoEcommerce.entities.Producto;
import com.proyectos.ProyectoEcommerce.error.exceptions.ProductoException;
import com.proyectos.ProyectoEcommerce.repositories.ProductoRepository;
import com.proyectos.ProyectoEcommerce.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoController(ProductoService productoService, ProductoRepository productoRepository) {
        this.productoService = productoService;
        this.productoRepository = productoRepository;
    }

    @GetMapping("")
    public ResponseEntity<?> listadoProductos(){
        List<Producto> lista = productoService.listarProductos();
        return new ResponseEntity<>(lista, lista.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    //@GetMapping("/paginado/{nropagina}")
    @GetMapping("/paginado")
    public ResponseEntity<?> listadoProductos(@PageableDefault(page = 0, size = 10, sort = "nombre") Pageable pageable/*, @PathVariable Integer nropagina*/){

        //Pageable pageable = PageRequest.of(
                //nropagina-1, 10,
                //Sort.by(Sort.Direction.ASC,"nombre"));

        Page<Producto> lista = productoRepository.findAllByActiveTrue(pageable);

        return new ResponseEntity<>(lista, !lista.getContent().isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarProductoPorId(@PathVariable Long id){

        try {
            return new ResponseEntity<>(productoService.listarProductoPorId(id), HttpStatus.OK);
        } catch (ProductoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/empiezaPor/{nombre}")
    public ResponseEntity<?> listadoProductosQueEmpiecenPor(@PathVariable String nombre){
        List<Producto> lista = productoRepository.findAllByNombreStartingWithIgnoreCaseAndActiveTrue(Sort.by("nombre", "precio"),nombre);
        return new ResponseEntity<>(lista, lista.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**/
    @GetMapping("/masComprados")
    public ResponseEntity<?> listadoProductosMasComprados(){
        List<Producto> lista = productoService.listarProductosMasComprados();
        return new ResponseEntity<>(lista, lista.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    /**/

    @PostMapping("")
    public ResponseEntity<?> registrarProducto(@Valid @RequestBody Producto producto){
        return new ResponseEntity<>(productoService.registrarProducto(producto), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarProducto(@Valid @RequestBody Producto producto){

        try {
            return new ResponseEntity<>(productoService.actualizarProducto(producto), HttpStatus.OK);
        } catch (ProductoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){

        try {
            return new ResponseEntity<>(productoService.eliminarProducto(id), HttpStatus.OK);
        } catch (ProductoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/precio")
    public ResponseEntity<List<Producto>> listarProductosPorPrecioEntre(@RequestParam double p1, @RequestParam double p2){

        return new ResponseEntity<>(productoRepository.findAllByPrecioBetweenAndActiveTrue(p1, p2), HttpStatus.OK);
    }

    @GetMapping("/cantidad")
    public ResponseEntity<List<Producto>> listarProductosPorCantidadEntre(@RequestParam int c1, @RequestParam int c2){

        return new ResponseEntity<>(productoRepository.findAllByCantidadBetweenAndActiveTrue(c1, c2), HttpStatus.OK);
    }

    @GetMapping("/muyPocaCantidad")
    public ResponseEntity<List<Producto>> listarProductosPorMuyPocaCantidad(){

        return new ResponseEntity<>(productoRepository.findAllByCantidadLessThanEqualAndActiveTrue(5), HttpStatus.OK);
    }

    @GetMapping("/todaviaNoVencen")
    public ResponseEntity<List<Producto>> listarProductosQueTodaviaNoVencen(){

        return new ResponseEntity<>(productoRepository.findAllByFechaVenGreaterThanAndActiveTrue(new Date()), HttpStatus.OK);
    }

    @GetMapping("/vencenEnMenosUnMes")
    public ResponseEntity<List<Producto>> listarProductosVencenMenosDeUnMes(){

        return new ResponseEntity<>(productoRepository.findAllByFechaVenProximoAVencer(), HttpStatus.OK);
    }

    @GetMapping("/vencidos")
    public ResponseEntity<List<Producto>> listarProductosVencidos(){

        return new ResponseEntity<>(productoRepository.findAllByFechaVenLessThanEqualAndActiveTrue(new Date()), HttpStatus.OK);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Producto>> listarProductosPorCategoria(@PathVariable Long id){

        return new ResponseEntity<>(productoRepository.findAllByCategoriaId(id), HttpStatus.OK);
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<List<Producto>> listarProductosPorProveedor(@PathVariable Long id){

        return new ResponseEntity<>(productoRepository.findAllByProveedorId(id), HttpStatus.OK);
    }

}
