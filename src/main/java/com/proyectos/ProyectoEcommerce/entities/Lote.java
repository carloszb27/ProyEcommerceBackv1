package com.proyectos.ProyectoEcommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tbl_lote")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lote extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "precio", length = 100, nullable = false)
    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero
    private double precio;

    @Column(name = "stock", length = 100, nullable = false)
    @NotNull(message = "La cantidad es obligatorio")
    @PositiveOrZero
    private int stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "proveedor"/*, nullable = false*/)
    //@NotNull(message = "El proveedor es obligatorio")
    private Proveedor proveedor;

    @Column(name = "active")
    private boolean active = true;

}
