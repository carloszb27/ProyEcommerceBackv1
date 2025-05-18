package com.proyectos.ProyectoEcommerce.util.user;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ClientIPFinder {

    private static ClientIPFinder instance = null;

    private ClientIPFinder(){
    }

    public static ClientIPFinder getInstance(){
        if(instance == null) {
            synchronized (ClientIPFinder.class) {
                if(instance == null) {
                    instance = new ClientIPFinder();
                }
            }
        }
        return instance;
    }

    public String getClientIp() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            HttpServletRequest request = servletRequestAttributes.getRequest();

            String ip = request.getHeader("X-Forwarded-For");

            if (ip == null || ip.isBlank()) {
                ip = request.getRemoteAddr();
            }

            return ip;
        }
        return "Unknown";
    }
}
