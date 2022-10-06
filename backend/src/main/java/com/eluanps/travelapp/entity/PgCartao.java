package com.eluanps.travelapp.entity;

import com.eluanps.travelapp.entity.enums.PagamentoStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pg_cartao")
@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("pgBoleto")
public class PgCartao extends Pagamento{
    
    private int numParcelas;

    public PgCartao(int numParcelas, Long id, PagamentoStatus status, Pedido pedido) {
        super(id, status, pedido);
        this.numParcelas = numParcelas;
    }
 
}
