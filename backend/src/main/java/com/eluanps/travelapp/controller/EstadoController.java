package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.entity.Cidade;
import com.eluanps.travelapp.entity.Estado;
import com.eluanps.travelapp.entity.dto.CidadeDTO;
import com.eluanps.travelapp.service.CidadeService;
import com.eluanps.travelapp.service.EstadoService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Estado> getAll() {
        return estadoService.getAll();
    }

    @GetMapping("/{estadoId}/cidades")
    public List<CidadeDTO> findCidades(@PathVariable Long estadoId) {
        List<Cidade> list = cidadeService.findByEstado(estadoId);
        List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
        return listDto;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado salvar(@RequestBody @Validated Estado estado) {
        return estadoService.salvar(estado);
    }

}
