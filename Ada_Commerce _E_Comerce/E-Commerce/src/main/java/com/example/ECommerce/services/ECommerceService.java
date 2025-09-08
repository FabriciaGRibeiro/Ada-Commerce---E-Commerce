package com.example.ECommerce.services;

import com.example.ECommerce.dataStore.Store;
import com.example.ECommerce.model.Cliente;
import com.example.ECommerce.model.ItemPedido;
import com.example.ECommerce.model.Pedido;
import com.example.ECommerce.model.Produto;

import java.util.Collection;
import java.util.Optional;

public class ECommerceService {
    private final Store dataStore;

    public ECommerceService(Store dataStore) {
        this.dataStore = dataStore;
    }

    public Cliente cadastrarCliente(String nome, String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("Cliente precisa ter documento de identificação.");
        }
        Cliente cliente = new Cliente(0, nome, documento);
        return dataStore.addCliente(cliente);
    }

    public Collection<Cliente> listarClientes() {
        return dataStore.listClientes();
    }

    public boolean atualizarCliente(int clienteId, String nome, String documento) {
        return dataStore.updateCliente(clienteId, nome, documento);
    }

    public Produto cadastrarProduto(String nome, double preco) {
        Produto produto = new Produto(0, nome, preco);
        return dataStore.addProduto(produto);
    }

    public Collection<Produto> listarProdutos() {
        return dataStore.listProdutos();
    }

    public boolean atualizarProduto(int produtoId, String nome, Double preco) {
        return dataStore.updateProduto(produtoId, nome, preco);
    }

    public Pedido criarPedido(int clienteId) {
        Cliente cliente = dataStore.getCliente(clienteId);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        Pedido pedido = new Pedido(0, cliente);
        return dataStore.addPedido(pedido);
    }

    public Pedido adicionarItemPedido(int pedidoId, int produtoId, int quantidade, double precoVenda) {
        Pedido pedido = dataStore.getPedido(pedidoId);
        Produto produto = dataStore.getProduto(produtoId);

        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        if (!pedido.getStatus().equals("aberto")) {
            throw new IllegalStateException("Não é possível adicionar itens a um pedido que não esteja aberto.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        Optional<ItemPedido> existingItem = pedido.getItens().stream()
                .filter(item -> item.getProduto().getId() == produtoId)
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantidade(existingItem.get().getQuantidade() + quantidade);
        } else {
            ItemPedido itemPedido = new ItemPedido(produto, quantidade);
            pedido.getItens().add(itemPedido);
        }
        return pedido;
    }

    public Pedido removerItemPedido(int pedidoId, int produtoId) {
        Pedido pedido = dataStore.getPedido(pedidoId);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }
        if (!pedido.getStatus().equals("aberto")) {
            throw new IllegalStateException("Não é possível remover itens de um pedido que não esteja aberto.");
        }

        boolean removed = pedido.getItens().removeIf(item -> item.getProduto().getId() == produtoId);
        if (!removed) {
            throw new IllegalArgumentException("Item não encontrado no pedido.");
        }
        return pedido;
    }

    public Pedido alterarQuantidadeItemPedido(int pedidoId, int produtoId, int novaQuantidade) {
        Pedido pedido = dataStore.getPedido(pedidoId);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }
        if (!pedido.getStatus().equals("aberto")) {
            throw new IllegalStateException("Não é possível alterar a quantidade de itens em um pedido que não esteja aberto.");
        }
        if (novaQuantidade <= 0) {
            throw new IllegalArgumentException("A nova quantidade deve ser maior que zero.");
        }

        Optional<ItemPedido> existingItem = pedido.getItens().stream()
                .filter(item -> item.getProduto().getId() == produtoId)
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantidade(novaQuantidade);
        } else {
            throw new IllegalArgumentException("Item não encontrado no pedido.");
        }
        return pedido;
    }

    private void notificarCliente(Cliente cliente, String mensagem) {
        System.out.println("E-mail para " + cliente.getNome() + " (" + cliente.getDocumento() + "): " + mensagem);
    }

    public Pedido finalizarPedido(int pedidoId) {
        Pedido pedido = dataStore.getPedido(pedidoId);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }
        if (pedido.getItens().isEmpty()) {
            throw new IllegalStateException("Pedido deve ter ao menos um item para ser finalizado.");
        }

        double totalValor = pedido.getItens().stream()
                .mapToDouble(item -> item.getQuantidade() * item.getPrecoVenda())
                .sum();

        if (totalValor <= 0) {
            throw new IllegalStateException("O valor total do pedido deve ser maior que zero para ser finalizado.");
        }

        pedido.setStatus("aguardando_pagamento");
        pedido.setStatusPagamento("aguardando_pagamento");
        dataStore.updatePedido(pedidoId, "aguardando_pagamento", "aguardando_pagamento", null);
        notificarCliente(pedido.getCliente(), "Seu pedido " + pedido.getId() + " foi finalizado e está aguardando pagamento. Valor total: R$ " + String.format("%.2f", totalValor));
        return pedido;
    }

    public Pedido pagarPedido(int pedidoId) {
        Pedido pedido = dataStore.getPedido(pedidoId);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }
        if (!pedido.getStatusPagamento().equals("aguardando_pagamento")) {
            throw new IllegalStateException("O pedido não está no status 'Aguardando pagamento' para ser pago.");
        }

        pedido.setStatusPagamento("pago");
        dataStore.updatePedido(pedidoId, null, "pago", null);
        notificarCliente(pedido.getCliente(), "Seu pedido " + pedido.getId() + " foi pago com sucesso!");
        return pedido;
    }

    public Pedido entregarPedido(int pedidoId) {
        Pedido pedido = dataStore.getPedido(pedidoId);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }
        if (!pedido.getStatusPagamento().equals("pago")) {
            throw new IllegalStateException("O pedido precisa estar pago para ser entregue.");
        }

        pedido.setStatusEntrega("finalizado");
        pedido.setStatus("finalizado");
        dataStore.updatePedido(pedidoId, "finalizado", null, "finalizado");
        notificarCliente(pedido.getCliente(), "Seu pedido " + pedido.getId() + " foi entregue com sucesso!\nObrigado por comprar conosco!");
        return pedido;
    }

    public Collection<Pedido> listarPedidos() {
        return dataStore.listPedidos();
    }

    public Pedido getPedido(int pedidoId) {
        return dataStore.getPedido(pedidoId);
    }
}


