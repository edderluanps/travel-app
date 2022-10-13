package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.controller.util.URL;
import com.eluanps.travelapp.entity.Pacote;
import com.eluanps.travelapp.service.PacoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pacote")
public class PacoteController {

    @Autowired
    private PacoteService pacoteService;
/*
    @GetMapping
    public List<Pacote> getAll() {
        return pacoteService.getAll();
    }
*/
    
    @GetMapping("/{id}")
    public Pacote findById(@PathVariable Long id) {
        return pacoteService.findById(id);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pacote salvar(@RequestBody @Validated Pacote pacote) {
        return pacoteService.salvar(pacote);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Pacote pacote) {
        pacoteService.atualizar(id, pacote);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        pacoteService.delete(id);
    }

    @GetMapping
    public Page<Pacote> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageRows", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nomeDecoded = URL.decodeParam(nome);
        return pacoteService.search(nomeDecoded, page, linesPerPage, orderBy, direction);

    }

}
