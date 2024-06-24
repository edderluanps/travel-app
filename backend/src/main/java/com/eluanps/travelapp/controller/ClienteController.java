package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.entity.Cliente;
import com.eluanps.travelapp.entity.dto.ClienteDTO;
import com.eluanps.travelapp.entity.dto.ClienteNewDTO;
import com.eluanps.travelapp.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @ApiOperation(value = "Listagem de Usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<ClienteDTO> getAll() {
        List<Cliente> lista = clienteService.getAll();
        List<ClienteDTO> listaDto = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return listaDto;
    }

    @ApiOperation(value = "Busca Usuario por ID")    
    @GetMapping("/{id}")
    public Cliente findById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @ApiOperation(value = "Cadastra Usuario")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@Valid @RequestBody ClienteNewDTO clienteDto) {
        Cliente cliente = clienteService.fromDTO(clienteDto);
        cliente = clienteService.salvar(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return cliente;
    }

    @ApiOperation(value = "Edita Usuario")    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@Valid @RequestBody ClienteDTO clienteDto, @PathVariable Long id) {
        Cliente cliente = clienteService.fromDTO(clienteDto);
        cliente.setId(id);
        cliente = clienteService.atualizar(cliente);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Não é possível excluir um Usuário sem possuir previlégios de ADMIN"),
        @ApiResponse(code = 400, message = "Usuário inexistente.")  
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clienteService.delete(id);
    }

    @ApiOperation(value = "Busca Usuario por e-mail")    
    @GetMapping("/email")
    public Cliente find(@RequestParam(value = "value") String email) {
        Cliente cliente = clienteService.findByEmail(email);
        return cliente;
    }

    @ApiOperation(value = "Busca Usuarios com paginação")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/page")
    public Page<ClienteDTO> getPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> lista = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listaDTO = lista.map(obj -> new ClienteDTO(obj));
        return listaDTO;
    }

}
