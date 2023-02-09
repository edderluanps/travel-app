package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Cliente;
import com.eluanps.travelapp.entity.ItemPedido;
import com.eluanps.travelapp.entity.Pedido;
import com.eluanps.travelapp.entity.PgBoleto;
import com.eluanps.travelapp.entity.enums.PagamentoStatus;
import com.eluanps.travelapp.repository.ItemPedidoRepository;
import com.eluanps.travelapp.repository.PagamentoRepository;
import com.eluanps.travelapp.repository.PedidoRepository;
import com.eluanps.travelapp.security.UserSS;
import com.eluanps.travelapp.service.exceptions.AuthorizationException;
import com.eluanps.travelapp.service.exceptions.DataIntegrityException;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PacoteService pacoteService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado"));
    }

    public Pedido salvar(Pedido pedido) {
        pedido.setDataPedido(new Date());
        pedido.setCliente(clienteService.findById(pedido.getCliente().getId()));
        pedido.getPagamento().setStatus(PagamentoStatus.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if (pedido.getPagamento() instanceof PgBoleto) {
            PgBoleto pgBoleto = (PgBoleto) pedido.getPagamento();
            boletoService.preencherPGBoleto(pgBoleto, pedido.getDataPedido());
        }
        pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido ipedido : pedido.getItens()) {
            ipedido.setDesconto(0);
            ipedido.setPacote(pacoteService.findById(ipedido.getPacote().getId()));
            ipedido.setPreco(ipedido.getPacote().getPreco());
            ipedido.setPedido(pedido);
        }

        itemPedidoRepository.saveAll(pedido.getItens());
        //emailService.sendOrderConfirmationEmail(pedido);
        return pedido;
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

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Cliente cliente = clienteService.findById(user.getId());
        return pedidoRepository.findAll(pageRequest);
    }
    
        public List<Pedido> findByClienteId(Long id) {
        return pedidoRepository.findByClienteId(id);
    }

}
