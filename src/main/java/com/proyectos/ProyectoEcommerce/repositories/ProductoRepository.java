package com.proyectos.ProyectoEcommerce.repositories;

import com.proyectos.ProyectoEcommerce.enums.Categoria;
import com.proyectos.ProyectoEcommerce.entities.Lote;
import com.proyectos.ProyectoEcommerce.entities.Producto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findAllByActiveTrue();

    @Query("select p from Producto p where p.active = true")
    Page<Producto> findPagesAllByActiveTrue(Pageable pageable);

    //@Query("select p from Producto p where p.nombre like :nombre% and p.active = true")
    List<Producto> findAllByNombreStartingWithIgnoreCaseAndActiveTrue(Sort sort, String nombre);

    @Modifying
    @Transactional
    @Query("update Producto p set p.active=:active where p.id=:id")
    void updateProductoSetActiveForId(@Param("active") boolean active, @Param("id") Long id);

    //@Query("select p from Producto p where p.precio between :precio1 and :precio2 and c.active = true")
    List<Producto> findAllByPrecioBetweenAndActiveTrue(double precio1, double precio2);

    @Query("select p from Producto p where p.lote.stock between :cantidad1 and :cantidad2 and p.active=true")
    List<Producto> findAllByCantidadBetweenAndActiveTrue(int cantidad1, int cantidad2);

    @Query("select p from Producto p where p.lote.stock <= :cantidad and p.active=true")
    List<Producto> findAllByCantidadLessThanEqualAndActiveTrue(int cantidad);

    // Productos que no vencen aun
    List<Producto> findAllByFechaVenGreaterThanAndActiveTrue(Date fecha);

    //listarProductosVencidos
    List<Producto> findAllByFechaVenLessThanEqualAndActiveTrue(Date fecha);

    @Query("select p from Producto p where p.fechaVen between CURRENT_DATE() and :fechaLimite ")
    List<Producto> findAllByFechaVenProximoAVencer(@Param("fechaLimite") Date fechaLimite);

    @Query("select p from Producto p where categoria = ?1")
    List<Producto> findAllByCategoria(Categoria categoria);

    @Query("select p from Producto p where p.lote.id = :loteId")
    Producto findProductoByLoteId(@Param("loteId") Long loteId);

    @Query("select p.lote from Producto p where p.id = :productoId")
    Lote findLoteByProductoId(@Param("productoId") Long productoId);

    @Query("select p from Producto p join p.lote l where l.proveedor.id = :proveedor")
    List<Producto> findAllByProveedorId(@Param("proveedor") Long proveedor);

    @Modifying
    @Transactional
    @Query("update Producto p set p.precio = ROUND(p.precio * :factor, 4) where p.active = true")
    void updateAllProductosPrecios(@Param("factor") double factor);
}
