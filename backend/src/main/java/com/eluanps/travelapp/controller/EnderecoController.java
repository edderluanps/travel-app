package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.entity.Endereco;
import com.eluanps.travelapp.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @ApiOperation(value = "Listagem de Endereços")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Endereco> getAll() {
        return enderecoService.getAll();
    }

    @ApiOperation(value = "Busca Endereço por ID")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Endereco findById(@PathVariable Long id) {
        return enderecoService.findById(id);
    }

    @ApiOperation(value = "Cadastra Endereço")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco salvar(@RequestBody @Validated Endereco endereco) {
        return enderecoService.salvar(endereco);
    }

    @ApiOperation(value = "Edita Endereço")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
        enderecoService.atualizar(id, endereco);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Endereço inexistente.")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        enderecoService.delete(id);
    }

}
