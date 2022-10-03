package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Endereco;
import com.eluanps.travelapp.repository.EnderecoRepository;
import com.eluanps.travelapp.service.exceptions.DataIntegrityException;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public List<Endereco> getAll() {
        return enderecoRepository.findAll();
    }

    public Endereco findById(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado"));
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void atualizar(Long id, Endereco endereco) {
        enderecoRepository.findById(id).map(obj -> {
            endereco.setId(obj.getId());
            return enderecoRepository.save(endereco);
        }).orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado"));
    }

    public void delete(Long id) {
        findById(id);
        try {
            enderecoRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Não foi possivel deletar o Endereço: Item Ativo.");
        }
    }

}
