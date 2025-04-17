package com.proyectos.ProyectoEcommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto implements Serializable {

    @Id
    @SequenceGenerator(
            name = "producto_sequence",
            sequenceName = "producto_sequence",
            allocationSize = 100
    )
    @GeneratedValue(generator = "producto_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "descripcion", length = 100, nullable = false)
    @NotBlank(message = "La descripcion es obligatorio")
    private String descripcion;

    @Column(name = "precio", length = 100/*, nullable = false*/)
    //@NotNull(message = "El precio es obligatorio")
    @PositiveOrZero
    private double precio;

    /*
    @Column(name = "cantidad", length = 100, nullable = false)
    @NotNull(message = "La cantidad es obligatorio")
    @PositiveOrZero
    private int cantidad;
    */

    @Column(name = "urlImagen", length = 100)
    @NotBlank(message = "La url de la imagen es obligatorio")
    @Size(min = 10, max = 300, message = "La url debe tener al menos 10 caracteres" +
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "producto", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Los lotes es obligatorio")
    private List<Lote> lotes;

    @Column(name = "descuento")
    private boolean descuento = false;

    @Column(name = "active")
    private boolean active = true;

}
