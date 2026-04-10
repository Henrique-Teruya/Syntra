# CONTEXT.md — Projeto Syntra

## Visão Geral

O **Syntra** é um sistema acadêmico desenvolvido em **Java + Swing + MySQL**, criado com foco didático para demonstrar conceitos de:

- CRUD completo
- Programação orientada a objetos
- Integração com banco de dados relacional
- Arquitetura simples em camadas
- Integridade referencial com Foreign Keys
- Regras básicas de negócio aplicadas no código

O projeto **não tem foco enterprise/corporativo**, portanto sua arquitetura deve permanecer **simples, legível e acadêmica**, evitando complexidade desnecessária.

---

## Filosofia / Viés do Projeto

O Syntra foi pensado como um projeto:

- Acadêmico
- Didático
- Modular porém simples
- Fácil de entender por estudantes iniciantes/intermediários
- Sem padrões enterprise desnecessários

### Prioridades do projeto:

1. Clareza do código
2. Organização simples
3. Fácil manutenção
4. Fácil explicação acadêmica
5. Funcionalidade completa

### Deve-se evitar:

- Overengineering
- Clean Architecture desnecessária
- Muitas abstrações
- Interfaces inúteis
- Patterns enterprise sem necessidade
- Separações excessivas de responsabilidade

---

## Estrutura de Pastas Esperada

```plaintext
src/
├── conexaomysql/
│   └── Conexao.java
│
├── tabelaatributos/
│   ├── Cliente.java
│   ├── Demandas.java
│   ├── Estoque.java
│   ├── Garantia.java
│   └── Chamados.java
│
├── dao_tabela_atributos/
│   ├── dao_clientes.java
│   ├── dao_demandas.java
│   ├── dao_estoque.java
│   ├── dao_garantia.java
│   └── dao_chamados.java
│
├── telas/
│   ├── TelaInserirCliente.java
│   ├── TelaInserirDemandas.java
│   ├── TelaInserirEstoque.java
│   ├── TelaInserirGarantia.java
│   └── TelaInserirChamados.java
│
└── MenuPrincipal.java
```

---

## Arquitetura do Sistema

O projeto segue arquitetura simples em 3 camadas:

### 1. tabelaatributos (Model)

Responsável por representar as tabelas do banco em objetos Java.

Cada classe contém:

- atributos privados
- getters
- setters

Exemplo:

```java
private int id_cliente;

public int getId_cliente() {
    return id_cliente;
}

public void setId_cliente(int id_cliente) {
    this.id_cliente = id_cliente;
}
```

---

### 2. DAO (Data Access Object)

Responsável por:

- Inserir dados
- Atualizar dados
- Buscar dados
- Remover dados
- Aplicar regras de negócio simples

Todo acesso ao banco ocorre pelos DAOs.

---

### 3. Telas Swing

Responsável por:

- Interface visual
- Captura de inputs
- Validações básicas
- Chamadas aos DAOs

---

## Banco de Dados MySQL

O sistema utiliza **MySQL/MariaDB via JDBC**.

A conexão é feita por:

```java
Conexao.java
```

Usando:

- JDBC Driver / Connector
- PreparedStatement
- ResultSet

---

## Estrutura do Banco

### cliente

Armazena clientes cadastrados.

Campos:

- id_cliente
- nome
- grupo
- CNPJ
- CEP
- Bairro
- Rua

---

### demandas

Representa solicitações/pedidos.

Campos:

- id
- id_cliente (FK)
- descricao
- data_solicitacao
- entregueSouN

---

### estoque_mov

Sistema de movimentação de estoque.

**IMPORTANTE:**

O estoque NÃO funciona por quantidade fixa.

Ele funciona por **histórico de movimentações**.

Campos:

- id_mov
- id_material
- descricao
- quantidade
- tipo_mov
- data_mov

Tipos possíveis:

- ENTRADA
- SAIDA

---

### demandas_materiais

Tabela intermediária entre demanda e materiais.

Campos:

- id
- id_demanda
- id_material
- quantidade_usada

Serve para registrar quais materiais foram usados em cada demanda.

---

### chamados

Sistema de suporte/atendimento.

Campos:

- id_chamado
- id_cliente
- descricao
- data_chamado
- status_chamado

---

### garantia

Controle de garantia.

Campos:

- id_garantia
- id_cliente
- id_material
- id_demanda
- meses_garantia
- data_compra

---

## Views Importantes

### estoque_real

Calcula quantidade real do estoque dinamicamente.

```sql
ENTRADA = soma
SAIDA = subtração
```

A view existe porque o estoque é baseado em movimentações.

Não existe campo fixo de quantidade.

---

### garantia_status

Calcula:

- validade da garantia
- status (VÁLIDA / EXPIRADA)

Com base em:

```sql
data_compra + meses_garantia
```

---

## Regras de Negócio Importantes

### Estoque

- ENTRADA cria novo material / adiciona quantidade
- SAÍDA reduz quantidade lógica via movimentação
- Quantidade real SEMPRE consultada pela VIEW

---

### Demandas

Pode:

1. Criar nova demanda
2. Atualizar status de entrega de demanda existente

---

### Garantia

Só faz sentido após demanda/compra existir.

Pode vincular:

- cliente
- material
- demanda

---

## Relações / Foreign Keys

### Principais FKs:

- demandas.id_cliente → cliente.id_cliente
- chamados.id_cliente → cliente.id_cliente
- garantia.id_cliente → cliente.id_cliente
- garantia.id_demanda → demandas.id
- demandas_materiais.id_demanda → demandas.id

---

## Cuidados ao Alterar o Projeto

Antes de alterar qualquer lógica:

### Sempre lembrar:

1. O projeto é acadêmico.
2. Simplicidade > arquitetura perfeita.
3. Toda mudança deve manter compatibilidade com MySQL.
4. Não quebrar DAOs existentes.
5. Não complicar sem necessidade.

---

## Stack Utilizada

- Java
- Swing
- JDBC
- MySQL / MariaDB
- NetBeans / Ant Build

---

## Objetivo Final

O Syntra é um sistema de gestão acadêmico simples que demonstra:

- cadastro de clientes
- gestão de demandas
- controle de estoque por movimentação
- chamados
- garantias
- integração completa Java + MySQL

Toda evolução futura deve respeitar:

> simplicidade, clareza e didática acima de sofisticação arquitetural.
