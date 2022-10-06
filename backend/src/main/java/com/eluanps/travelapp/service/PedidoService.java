package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Pedido;
import com.eluanps.travelapp.repository.PedidoRepository;
import com.eluanps.travelapp.service.exceptions.DataIntegrityException;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado"));
    }

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void atualizar(Long id, Pedido pedido) {
        pedidoRepository.findById(id).map(obj -> {
            pedido.setId(obj.getId());
            return pedidoRepository.save(pedido);
        }).orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado"));
    }

    public void delete(Long id) {
        findById(id);
        try {
            pedidoRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Não foi possivel deletar o Pedido: Item Ativo.");
        }
    }

}
