package com.eluanps.travelapp.service.validation;

import com.eluanps.travelapp.controller.exceptions.FieldMessage;
import com.eluanps.travelapp.entity.Cliente;
import com.eluanps.travelapp.entity.dto.ClienteNewDTO;
import com.eluanps.travelapp.entity.enums.TipoCliente;
import com.eluanps.travelapp.repository.ClienteRepository;
import com.eluanps.travelapp.service.validation.util.BR;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert cli) {
    }

    @Override
    public boolean isValid(ClienteNewDTO clienteNewDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (clienteNewDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(clienteNewDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (clienteNewDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(clienteNewDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Cliente cliente = clienteRepository.findByEmail(clienteNewDto.getEmail());
        if (cliente != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
