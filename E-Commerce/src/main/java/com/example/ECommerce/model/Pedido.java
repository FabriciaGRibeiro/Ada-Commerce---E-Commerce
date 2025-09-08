package com.example.ECommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private LocalDateTime dataCriacao;
    private String status;
    private String statusPagamento;
    private String statusEntrega;
    private List<ItemPedido> itens;
   


    public Pedido( int id, Cliente cliente) {
    this.id = id;
    this.cliente = cliente;
    this.dataCriacao = LocalDateTime.now();
    this.status = "aberto";
    this.statusPagamento = "pendente";
    this.statusEntrega = "pendente";
    this.itens = new ArrayList<>();
}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public Cliente getCliente() {
    return cliente;
}

public void setCliente(Cliente cliente) {
    this.cliente = cliente;
}
public LocalDateTime getDataCriacao() {
    return dataCriacao;
}
public void setDataCriacao(LocalDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
}

public String getStatus() {
    return status;

}

public void setStatus(String status){
    this.status = status;
}

public String getStatusPagamento() {
    return statusPagamento;
}

public void setStatusPagamento(String statusPagamento) {
    this.statusPagamento = statusPagamento;
}

public String getStatusEntrega() {
    return statusEntrega;
}

public void setStatusEntrega(String statusEntrega) {
    this.statusEntrega = statusEntrega;
}
public List<ItemPedido> getItens() {
    return itens;

}
public void setItens(List<ItemPedido> itens) {
    this.itens = itens;
}

@Override
    public String toString() {
    return "Pedido (Id: " + id + ", Cliente: " + cliente.getNome() + ", Status: " + status + ", Pagamento: " + statusPagamento + ", Entrega: " + statusEntrega + ", Itens: " + itens.size() + ")";
    }
}
