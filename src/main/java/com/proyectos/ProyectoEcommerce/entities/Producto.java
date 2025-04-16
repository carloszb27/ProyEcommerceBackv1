package com.proyectos.ProyectoEcommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString(exclude = "proveedor")
public class Producto implements Serializable {

    @Id
    @SequenceGenerator(
            name = "producto_sequence",
            sequenceName = "producto_sequence",
            allocationSize = 100
    )
    @GeneratedValue(generator = "producto_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "idProducto", length = 50)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "descripcion", length = 100, nullable = false)
    @NotBlank(message = "La descripcion es obligatorio")
    private String descripcion;

    @Column(name = "precio", length = 100, nullable = false)
    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero
    private double precio;

    @Column(name = "cantidad", length = 100, nullable = false)
    @NotNull(message = "La cantidad es obligatorio")
    @PositiveOrZero
    private int cantidad;

    @Column(name = "urlImagen", length = 100)
    @NotBlank(message = "La url de la imagen es obligatorio")
    @Size(min = 10, max = 100, message = "La url debe tener al menos 10 caracteres" +
            " y no debe exceder los 100 caracteres")
    private String urlImagen;

    @Column(name = "fechaVen")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(value = TemporalType.DATE)
    private Date fechaVen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria", nullable = false)
    @NotNull(message = "La categoria es obligatorio")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor", nullable = false)
    @NotNull(message = "El proveedor es obligatorio")
    private Proveedor proveedor;

    @Column(name = "active")
    private boolean active = true;

}
