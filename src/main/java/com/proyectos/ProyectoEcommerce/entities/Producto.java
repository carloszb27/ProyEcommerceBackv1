package com.proyectos.ProyectoEcommerce.entities;

import com.proyectos.ProyectoEcommerce.enums.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Future
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(value = TemporalType.DATE)
    private Date fechaVen;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "La categoria es obligatorio")
    private Categoria categoria;

    /*
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "producto", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Los lotes es obligatorio")
    private List<Lote> lotes;
*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lote"/*, nullable = false*/)
    @NotNull(message = "El lote es obligatorio")
    private Lote lote;


    @Column(name = "descuento")
    @PositiveOrZero
    private double descuento = 0.0;

    @Column(name = "active")
    private boolean active = true;

}
