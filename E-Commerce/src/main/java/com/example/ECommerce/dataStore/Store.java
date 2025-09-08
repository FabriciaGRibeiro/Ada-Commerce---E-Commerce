package com.example.ECommerce.dataStore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.ECommerce.model.Cliente;
import com.example.ECommerce.model.Pedido;
import com.example.ECommerce.model.Produto;

public class Store {
    private final Map<Integer, Cliente> cliente;
    private final Map<Integer, Produto> produtos;
    public final Map<Integer, Pedido> pedidos;
    private int nextClienteID;
    private int nextProdutoID;
    private final int nextPedido;


    public Store() {
        this.cliente = new HashMap<>();
        this.produtos = new HashMap<>();
        this.pedidos = new HashMap<>();
        this.nextClienteID = 1;
        this.nextProdutoID = 1;
        this.nextPedido = 1;
    }

    public Cliente addCliente(Cliente cliente) {
        cliente.put(nextClienteID, cliente);
        nextClienteID++;
        return cliente;
    }

    public Cliente getCliente(int clientId) {
        return cliente.get(clientId);
    }

    public Collection<Cliente> listClientes() {
        return cliente.values();
    }


    public boolean updateCliente(int clienteId, String nome, String cpf) {
        Cliente clientes = cliente.get(clienteId);
        if (cliente != null) {
            if (nome != null) {
                ((Cliente) clientes).setNome(nome);
            }
            return true;
        }
        return false;
    }

    public Produto addProduto(Produto produto) {
        produto.setId(nextProdutoID);
        produtos.put(nextProdutoID, produto);
        nextProdutoID++;
        return produto;
    }

    public Produto getProduto(int produtoId) {
        return produtos.get(produtoId);
    }

    public Collection<Produto> listProdutos() {
        return produtos.values();

    }

    public Pedido getPedido(int pedidoID) {
        return pedidos.get(pedidoID);
    }

    public boolean updatePedido(int pedidoId, String status, String statusPagamento, String statusEntrega) {
        Pedido pedido = getPedido(pedidoId);
        if (pedido != null) {
            if (status != null) {
                pedido.setStatus(status);
            }
            if (statusPagamento != null) {
                pedido.setStatusPagamento(statusPagamento);
            }
            if (statusEntrega != null) {
                pedido.setStatusEntrega(statusEntrega);
            }
            return true;
        }
        return false;

    }

    public boolean updateProduto(int produtoId, String nome, Double preco) {
       
        throw new UnsupportedOperationException("Unimplemented method 'updateProduto'");
    }

    public Pedido addPedido(Pedido pedido) {
      
      throw new UnsupportedOperationException("Unimplemented method 'addPedido'");
    }

    public Collection<Pedido> listPedidos() {
     
      throw new UnsupportedOperationException("Unimplemented method 'listPedidos'");
    }
}







































































