package com.eluanps.travelapp.repository;

import com.eluanps.travelapp.entity.Cidade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT c FROM Cidade c WHERE c.estado.id = :estadoId ORDER BY c.nome")
    public List<Cidade> findCidades(@Param("estadoId") Long estado_id);

}
