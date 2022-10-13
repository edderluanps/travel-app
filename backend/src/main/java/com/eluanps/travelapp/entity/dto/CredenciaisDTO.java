package com.eluanps.travelapp.entity.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO implements Serializable{
    
    public static final long serialVersionUID = 1L;
    
    private String email;
    private String senha;

}
