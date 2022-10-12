package com.eluanps.travelapp.entity.dto;

import com.eluanps.travelapp.entity.Cliente;
import com.eluanps.travelapp.service.validation.ClienteUpdate;
import java.io.Serializable;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ClienteUpdate
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Campo obrigatório")
    private String nome;
    
    @NotEmpty(message = "Campo obrigatório")
    @Email(message = "Email inválido")
    @Column(unique=true)
    private String email;

    public ClienteDTO() {
    }
    
    public ClienteDTO(Cliente cliente) {
        id = cliente.getId();
        nome = cliente.getNome();
        email = cliente.getEmail();
    }

}
