document.addEventListener('DOMContentLoaded', () => {
    // Funções para carregar dados iniciais (simulados por enquanto)
    carregarClientes();
    carregarProdutos();
    carregarPedidos();
});

// --- Funções de Cliente ---
function cadastrarCliente() {
    const nome = document.getElementById('clienteNome').value;
    const documento = document.getElementById('clienteDocumento').value;

    if (!nome || !documento) {
        alert('Nome e Documento são obrigatórios para o cliente.');
        return;
    }

    // Simulação de chamada de API
    console.log(`Cadastrando cliente: ${nome}, Documento: ${documento}`);
    const cliente = { id: Date.now(), nome, documento }; // ID simulado
    adicionarClienteNaLista(cliente);

    document.getElementById('clienteNome').value = '';
    document.getElementById('clienteDocumento').value = '';
    alert('Cliente cadastrado com sucesso!');
}

function carregarClientes() {
    // Simulação de carregamento de clientes de uma API
    const clientes = [
        { id: 1, nome: 'João Silva', documento: '111.222.333-44' },
        { id: 2, nome: 'Maria Oliveira', documento: '555.666.777-88' }
    ];
    clientes.forEach(adicionarClienteNaLista);
}

function adicionarClienteNaLista(cliente) {
    const lista = document.getElementById('listaClientes');
    const li = document.createElement('li');
    li.textContent = `ID: ${cliente.id}, Nome: ${cliente.nome}, Documento: ${cliente.documento}`;
    lista.appendChild(li);
}

// --- Funções de Produto ---
function cadastrarProduto() {
    const nome = document.getElementById('produtoNome').value;
    const preco = parseFloat(document.getElementById('produtoPreco').value);

    if (!nome || isNaN(preco) || preco <= 0) {
        alert('Nome e Preço (maior que zero) são obrigatórios para o produto.');
        return;
    }

    // Simulação de chamada de API
    console.log(`Cadastrando produto: ${nome}, Preço: ${preco}`);
    const produto = { id: Date.now(), nome, preco }; // ID simulado
    adicionarProdutoNaLista(produto);

    document.getElementById('produtoNome').value = '';
    document.getElementById('produtoPreco').value = '';
    alert('Produto cadastrado com sucesso!');
}

function carregarProdutos() {
    // Simulação de carregamento de produtos de uma API
    const produtos = [
        { id: 101, nome: 'Notebook', preco: 2500.00 },
        { id: 102, nome: 'Mouse', preco: 50.00 }
    ];
    produtos.forEach(adicionarProdutoNaLista);
}

function adicionarProdutoNaLista(produto) {
    const lista = document.getElementById('listaProdutos');
    const li = document.createElement('li');
    li.textContent = `ID: ${produto.id}, Nome: ${produto.nome}, Preço: R$ ${produto.preco.toFixed(2)}`;
    lista.appendChild(li);
}

// --- Funções de Pedido ---
function criarPedido() {
    const clienteId = parseInt(document.getElementById('pedidoClienteId').value);

    if (isNaN(clienteId)) {
        alert('ID do Cliente é obrigatório para criar um pedido.');
        return;
    }

    // Simulação de chamada de API
    console.log(`Criando pedido para Cliente ID: ${clienteId}`);
    const pedido = { id: Date.now(), clienteId, status: 'aberto', itens: [] }; // Pedido simulado
    adicionarPedidoNaLista(pedido);

    document.getElementById('pedidoClienteId').value = '';
    alert('Pedido criado com sucesso!');
}

function carregarPedidos() {
    // Simulação de carregamento de pedidos de uma API
    const pedidos = [
        { id: 1001, clienteId: 1, status: 'aberto', itens: [{ produtoId: 101, quantidade: 1, precoVenda: 2600.00 }] },
        { id: 1002, clienteId: 2, status: 'aguardando_pagamento', itens: [] }
    ];
    pedidos.forEach(adicionarPedidoNaLista);
}

function adicionarPedidoNaLista(pedido) {
    const lista = document.getElementById('listaPedidos');
    const li = document.createElement('li');
    li.innerHTML = `
        <span>ID: ${pedido.id}, Cliente ID: ${pedido.clienteId}, Status: ${pedido.status}</span>
        <button onclick="gerenciarPedido(${pedido.id})">Gerenciar</button>
    `;
    lista.appendChild(li);
}

function gerenciarPedido(pedidoId) {
    alert(`Funcionalidade de gerenciar pedido ${pedidoId} será implementada em breve.`);
    // Aqui você implementaria a lógica para exibir os detalhes do pedido e permitir adicionar/remover itens, finalizar, pagar, etc.
}


