package com.eluanps.travelapp.repository;

import com.eluanps.travelapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
    
}
