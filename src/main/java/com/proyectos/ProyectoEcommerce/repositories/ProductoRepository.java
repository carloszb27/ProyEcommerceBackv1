package com.proyectos.ProyectoEcommerce.repositories;

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
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findAllByActiveTrue();

    Page<Producto> findAllByActiveTrue(Pageable pageable);

    //@Query("select p from Producto p where p.nombre like :nombre% and p.active = true")
    List<Producto> findAllByNombreStartingWithIgnoreCaseAndActiveTrue(Sort sort, String nombre);

    @Modifying
    @Transactional
    @Query("update Producto p set p.active=:active where p.id=:id")
    void updateProductoSetActiveForId(@Param("active") boolean active, @Param("id") Long id);

    //@Query("select p from Producto p where p.precio between :precio1 and :precio2 and c.active = true")
    List<Producto> findAllByPrecioBetweenAndActiveTrue(double precio1, double precio2);

    List<Producto> findAllByCantidadBetweenAndActiveTrue(int cantidad1, int cantidad2);

    List<Producto> findAllByCantidadLessThanEqualAndActiveTrue(int cantidad);


    // Productos que no vencen aun
    List<Producto> findAllByFechaVenGreaterThanAndActiveTrue(Date fecha);

    //listarProductosVencidos
    List<Producto> findAllByFechaVenLessThanEqualAndActiveTrue(Date fecha);

    //listarProductosPorVencerEnMenosdeUnMes

    //@Query("select p from Producto p where (p.fechaVen - :fecha)<1")
    //@Query(value = " select * from producto where (fecha_ven - now()) < interval '1 month' and fecha_ven > now() ", nativeQuery = true)
    @Query(value = " select * from producto where (fecha_ven - now()) between interval '0 day' and interval '1 month' and active=true ", nativeQuery = true)
    List<Producto> findAllByFechaVenProximoAVencer();



    @Query("select p from Producto p where p.categoria.id = ?1")
    List<Producto> findAllByCategoriaId(Long categoria);

    /*
    @Query("select p from Producto p where p.stock.id =:stock")
    List<Producto> findAllByStockId(@Param("proveedor") Long stock);
    */

    @Query("select distinct p from Producto p join p.stock s where s.proveedor.id = :proveedor")
    List<Producto> findAllByProveedorId(@Param("proveedor") Long proveedor);

}
