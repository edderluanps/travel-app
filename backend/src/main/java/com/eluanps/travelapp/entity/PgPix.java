package com.eluanps.travelapp.entity;

import com.eluanps.travelapp.entity.enums.PagamentoStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pg_pix")
@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("pgPix")
public class PgPix extends Pagamento{
    
    private String chave;

    public PgPix(String chave, Long id, PagamentoStatus status, Pedido pedido) {
        super(id, status, pedido);
        this.chave = chave;
    }
    
}
