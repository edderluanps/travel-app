package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.entity.Voos;
import com.eluanps.travelapp.service.VoosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/voos")
public class VoosController {

    @Autowired
    VoosService voosService;

    @GetMapping
    public List<Voos> getAll() {
        return voosService.getAll();
    }

    @GetMapping("/{id}")
    public Voos findById(@PathVariable Long id) {
        return voosService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Voos salvar(@RequestBody @Validated Voos voos) {
        return voosService.salvar(voos);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Voos voos) {
        voosService.atualizar(id, voos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        voosService.delete(id);
    }

}
