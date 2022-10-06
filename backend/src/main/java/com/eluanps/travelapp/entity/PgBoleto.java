package com.eluanps.travelapp.entity;

import com.eluanps.travelapp.entity.enums.PagamentoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pg_boleto")
@Getter
@Setter
@NoArgsConstructor
@JsonTypeName("pgBoleto")
public class PgBoleto extends Pagamento{

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date pagamento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date vencimento;

    public PgBoleto(Long id, Date pagamento, Date vencimento, PagamentoStatus status, Pedido pedido) {
        super(id, status, pedido);
        this.pagamento = pagamento;
        this.vencimento = vencimento;
    }

    
    
    

}
