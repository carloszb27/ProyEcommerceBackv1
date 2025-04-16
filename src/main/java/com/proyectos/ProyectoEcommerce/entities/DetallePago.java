package com.proyectos.ProyectoEcommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "detallePago")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetallePago implements Serializable {

    @Id
    @SequenceGenerator(
            name = "detallepago_sequence",
            sequenceName = "detallepago_sequence"
    )
    @GeneratedValue(generator = "detallepago_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "numero_tarjeta", length = 50, nullable = false)
    @NotBlank(message = "El numero de la tarjeta es obligatorio")
    private String numeroTarjeta;

    @Column(name = "cvv", length = 10, nullable = false)
    @NotBlank(message = "El codigo cvv es obligatorio")
    private String cvv;

    @Column(name = "fechaVen", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(value = TemporalType.DATE)
    private Date fechaVen;

    @Column(name = "password", length = 50, nullable = false)
    @NotBlank(message = "El password es obligatorio")
    private String password;

}
