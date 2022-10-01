package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Pacote;
import com.eluanps.travelapp.repository.PacoteRepository;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacoteService {

    @Autowired
    PacoteRepository pacoteRepository;

    public List<Pacote> getAll() {
        return pacoteRepository.findAll();
    }

    public Pacote findById(Long id) {
        return pacoteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pacote não encontrado"));
    }

    public Pacote salvar(Pacote pacote) {
        return pacoteRepository.save(pacote);
    }

    public void atualizar(Long id, Pacote pacote) {
        pacoteRepository.findById(id).map(obj -> {
            pacote.setId(obj.getId());
            return pacoteRepository.save(pacote);
        }).orElseThrow(() -> new ObjectNotFoundException("Pacote não encontrado"));
    }

    public void delete(Long id) {
        pacoteRepository.findById(id).map(obj -> {
            pacoteRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ObjectNotFoundException("Pacote não encontrado"));
    }

}
