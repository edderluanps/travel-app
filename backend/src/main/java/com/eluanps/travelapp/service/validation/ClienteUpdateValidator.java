package com.eluanps.travelapp.service.validation;

import com.eluanps.travelapp.controller.exceptions.FieldMessage;
import com.eluanps.travelapp.entity.Cliente;
import com.eluanps.travelapp.entity.dto.ClienteDTO;
import com.eluanps.travelapp.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public void initializable(ClienteUpdate cli){
        
    }
    
    @Override
    public boolean isValid(ClienteDTO clienteDto, ConstraintValidatorContext context){
        
        Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        
        Integer uriId = Integer.parseInt(map.get("id"));
        
        List<FieldMessage> list = new ArrayList<>();
        
        Cliente cliente =clienteRepository.findByEmail(clienteDto.getEmail());
        
        if(cliente != null && !cliente.getId().equals(uriId)){
            list.add(new FieldMessage("email", "email já existente"));
        }
        
        for (FieldMessage e : list){
            
            context.disableDefaultConstraintViolation();
            
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName());
            
        }
        return list.isEmpty();
    }
    
}