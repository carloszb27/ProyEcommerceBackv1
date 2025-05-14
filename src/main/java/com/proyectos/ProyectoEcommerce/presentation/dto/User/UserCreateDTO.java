package com.proyectos.ProyectoEcommerce.presentation.dto.User;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Builder
public record UserCreateDTO (@NotBlank(message = "El nombre es obligatorio")String firstname, @NotBlank(message = "Los apellidos son obligatorios")String lastname,
                             @NotBlank(message = "El email es obligatorio")
                             @Email String email, @Pattern(regexp = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n" +
        "2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n" +
        "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$")
                             @NotBlank(message = "El numero de telefono es obligatorio") String cellphone,
                             @Past
                             @DateTimeFormat(pattern = "dd/MM/yyyy")
                             @Temporal(value = TemporalType.DATE)
                             @NotNull(message = "La fecha de nacimiento es obligatorio") Date fechaNacimiento,
                             @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$"
                                     , message = "El password debe tener al menos 8 caracteres, al menos una letra en mayuscula, " +
                                     "una letra en minuscula, un numero y un caracter especial") String password, Set<String> roles) {
}
