package com.proyectos.ProyectoEcommerce.util.file;

import com.proyectos.ProyectoEcommerce.persistence.entity.User;
import com.proyectos.ProyectoEcommerce.util.user.ClientIPFinder;

import java.io.IOException;
import java.time.LocalDateTime;

public class LoginLogBuilder {

    public static void crearLog(User user) throws IOException {

        StringBuilder sb = new StringBuilder();

        String mensajeUser = sb.append("[Firstname: ")
                .append(user.getFirstname())
                .append(", Lastname: ")
                .append(user.getLastname())
                .append(", Email: ")
                .append(user.getEmail())
                .append(", Cellphone: ")
                .append(user.getCellphone())
                .append("]")
                .toString();

        String log = "LOGIN - User: " + mensajeUser + ", IP: " + ClientIPFinder.getClientIp() + ", Timestamp: " + LocalDateTime.now();
        FileUtil.write(log, "src/main/resources/logsUsuarios.txt");
    }
}
