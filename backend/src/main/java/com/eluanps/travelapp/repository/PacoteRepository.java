package com.eluanps.travelapp.repository;

import com.eluanps.travelapp.entity.Pacote;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PacoteRepository extends JpaRepository<Pacote, Long>{
    
    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Pacote obj WHERE obj.nome LIKE %:nome%")
    List<Pacote> findByNome(@Param("nome") String nome);
    
    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Pacote obj WHERE obj.nome LIKE %:nome% AND obj.voos.dataDesembarque =:data")
    List<Pacote> findByNomeAndDate(@Param("nome") String nome, @Param("data") LocalDate data);
    
    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Pacote obj ORDER BY obj.id")
    List<Pacote> findLastThree(Pageable pageable);
    
}
