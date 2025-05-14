package com.proyectos.ProyectoEcommerce.persistence.entity;

import com.proyectos.ProyectoEcommerce.persistence.enums.EstadoCarrito;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tbl_carrito")
//@Data
@Getter
@Setter
@EqualsAndHashCode(exclude = {"carritoItems", "user"}, callSuper = false)
@ToString(exclude = {"carritoItems", "user"})

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Carrito extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "precioTotal", length = 100, nullable = false)
    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero
    private double precio;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "carrito", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Los items son obligatorios")
    private List<CarritoItem> carritoItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    @NotNull(message = "El user es obligatorio")
    private User user;

    @Enumerated(EnumType.STRING)
    private EstadoCarrito estado;

    @Column(name = "active")
    private boolean active = true;
}