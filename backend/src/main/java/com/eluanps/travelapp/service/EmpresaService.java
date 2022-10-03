package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Empresa;
import com.eluanps.travelapp.repository.EmpresaRepository;
import com.eluanps.travelapp.service.exceptions.DataIntegrityException;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    public List<Empresa> getAll() {
        return empresaRepository.findAll();
    }

    public Empresa findById(Long id) {
        return empresaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Empresa não encontrada"));
    }

    public Empresa salvar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public void atualizar(Long id, Empresa empresa) {
        empresaRepository.findById(id).map(obj -> {
            empresa.setId(obj.getId());
            return empresaRepository.save(empresa);
        }).orElseThrow(() -> new ObjectNotFoundException("Empresa não encontrada"));
    }

    public void delete(Long id) {
        findById(id);
        try {
            empresaRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Não foi possivel deletar a Empresa: Item Ativo.");
        }
    }

}
