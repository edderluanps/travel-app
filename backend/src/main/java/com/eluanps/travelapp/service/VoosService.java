package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Voos;
import com.eluanps.travelapp.repository.VoosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VoosService {

    @Autowired
    VoosRepository voosRepository;

    public List<Voos> getAll() {
        return voosRepository.findAll();
    }

    public Voos findById(Long id) {
        return voosRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voo não encontrado!"));
    }

    public Voos salvar(Voos voos) {
        return voosRepository.save(voos);
    }

    public void atualizar(Long id, Voos voos) {
        voosRepository.findById(id).map(obj -> {
            voos.setId(obj.getId());
            return voosRepository.save(voos);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voo não encontrado!"));
    }

    public void delete(Long id) {
        voosRepository.findById(id).map(obj -> {
            voosRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voo não encontrado!"));
    }

}
