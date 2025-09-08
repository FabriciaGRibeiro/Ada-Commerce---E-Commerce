Sistema E-Commerce para Ada Tech (Java)

Este projeto implementa um sistema de E-Commerce básico para a Ada Tech, permitindo o gerenciamento de clientes, produtos e pedidos. O sistema foi desenvolvido em Java, seguindo os princípios de Orientação a Objetos e SOLID, com uma base de dados em memória para simplificar o desenvolvimento.

Funcionalidades:

• Gerenciamento de Clientes:

• Cadastrar novos clientes (com documento de identificação obrigatório).

• Listar todos os clientes cadastrados.

• Atualizar informações de clientes existentes.

• Gerenciamento de Produtos:

• Cadastrar novos produtos.

• Listar todos os produtos cadastrados.

• Atualizar informações de produtos existentes.

• Gerenciamento de Pedidos:

• Criar novos pedidos associados a um cliente.

• Adicionar, remover e alterar a quantidade de itens em um pedido (apenas se o pedido estiver com status "aberto").

• Finalizar um pedido (requer ao menos um item e valor total maior que zero; altera o status para "aguardando_pagamento" e notifica o cliente por e-mail).

• Processar o pagamento de um pedido (apenas se o status for "aguardando_pagamento"; altera o status para "pago" e notifica o cliente por e-mail).

• Entregar um pedido (apenas se o status de pagamento for "pago"; altera o status para "finalizado" e notifica o cliente por e-mail).

Como Executar:

1. Clone o repositório:

2. Compile e execute o sistema:

3. Interaja com o menu:

Siga as opções apresentadas no terminal para gerenciar clientes, produtos e pedidos.

Testes:

Para executar os testes unitários, utilize o seguinte comando:

Bash
mvn test

Princípios de Design:

Este projeto busca aplicar os seguintes princípios:

• SOLID.

• Single Responsibility Principle (SRP): Cada classe e módulo tem uma única responsabilidade bem definida (ex: DataStore para persistência, ECommerceService para regras de negócio).

• Open/Closed Principle (OCP): O sistema é aberto para extensão (ex: novas formas de notificação poderiam ser adicionadas sem modificar ECommerceService) e fechado para modificação.

• Liskov Substitution Principle (LSP): (Aplicado implicitamente através do uso de herança e polimorfismo, se aplicável em futuras extensões).

• Interface Segregation Principle (ISP): (Aplicado implicitamente, evitando interfaces "gordas" e focando em responsabilidades específicas).

• Dependency Inversion Principle (DIP): As dependências são injetadas (ex: ECommerceService recebe DataStore no construtor), promovendo baixo acoplamento.

• Orientação a Objetos:

• Encapsulamento: Atributos das classes são privados ou acessados via métodos.

• Herança: (Pode ser explorada em futuras extensões, como diferentes tipos de produtos ou clientes).

• Polimorfismo: (Pode ser explorada em futuras extensões).

• Abstração: Modelos de dados representam entidades do mundo real de forma simplificada.













