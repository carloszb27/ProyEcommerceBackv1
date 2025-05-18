package com.proyectos.ProyectoEcommerce.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tbl_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
}
