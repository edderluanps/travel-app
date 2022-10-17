package com.eluanps.travelapp.entity.dto;

import com.eluanps.travelapp.entity.Estado;
import java.io.Serializable;

public class EstadoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;

    public EstadoDTO() {
    }

    public EstadoDTO(Estado estado) {
        id = estado.getId();
        nome = estado.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
