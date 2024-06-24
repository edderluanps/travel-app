package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.entity.Pacote;
import com.eluanps.travelapp.service.PacoteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pacote")
public class PacoteController {

    @Autowired
    private PacoteService pacoteService;

    @ApiOperation(value = "Listagem de Pacotes")
    @GetMapping
    public List<Pacote> getAll() {
        return pacoteService.getAll();
    }

    @ApiOperation(value = "Busca os ultimos 3 Pacotes")
    @GetMapping("/ultimos-pacotes")
    public List<Pacote> getLastThree() {
        return pacoteService.getLastThree();
    }

    @ApiOperation(value = "Busca Pacote por ID")
    @GetMapping("/{id}")
    public Pacote findById(@PathVariable Long id) {
        return pacoteService.findById(id);
    }

    @ApiOperation(value = "Cadastra Pacote")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pacote salvar(@RequestBody @Validated Pacote pacote) {
        return pacoteService.salvar(pacote);
    }

    @ApiOperation(value = "Edita Pacote")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Pacote pacote) {
        pacoteService.atualizar(id, pacote);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Não é possível excluir o Pacote"),
        @ApiResponse(code = 400, message = "Pacote inexistente.")  
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        pacoteService.delete(id);
    }

    @ApiOperation(value = "Pesquisa Pacote")
    @GetMapping("/resultados-pesquisa")
    public List<Pacote> getByKeyword(@RequestParam(value = "nome", defaultValue = "") String nome) {
        return pacoteService.findByNome(nome);
    }
    
    @ApiOperation(value = "Pesquisa Pacote por Nome e Data")
    @GetMapping("/resultados-pesquisa-data")
    public List<Pacote> getByNomeAndDate(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "data", defaultValue = "01/01/2000") LocalDate data) {
        return pacoteService.findByNomeAndDate(nome, data);
    }

}
