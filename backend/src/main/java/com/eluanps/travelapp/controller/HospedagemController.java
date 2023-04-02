package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.entity.Hospedagem;
import com.eluanps.travelapp.service.HospedagemService;
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
@RequestMapping("/api/hospedagem")
public class HospedagemController {

    @Autowired
    private HospedagemService hospedagemService;

    @ApiOperation(value = "Listagem de Hospedagens")
    @GetMapping
    public List<Hospedagem> getAll() {
        return hospedagemService.getAll();
    }

    @ApiOperation(value = "Busca Hospedagem por ID")
    @GetMapping("/{id}")
    public Hospedagem findById(@PathVariable Long id) {
        return hospedagemService.findById(id);
    }

    @ApiOperation(value = "Cadastra Hospedagem")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hospedagem salvar(@RequestBody @Validated Hospedagem hotel) {
        return hospedagemService.salvar(hotel);
    }

    @ApiOperation(value = "Edita Hospedagem")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Hospedagem hotel) {
        hospedagemService.atualizar(id, hotel);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Não é possível excluir uma Hospedagem vinculada a um Pacote"),
        @ApiResponse(code = 400, message = "Hospedagem inexistente.")  
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        hospedagemService.delete(id);
    }

}
