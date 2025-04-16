package com.proyectos.ProyectoEcommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orden_venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenVenta implements Serializable {

    @Id
    @SequenceGenerator(
            name = "ordenventa_sequence",
            sequenceName = "ordenventa_sequence"
    )
    @GeneratedValue(generator = "ordenventa_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 50)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalle_pago")
    //@NotNull(message = "El detalle de pago es obligatorio")
    private DetallePago detallePago;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @Column(name = "fechaCompra")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(value = TemporalType.DATE)
    //@NotNull(message = "La fecha es obligatorio")
    private Date fechaCompra = new Date();

    @Column(name = "fechaEntrega")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(value = TemporalType.DATE)
    //@NotNull(message = "La fecha de entrega es obligatorio")
    private Date fechaEntrega = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrito")
    @NotNull(message = "El carrito es obligatorio")
    private Carrito carrito;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_envio")
    @NotNull(message = "La direccion de envio es obligatorio")
    private DireccionEnvio direccionEnvio;

    @ManyToOne
    @JoinColumn(name = "metodo_pago"/*, nullable = false*/)
    @NotNull(message = "El metodo de pago es obligatorio")
    private MetodoPago metodoPago;

    // Falta Metodo de Envio

    @Column(name = "entregado")
    private boolean entregado;

    @Column(name = "active")
    private boolean active = true;
}
