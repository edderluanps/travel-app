package com.eluanps.travelapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pedido")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataPedido;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido(Long id, Date dataPedido, Cliente cliente) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.cliente = cliente;
    }

    public double getValorTotal() {
        double soma = 0.0;
        for (ItemPedido ip : itens) {
            soma = soma + ip.getSubtotal();
        }
        return soma;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido nº: ");
        sb.append(getId());
        sb.append(", Data da compra: ");
        sb.append(sdf.format(getDataPedido()));
        sb.append(", Cliente: ");
        sb.append(getCliente().getNome());
        sb.append(", Status: ");
        sb.append(getPagamento().getStatus().getDescricao());
        sb.append(",\n Detalhes: \n");
        for (ItemPedido ip : itens) {
            sb.append(ip.toString());
        }
        sb.append("Valor total: ");
        sb.append(nf.format(getValorTotal()));
        return sb.toString();
    }

}
