package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Endereco;
import com.eluanps.travelapp.repository.EnderecoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public List<Endereco> getAll() {
        return enderecoRepository.findAll();
    }

    public Endereco findById(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado!"));
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void atualizar(Long id, Endereco endereco) {
        enderecoRepository.findById(id).map(obj -> {
            endereco.setId(obj.getId());
            return enderecoRepository.save(endereco);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco não encontrado!"));
    }

    public void delete(Long id) {
        enderecoRepository.findById(id).map(obj -> {
            enderecoRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco não encontrado!"));
    }

}
