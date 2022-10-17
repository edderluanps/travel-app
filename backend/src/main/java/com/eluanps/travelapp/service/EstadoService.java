package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Estado;
import com.eluanps.travelapp.repository.EstadoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> getAll() {
        return estadoRepository.findAll();
    }

    public List<Estado> findAll() {
        return estadoRepository.findAllByOrderByNome();
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

}
