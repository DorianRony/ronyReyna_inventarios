package com.prueba.ronyreyna_inventarios.service;

import com.prueba.ronyreyna_inventarios.models.entity.Cliente;
import com.prueba.ronyreyna_inventarios.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public List<Cliente> lista() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    public Cliente findbyId(Integer id) {
        return clienteRepository.findById(id).get();
    }

    public Cliente findbyIdentificacion(String identificacion) {
        return clienteRepository.findByIdentificacion(identificacion);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
}
