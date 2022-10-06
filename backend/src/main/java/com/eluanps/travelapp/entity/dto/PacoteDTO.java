package com.eluanps.travelapp.entity.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PacoteDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private double preco;

}
