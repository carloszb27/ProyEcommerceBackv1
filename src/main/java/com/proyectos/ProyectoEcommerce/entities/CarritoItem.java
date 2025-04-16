package com.proyectos.ProyectoEcommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "carrito_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarritoItem implements Serializable {

    @Id
    @SequenceGenerator(
            name = "carritoitem_sequence",
            sequenceName = "carritoitem_sequence",
            allocationSize = 100
    )
    @GeneratedValue(generator = "carritoitem_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCarrito")
    @JsonBackReference
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @Column(name = "precioItem")
    @PositiveOrZero
    private double precio;

    @Column(name = "cantidadItem")
    @PositiveOrZero
    private int cantidad;

    @Column(name = "active")
    private boolean active = true;
}