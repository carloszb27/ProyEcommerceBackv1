package com.proyectos.ProyectoEcommerce.service.impl;

import com.proyectos.ProyectoEcommerce.entities.Cliente;
import com.proyectos.ProyectoEcommerce.error.exceptions.ClienteException;
import com.proyectos.ProyectoEcommerce.repositories.ClienteRepository;
import com.proyectos.ProyectoEcommerce.service.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> lista = clienteRepository.findAllByActiveTrue();

        return lista.size()>0 ? lista : new ArrayList<Cliente>();
    }

    @Override
    public Cliente listarClientePorId(Long id) throws ClienteException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException(ClienteException.NotFoundException(id)));

        return cliente;
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) {

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) throws ClienteException {

        Cliente clienteExiste = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException(ClienteException.NotFoundException(cliente.getId())));

        //cliente.setId(id);

        return clienteRepository.save(cliente);
    }

    @Override
    public String eliminarCliente(Long id) throws ClienteException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteException(ClienteException.NotFoundException(id)));

        clienteRepository.updateClienteSetActiveForId(false, id);

        return "El cliente se ha eliminado correctamente";
    }
}
