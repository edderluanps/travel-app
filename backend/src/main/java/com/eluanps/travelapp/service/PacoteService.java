package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Pacote;
import com.eluanps.travelapp.repository.PacoteRepository;
import com.eluanps.travelapp.service.exceptions.DataIntegrityException;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;

    public List<Pacote> getAll() {
        return pacoteRepository.findAll();
    }

    public Pacote findById(Long id) {
        return pacoteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pacote não encontrado"));
    }

    public List<Pacote> getLastThree() {
        return pacoteRepository.findLastThree(PageRequest.of(0, 3));
    }

    public Page<Pacote> getPage(Integer page, Integer pageRows, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, pageRows, Direction.valueOf(direction), orderBy);
        return pacoteRepository.findAll(pageRequest);
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
        findById(id);
        try {
            pacoteRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Não foi possivel deletar o Pacote: Item Ativo.");
        }
    }

    public List<Pacote> findByNome(String nome) {
        return pacoteRepository.findByNome(nome);
    }
    
    public List<Pacote> findByNomeAndDate(String nome, LocalDate data) {
        return pacoteRepository.findByNomeAndDate(nome, data);
    }

}
