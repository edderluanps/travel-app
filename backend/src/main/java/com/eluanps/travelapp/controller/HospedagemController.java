package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.entity.Hospedagem;
import com.eluanps.travelapp.service.HospedagemService;
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
@RequestMapping("/api/hospedagem")
public class HospedagemController {

    @Autowired
    HospedagemService hospedagemService;

    @GetMapping
    public List<Hospedagem> getAll() {
        return hospedagemService.getAll();
    }

    @GetMapping("/{id}")
    public Hospedagem findById(@PathVariable Long id) {
        return hospedagemService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hospedagem salvar(@RequestBody @Validated Hospedagem hotel) {
        return hospedagemService.salvar(hotel);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Hospedagem hotel) {
        hospedagemService.atualizar(id, hotel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        hospedagemService.delete(id);
    }

}
