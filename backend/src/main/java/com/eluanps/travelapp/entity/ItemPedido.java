package com.eluanps.travelapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.text.NumberFormat;
import java.util.Locale;
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
    
    public double getSubtotal(){
        return (preco - desconto) * quantidade;
    }
    
    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }
    
    public void setPedido(Pedido pedido){
        id.setPedido(pedido);
    }
    
    public Pacote getPacote() {
        return id.getPacote();
    }
    
    public void setPacote(Pacote pacote){
        id.setPacote(pacote);
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

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        StringBuilder sb = new StringBuilder();
        sb.append(getPacote().getNome());
        sb.append(", Qtd.: ");
        sb.append(getQuantidade());
        sb.append(", Preço Unitário: ");
        sb.append(nf.format(getPreco()));
        sb.append(", Subtotal: ");
        sb.append(nf.format(getSubtotal()));
        sb.append("\n");
        return sb.toString();
    }
    
}
