package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.entity.Pagamento;
import com.eluanps.travelapp.service.PagamentoService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @ApiOperation(value = "Busca Pagamentos do Cliente")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Pagamento> getAll() {
        return pagamentoService.getAll();
    }
    
    @ApiOperation(value = "Cadastra Pagamento")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pagamento salvar(@RequestBody @Validated Pagamento estado) {
        return pagamentoService.salvar(estado);
    }

}
