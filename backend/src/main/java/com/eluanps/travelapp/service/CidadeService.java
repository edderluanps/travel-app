package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Cidade;
import com.eluanps.travelapp.repository.CidadeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    CidadeRepository cidadeRepository;

    public List<Cidade> getAll() {
        return cidadeRepository.findAll();
    }

    public Cidade salvar(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

}
