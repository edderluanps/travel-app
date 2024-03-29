package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Voos;
import com.eluanps.travelapp.repository.VoosRepository;
import com.eluanps.travelapp.service.exceptions.DataIntegrityException;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class VoosService {

    @Autowired
    private VoosRepository voosRepository;

    public List<Voos> getAll() {
        return voosRepository.findAll();
    }

    public Voos findById(Long id) {
        return voosRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Voo não encontrado"));
    }

    public Voos salvar(Voos voos) {
        return voosRepository.save(voos);
    }

    public void atualizar(Long id, Voos voos) {
        voosRepository.findById(id).map(obj -> {
            voos.setId(obj.getId());
            return voosRepository.save(voos);
        }).orElseThrow(() -> new ObjectNotFoundException("Voo não encontrado"));
    }

    public void delete(Long id) {
        findById(id);
        try {
            voosRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Não foi possivel deletar o Voo: Item Ativo.");
        }
    }
    
}
