package com.proyectos.ProyectoEcommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_carrito")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Carrito implements Serializable {

    @Id
    @SequenceGenerator(
            name = "carrito_sequence",
            sequenceName = "carrito_sequence",
            allocationSize = 100
    )
    @GeneratedValue(generator = "carrito_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "precioTotal", length = 100, nullable = false)
    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero
    private double precio;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "carrito", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Los items son obligatorios")
    private List<CarritoItem> carritoItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente")
    @NotNull(message = "El cliente es obligatorio")
    private Cliente cliente;

    @Column(name = "active")
    private boolean active = true;
}