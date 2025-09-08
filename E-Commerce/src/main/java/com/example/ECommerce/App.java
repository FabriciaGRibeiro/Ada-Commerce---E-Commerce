package com.example.ECommerce;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.example.ECommerce.dataStore.Store;
import com.example.ECommerce.model.Cliente;
import com.example.ECommerce.model.Pedido;
import com.example.ECommerce.model.Produto;
import com.example.ECommerce.services.ECommerceService;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Store dataStore = new Store();
    private static final ECommerceService service = new ECommerceService(dataStore);

    public static void main(String[] args) {
        try (scanner) {
            int choice;
            do {
                printMainMenu();
                choice = getUserChoice();
                
                switch (choice) {
                    case 1 -> gerenciarClientes();
                    case 2 -> gerenciarProdutos();
                    case 3 -> gerenciarPedidos();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } while (choice != 0);
        }
    }

    private static void printMainMenu() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Gerenciar Clientes");
        System.out.println("2. Gerenciar Produtos");
        System.out.println("3. Gerenciar Pedidos");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int getUserChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.next();
            return -1;
        }
    }

    private static void gerenciarClientes() {
        int choice;
        do {
            System.out.println("\n--- Gerenciar Clientes ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Atualizar Cliente");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            choice = getUserChoice();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Nome do Cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Documento do Cliente: ");
                    String documento = scanner.nextLine();
                    try {
                        Cliente cliente = service.cadastrarCliente(nome, documento);
                        System.out.println("Cliente cadastrado com sucesso: " + cliente);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Clientes Cadastrados ---");
                    if (service.listarClientes().isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        service.listarClientes().forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("ID do Cliente a ser atualizado: ");
                    int clienteId = getUserChoice();
                    scanner.nextLine();
                    System.out.print("Novo Nome (deixe em branco para não alterar): ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo Documento (deixe em branco para não alterar): ");
                    String novoDocumento = scanner.nextLine();
                    try {
                        if (service.atualizarCliente(clienteId, novoNome.isEmpty() ? null : novoNome, novoDocumento.isEmpty() ? null : novoDocumento)) {
                            System.out.println("Cliente atualizado com sucesso.");
                        } else {
                            System.out.println("Cliente não encontrado.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 0 -> {
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (choice != 0);
    }

    private static void gerenciarProdutos() {
        int choice;
        do {
            System.out.println("\n--- Gerenciar Produtos ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            choice = getUserChoice();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> {
                    System.out.print("Nome do Produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preço do Produto: ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine(); 
                    Produto produto = service.cadastrarProduto(nome, preco);
                    System.out.println("Produto cadastrado com sucesso: " + produto);
                }
                case 2 -> {
                    System.out.println("\n--- Produtos Cadastrados ---");
                    if (service.listarProdutos().isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                    } else {
                        service.listarProdutos().forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("ID do Produto a ser atualizado: ");
                    int produtoId = getUserChoice();
                    scanner.nextLine(); 
                    System.out.print("Novo Nome (deixe em branco para não alterar): ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo Preço (deixe em branco para não alterar): ");
                    String novoPrecoStr = scanner.nextLine();
                    Double novoPreco = novoPrecoStr.isEmpty() ? null : Double.valueOf(novoPrecoStr);
                    if (service.atualizarProduto(produtoId, novoNome.isEmpty() ? null : novoNome, novoPreco)) {
                        System.out.println("Produto atualizado com sucesso.");
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                }
                case 0 -> {
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (choice != 0);
    }

    private static void gerenciarPedidos() {
        int choice;
        do {
            System.out.println("\n--- Gerenciar Pedidos ---");
            System.out.println("1. Criar Pedido");
            System.out.println("2. Listar Pedidos");
            System.out.println("3. Gerenciar Pedido Específico");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            choice = getUserChoice();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> {
                    System.out.print("ID do Cliente para o novo pedido: ");
                    int clienteId = getUserChoice();
                    scanner.nextLine(); 
                    try {
                        Pedido pedido = service.criarPedido(clienteId);
                        System.out.println("Pedido criado com sucesso: " + pedido);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Pedidos Cadastrados ---");
                    if (service.listarPedidos().isEmpty()) {
                        System.out.println("Nenhum pedido cadastrado.");
                    } else {
                        service.listarPedidos().forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("ID do Pedido a gerenciar: ");
                    int pedidoId = getUserChoice();
                    scanner.nextLine(); 
                    Pedido pedido = service.getPedido(pedidoId);
                    if (pedido != null) {
                        gerenciarPedidoEspecifico(pedido);
                    } else {
                        System.out.println("Pedido não encontrado.");
                    }
                }
                case 0 -> {
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (choice != 0);
    }

    private static void gerenciarPedidoEspecifico(Pedido pedido) {
        int choice;
        do {
            System.out.println("\n--- Gerenciar Pedido " + pedido.getId() + " (Status: " + pedido.getStatus() + ", Pagamento: " + pedido.getStatusPagamento() + ", Entrega: " + pedido.getStatusEntrega() + ") ---");
            System.out.println("1. Adicionar Item");
            System.out.println("2. Remover Item");
            System.out.println("3. Alterar Quantidade de Item");
            System.out.println("4. Finalizar Pedido");
            System.out.println("5. Pagar Pedido");
            System.out.println("6. Entregar Pedido");
            System.out.println("7. Ver Itens do Pedido");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            choice = getUserChoice();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> {
                    System.out.print("ID do Produto: ");
                    int produtoId = getUserChoice();
                    System.out.print("Quantidade: ");
                    int quantidade = getUserChoice();
                    System.out.print("Preço de Venda: ");
                    double precoVenda = scanner.nextDouble();
                    scanner.nextLine(); 
                    try {
                        service.adicionarItemPedido(pedido.getId(), produtoId, quantidade, precoVenda);
                        System.out.println("Item adicionado com sucesso.");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("ID do Produto a remover: ");
                    int produtoId = getUserChoice();
                    scanner.nextLine(); 
                    try {
                        service.removerItemPedido(pedido.getId(), produtoId);
                        System.out.println("Item removido com sucesso.");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("ID do Produto: ");
                    int produtoId = getUserChoice();
                    System.out.print("Nova Quantidade: ");
                    int novaQuantidade = getUserChoice();
                    scanner.nextLine(); 
                    try {
                        service.alterarQuantidadeItemPedido(pedido.getId(), produtoId, novaQuantidade);
                        System.out.println("Quantidade alterada com sucesso.");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        service.finalizarPedido(pedido.getId());
                        System.out.println("Pedido finalizado com sucesso.");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 5 -> {
                    try {
                        service.pagarPedido(pedido.getId());
                        System.out.println("Pedido pago com sucesso.");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 6 -> {
                    try {
                        service.entregarPedido(pedido.getId());
                        System.out.println("Pedido entregue com sucesso.");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                case 7 -> {
                    if (pedido.getItens().isEmpty()) {
                        System.out.println("Nenhum item neste pedido.");
                    } else {
                        pedido.getItens().forEach(System.out::println);
                    }
                }
                case 0 -> {
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (choice != 0);
    }
}
