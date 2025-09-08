# Sistema E-Commerce para Ada Tech (Java)

Este projeto implementa um sistema de E-Commerce básico para a Ada Tech, permitindo o gerenciamento de clientes, produtos e pedidos. O sistema foi desenvolvido em Java, seguindo os princípios de Orientação a Objetos e SOLID, com uma base de dados em memória para simplificar o desenvolvimento.

## Funcionalidades

- **Gerenciamento de Clientes:**
    - Cadastrar novos clientes (com documento de identificação obrigatório).
    - Listar todos os clientes cadastrados.
    - Atualizar informações de clientes existentes.

- **Gerenciamento de Produtos:**
    - Cadastrar novos produtos.
    - Listar todos os produtos cadastrados.
    - Atualizar informações de produtos existentes.

- **Gerenciamento de Pedidos:**
    - Criar novos pedidos associados a um cliente.
    - Adicionar, remover e alterar a quantidade de itens em um pedido (apenas se o pedido estiver com status "aberto").
    - Finalizar um pedido (requer ao menos um item e valor total maior que zero; altera o status para "aguardando_pagamento" e notifica o cliente por e-mail).
    - Processar o pagamento de um pedido (apenas se o status for "aguardando_pagamento"; altera o status para "pago" e notifica o cliente por e-mail).
    - Entregar um pedido (apenas se o status de pagamento for "pago"; altera o status para "finalizado" e notifica o cliente por e-mail).

## Estrutura do Projeto

O projeto é um projeto Maven e possui a seguinte estrutura de diretórios:

```
SRC
├── main
│   └── java
│       └── com
│           └── example
│               └── ECommerce
│                   ├── dataStore
│                   ├── model
│                   │   ├── Cliente.java
│                   │   ├── ItemPedido.java
│                   │   ├── Pedido.java
│                   │   └── Produto.java
│                   ├── resources
│                   └── services
│                   ├── App.java
│                   └── ECommerceApplication.java (se aplicável, para Spring Boot, por exemplo)
└── test
```

- `src/main/java/com/example/ECommerce/model/`: Contém as classes de modelo (Cliente, Produto, ItemPedido, Pedido).
- `src/main/java/com/example/ECommerce/dataStore/DataStore.java`: Implementa a camada de persistência em memória para as entidades.
- `src/main/java/com/example/ECommerce/services/ECommerceService.java`: Contém a lógica de negócio e as regras de validação para as operações do E-Commerce.
- `src/main/java/com/example/ECommerce/App.java`: Interface de linha de comando para interagir com o sistema.
- `src/test/java/com/example/ECommerce/services/ECommerceServiceTest.java`: Testes unitários para as funcionalidades do `ECommerceService`.
- `pom.xml`: Arquivo de configuração do Maven.

## Como Executar

1.  **Clone o repositório:**
    ```bash
    git clone <URL_DO_REPOSITORIO>
    cd <NOME_DO_REPOSITORIO>
    ```

2.  **Compile e execute o sistema (via linha de comando):**
    ```bash
    mvn clean install
    java -cp target/classes com.example.ECommerce.App
    ```

3.  **Interaja com o menu:**
    Siga as opções apresentadas no terminal para gerenciar clientes, produtos e pedidos.

## Testes

Para executar os testes unitários, utilize o seguinte comando:

```bash
mvn test
```

## Princípios de Design

Este projeto busca aplicar os seguintes princípios:

-   **SOLID:**
    -   **Single Responsibility Principle (SRP):** Cada classe e módulo tem uma única responsabilidade bem definida (ex: `DataStore` para persistência, `ECommerceService` para regras de negócio).
    -   **Open/Closed Principle (OCP):** O sistema é aberto para extensão (ex: novas formas de notificação poderiam ser adicionadas sem modificar `ECommerceService`) e fechado para modificação.
    -   **Liskov Substitution Principle (LSP):** (Aplicado implicitamente através do uso de herança e polimorfismo, se aplicável em futuras extensões).
    -   **Interface Segregation Principle (ISP):** (Aplicado implicitamente, evitando interfaces "gordas" e focando em responsabilidades específicas).
    -   **Dependency Inversion Principle (DIP):** As dependências são injetadas (ex: `ECommerceService` recebe `DataStore` no construtor), promovendo baixo acoplamento.

-   **Orientação a Objetos:**
    -   **Encapsulamento:** Atributos das classes são privados ou acessados via métodos.
    -   **Herança:** (Pode ser explorada em futuras extensões, como diferentes tipos de produtos ou clientes).
    -   **Polimorfismo:** (Pode ser explorada em futuras extensões).
    -   **Abstração:** Modelos de dados representam entidades do mundo real de forma simplificada.
 
    -   Autor: Fabrícia Graziele Ribeiro
