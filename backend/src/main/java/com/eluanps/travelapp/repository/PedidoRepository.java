package com.eluanps.travelapp.repository;

import com.eluanps.travelapp.entity.Cliente;
import com.eluanps.travelapp.entity.Pedido;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
    @Transactional(readOnly = true)
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
    
    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Pedido obj WHERE obj.cliente.id =:id")
    List<Pedido> findByClienteId(@Param("id")Long id);
    
}
