package com.proyectos.ProyectoEcommerce.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.proyectos.ProyectoEcommerce.persistence.enums.EstadoPedido;
import com.proyectos.ProyectoEcommerce.persistence.enums.MetodoPago;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_orden_venta")

@Getter
@Setter
@EqualsAndHashCode(exclude = {"detalleUsuario", "carrito"}, callSuper = false)
@ToString(exclude = {"detalleUsuario", "carrito"})
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenVenta extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 50)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detalleUsuario", nullable = false)
    @NotNull(message = "El detalle de usuario es obligatorio")
    private DetalleUsuario detalleUsuario;

    @Column(name = "fechaCompra")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(value = TemporalType.DATE)
    //@NotNull(message = "La fecha es obligatorio")
    private Date fechaCompra = new Date();

    @Column(name = "fechaEntrega")
    @FutureOrPresent
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(value = TemporalType.DATE)
    //@NotNull(message = "La fecha de entrega es obligatorio")
    private Date fechaEntrega;

    @ManyToOne(fetch = FetchType.LAZY/*, cascade = CascadeType.MERGE*/)
    @JoinColumn(name = "carrito", nullable = false)
    @NotNull(message = "El carrito es obligatorio")
    private Carrito carrito;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El metodo de pago es obligatorio")
    private MetodoPago metodoPago;

    // Falta Metodo de Envio

    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;

    @Column(name = "active")
    private boolean active = true;
}
