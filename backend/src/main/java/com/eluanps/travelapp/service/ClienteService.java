package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Cidade;
import com.eluanps.travelapp.entity.Cliente;
import com.eluanps.travelapp.entity.Endereco;
import com.eluanps.travelapp.entity.dto.ClienteDTO;
import com.eluanps.travelapp.entity.dto.ClienteNewDTO;
import com.eluanps.travelapp.entity.enums.TipoCliente;
import com.eluanps.travelapp.entity.enums.TipoPerfil;
import com.eluanps.travelapp.repository.ClienteRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private BCryptPasswordEncoder bpe;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(TipoPerfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }
        return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente) {
        Cliente novoCliente = findById(cliente.getId());
        UpdateCliente(novoCliente, cliente);
        return clienteRepository.save(novoCliente);
    }

    public void delete(Long id) {
        findById(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possivel deletar o Cliente: Usuário Ativo.");
        }
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
        Cliente cliente = new Cliente(null, clienteDto.getNome(), clienteDto.getCpfOuCnpj(), clienteDto.getEmail(), bpe.encode(clienteDto.getSenha()),
                clienteDto.getDataNascimento(), clienteDto.getDataCadastro(), TipoCliente.toEnum(clienteDto.getTipo()), true);

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
