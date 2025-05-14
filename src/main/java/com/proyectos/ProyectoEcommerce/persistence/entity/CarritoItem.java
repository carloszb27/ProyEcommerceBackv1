package com.proyectos.ProyectoEcommerce.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tbl_carrito_item")

@Getter
@Setter
@EqualsAndHashCode(exclude = {"producto"}, callSuper = false)
@ToString(exclude = {"producto"})
//@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString(exclude = "carrito")
public class CarritoItem extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
/*
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCarrito")
    @JsonBackReference
    private Carrito carrito;
*/
    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @Column(name = "precioItem")
    @PositiveOrZero
    private double precio;

    @Column(name = "cantidadItem")
    @PositiveOrZero
    private Integer cantidad;

    @Column(name = "active")
    private boolean active = true;
}