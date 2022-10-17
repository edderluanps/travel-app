package com.eluanps.travelapp.repository;

import com.eluanps.travelapp.entity.Estado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    @Transactional(readOnly = true)
    public List<Estado> findAllByOrderByNome();

}
