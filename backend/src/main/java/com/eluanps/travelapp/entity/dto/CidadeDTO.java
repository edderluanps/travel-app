package com.eluanps.travelapp.entity.dto;

import com.eluanps.travelapp.entity.Cidade;
import java.io.Serializable;

public class CidadeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;

    public CidadeDTO() {
    }

    public CidadeDTO(Cidade cidade) {
        id = cidade.getId();
        nome = cidade.getNome();
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
