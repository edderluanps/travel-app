package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.entity.Empresa;
import com.eluanps.travelapp.service.EmpresaService;
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
@RequestMapping("/api/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @ApiOperation(value = "Listagem de Usuarios")
    @GetMapping
    public List<Empresa> getAll() {
        return empresaService.getAll();
    }

    @ApiOperation(value = "Busca Empresa por ID") 
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Empresa findById(@PathVariable Long id) {
        return empresaService.findById(id);
    }

    @ApiOperation(value = "Cadastra Empresa")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa salvar(@RequestBody @Validated Empresa empresa) {
        return empresaService.salvar(empresa);
    }

    @ApiOperation(value = "Edita Empresa")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Empresa empresa) {
        empresaService.atualizar(id, empresa);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Não é possível excluir uma Empresa vinculada a um serviço ativo"),
        @ApiResponse(code = 400, message = "Empresa inexistente.")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        empresaService.delete(id);
    }

}
