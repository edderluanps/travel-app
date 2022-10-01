package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Cliente;
import com.eluanps.travelapp.repository.ClienteRepository;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void atualizar(Long id, Cliente cliente) {
        clienteRepository.findById(id).map(obj -> {
            cliente.setId(obj.getId());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }

    public void delete(Long id) {
        clienteRepository.findById(id).map(obj -> {
            clienteRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }

}
