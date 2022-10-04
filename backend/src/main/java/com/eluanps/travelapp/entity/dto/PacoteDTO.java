package com.eluanps.travelapp.entity.dto;

import com.eluanps.travelapp.entity.Pacote;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacoteDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private double preco;

    public PacoteDTO(Pacote pacote) {
        this.id = pacote.getId();
        this.nome = pacote.getNome();
        this.preco = pacote.getPreco();
    }

}
