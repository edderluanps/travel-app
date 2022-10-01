package com.eluanps.travelapp.entity;

import com.eluanps.travelapp.entity.enums.PagamentoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
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
public class PgBoleto extends Pagamento{

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate pagamento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate vencimento;

    public PgBoleto(Long id, LocalDate pagamento, LocalDate vencimento, PagamentoStatus status, Pedido pedido) {
        super(id, status, pedido);
        this.pagamento = pagamento;
        this.vencimento = vencimento;
    }

    
    
    

}
