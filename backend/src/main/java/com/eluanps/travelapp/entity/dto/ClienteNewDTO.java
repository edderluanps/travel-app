package com.eluanps.travelapp.entity.dto;

import com.eluanps.travelapp.service.validation.ClienteInsert;
import java.io.Serializable;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ClienteInsert
public class ClienteNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 100, message = "Campo deve conter de 5 à 100 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    @Column(unique = true)
    private String email;
    
    @Column(unique = true)
    @NotEmpty(message = "Preenchimento obrigatório")
    private String cpfOuCnpj;

    private Integer tipo;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String dataNascimento;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String dataCadastro;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 20, message = "Campo deve conter de 5 à 20 caracteres")
    private String senha;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String logradouro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String numero;

    private String complemento;

    private String bairro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cep;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String telefone1;

    private String telefone2;

    private String telefone3;

    private Integer cidadeId;

}
