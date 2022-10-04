package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Hospedagem;
import com.eluanps.travelapp.repository.HospedagemRepository;
import com.eluanps.travelapp.service.exceptions.DataIntegrityException;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class HospedagemService {

    @Autowired
    HospedagemRepository hospedagemRepository;

    public List<Hospedagem> getAll() {
        return hospedagemRepository.findAll();
    }

    public Hospedagem findById(Long id) {
        return hospedagemRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Hospedagem não encontrada"));
    }

    public Hospedagem salvar(Hospedagem hotel) {
        return hospedagemRepository.save(hotel);
    }

    public void atualizar(Long id, Hospedagem hotel) {
        hospedagemRepository.findById(id).map(obj -> {
            hotel.setId(obj.getId());
            return hospedagemRepository.save(hotel);
        }).orElseThrow(() -> new ObjectNotFoundException("Hospedagem não encontrada"));
    }

    public void delete(Long id) {
        findById(id);
        try {
            hospedagemRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Não foi possivel deletar a Hospedagem: Item Ativo.");
        }
    }

}
