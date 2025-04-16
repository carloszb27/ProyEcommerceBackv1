package com.proyectos.ProyectoEcommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "metodo_pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetodoPago implements Serializable {

    @Id
    @SequenceGenerator(
            name = "metodopago_sequence",
            sequenceName = "metodopago_sequence",
            allocationSize = 1
    )
    @GeneratedValue(generator = "metodopago_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
}
