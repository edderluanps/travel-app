package com.eluanps.travelapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_item_pedido")
@NoArgsConstructor
public class ItemPedido {

    @JsonIgnore
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    private double desconto;
    private int quantidade;
    private double preco;

    public ItemPedido(Pedido pedido, Pacote pacote, double desconto, int quantidade, double preco) {
        id.setPedido(pedido);
        id.setPacote(pacote);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }
    
    public Pacote getPacote() {
        return id.getPacote();
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
