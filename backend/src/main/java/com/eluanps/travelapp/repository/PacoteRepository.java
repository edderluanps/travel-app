package com.eluanps.travelapp.repository;

import com.eluanps.travelapp.entity.Pacote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PacoteRepository extends JpaRepository<Pacote, Long>{
    
    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Pacote obj WHERE obj.nome LIKE %:nome%")
    Page<Pacote> findByNome(@Param("nome") String nome, Pageable pageRequest);
    
}
