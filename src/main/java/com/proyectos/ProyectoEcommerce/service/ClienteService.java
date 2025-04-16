package com.proyectos.ProyectoEcommerce.service;

import com.proyectos.ProyectoEcommerce.entities.Cliente;

import java.util.List;

public interface ClienteService {

    public List<com.proyectos.ProyectoEcommerce.entities.Cliente> listarClientes();
    public Cliente listarClientePorId(Long id);
    public Cliente registrarCliente(Cliente cliente);
    public Cliente actualizarCliente(Long id, Cliente cliente);
    public String eliminarCliente(Long id);
}
