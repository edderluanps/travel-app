package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.entity.Pacote;
import com.eluanps.travelapp.entity.dto.PacoteDTO;
import com.eluanps.travelapp.service.PacoteService;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/api/pacote")
public class PacoteController {

    @Autowired
    PacoteService pacoteService;

    @GetMapping
    public List<PacoteDTO> getAll() {
        List<Pacote> lista = pacoteService.getAll();
        List<PacoteDTO> listaDto = lista.stream().map(obj -> new PacoteDTO(obj)).collect(Collectors.toList());
        return listaDto;
    }

    @GetMapping("/{id}")
    public Pacote findById(@PathVariable Long id) {
        return pacoteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pacote salvar(@RequestBody @Validated Pacote pacote) {
        return pacoteService.salvar(pacote);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Pacote pacote) {
        pacoteService.atualizar(id, pacote);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        pacoteService.delete(id);
    }

}
