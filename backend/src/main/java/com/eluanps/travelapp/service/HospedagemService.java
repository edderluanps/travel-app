package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Hospedagem;
import com.eluanps.travelapp.repository.HospedagemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HospedagemService {

    @Autowired
    HospedagemRepository hospedagemRepository;

    public List<Hospedagem> getAll() {
        return hospedagemRepository.findAll();
    }

    public Hospedagem findById(Long id) {
        return hospedagemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospedagem não encontrada!"));
    }

    public Hospedagem salvar(Hospedagem hotel) {
        return hospedagemRepository.save(hotel);
    }

    public void atualizar(Long id, Hospedagem hotel) {
        hospedagemRepository.findById(id).map(obj -> {
            hotel.setId(obj.getId());
            return hospedagemRepository.save(hotel);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospedagem não encontrada!"));
    }

    public void delete(Long id) {
        hospedagemRepository.findById(id).map(obj -> {
            hospedagemRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospedagem não encontrada!"));
    }
    
}
