package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Cidade;
import com.eluanps.travelapp.entity.Cliente;
import com.eluanps.travelapp.entity.Endereco;
import com.eluanps.travelapp.entity.dto.ClienteDTO;
import com.eluanps.travelapp.entity.dto.ClienteNewDTO;
import com.eluanps.travelapp.entity.enums.TipoCliente;
import com.eluanps.travelapp.repository.ClienteRepository;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente) {
        Cliente novoCliente = findById(cliente.getId());
        UpdateCliente(novoCliente, cliente);
        return clienteRepository.save(novoCliente);
    }

    public void delete(Long id) {
        clienteRepository.findById(id).map(obj -> {
            clienteRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO clienteDto) {
        return new Cliente(clienteDto.getId(), clienteDto.getNome(), null, clienteDto.getEmail(),
                null, null, null, null, false);
    }

    public Cliente fromDTO(ClienteNewDTO clienteDto) {
        Cliente cliente = new Cliente(null, clienteDto.getNome(), clienteDto.getCpfOuCnpj(), clienteDto.getEmail(), clienteDto.getSenha(),
                null, null, TipoCliente.toEnum(clienteDto.getTipo()), true);

        Cidade cidade = new Cidade(clienteDto.getCidadeId().longValue(), null, null);

        Endereco endereco = new Endereco(null, clienteDto.getLogradouro(), clienteDto.getNumero(), clienteDto.getComplemento(),
                clienteDto.getBairro(), clienteDto.getCep(), cliente, cidade, true);

        cliente.getEndereco().add(endereco);
        cliente.getTelefone().add(clienteDto.getTelefone1());
        if (clienteDto.getTelefone2() != null) {
            cliente.getTelefone().add(clienteDto.getTelefone2());
        }
        if (clienteDto.getTelefone3() != null) {
            cliente.getTelefone().add(clienteDto.getTelefone3());
        }
        return cliente;
    }

    private void UpdateCliente(Cliente novoCliente, Cliente cliente) {
        novoCliente.setNome(cliente.getNome());
        novoCliente.setEmail(cliente.getEmail());
    }

}
