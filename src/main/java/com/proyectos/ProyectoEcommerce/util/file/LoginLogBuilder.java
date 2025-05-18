package com.proyectos.ProyectoEcommerce.util.file;

import com.proyectos.ProyectoEcommerce.persistence.entity.User;
import com.proyectos.ProyectoEcommerce.util.user.ClientIPFinder;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

public class LoginLogBuilder implements Serializable {

    private static LoginLogBuilder instance = null;

    private LoginLogBuilder() {
    }

    public static LoginLogBuilder getInstance(){
        if(instance == null) {
            synchronized (LoginLogBuilder.class){
                if(instance == null) {
                    instance = new LoginLogBuilder();
                }
            }
        }
        return instance;
    }

    public void imprimirLog(User user) throws IOException {

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

        String log = "LOGIN - User: " + mensajeUser + ", IP: " + ClientIPFinder.getInstance().getClientIp() + ", Timestamp: " + LocalDateTime.now();
        FileUtil.getInstance().write(log, "src/main/resources/logsUsuarios.txt");
    }

    protected Object readResolver(){
        return instance;
    }
}
