package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.entity.Estado;
import com.eluanps.travelapp.service.EstadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    EstadoService estadoService;

    @GetMapping
    public List<Estado> getAll() {
        return estadoService.getAll();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado salvar(@RequestBody @Validated Estado estado) {
        return estadoService.salvar(estado);
    }

}
