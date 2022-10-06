package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Pagamento;
import com.eluanps.travelapp.repository.PagamentoRepository;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> getAll() {
        return pagamentoRepository.findAll();
    }

    public Pagamento findById(Long id) {
        return pagamentoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pacote não encontrado"));
    }

    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public void atualizar(Long id, Pagamento pagamento) {
        pagamentoRepository.findById(id).map(obj -> {
            pagamento.setId(obj.getId());
            return pagamentoRepository.save(pagamento);
        }).orElseThrow(() -> new ObjectNotFoundException("Pacote não encontrado"));
    }

}
