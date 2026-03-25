# 🧠 Projeto Syntra

## 📌 Visão Geral

O Syntra é um sistema acadêmico desenvolvido em Java com o objetivo de gerenciar o fluxo de uma oficina técnica, incluindo:

- Cadastro de clientes
- Controle de demandas (ordens de serviço)
- Gestão de estoque
- Registro de chamados
- Controle de garantias

O sistema foi desenvolvido com foco em organização interna, rastreabilidade de informações e integridade dos dados.

---

## 🎯 Objetivo do Projeto

Este projeto tem caráter acadêmico e busca demonstrar:

- Aplicação de orientação a objetos em Java
- Separação de responsabilidades (MVC simplificado)
- Integração com banco de dados relacional (MySQL)
- Uso de interfaces gráficas com Swing
- Implementação de regras de negócio reais

---

## 🧱 Arquitetura do Sistema

O sistema é dividido em três camadas principais:

### 1. 📦 Model (tabelaatributos)

Representa as entidades do sistema (espelhamento do banco de dados)

Exemplos:
- Cliente
- Demandas
- Estoque
- Garantia
- Chamados

Cada classe contém:
- Atributos privados
- Getters e setters

---

### 2. 🗄 DAO (dao_tabela_atributos)

Responsável pela comunicação com o banco de dados

Funções:
- Inserção de dados
- Atualização
- Consulta
- Regras de negócio

Exemplo:
- dao_clientes → simples CRUD
- dao_demandas → inserção + vínculo com materiais
- dao_estoque → controle de entrada e saída
- dao_garantia → vínculo com demanda
- dao_chamados → registro de atendimento

---

### 3. 🖥 Interface (telas)

Interface gráfica feita em Java Swing

Funções:
- Entrada de dados
- Validação
- Exibição de mensagens
- Navegação entre funcionalidades

---

## 🗃 Estrutura do Banco de Dados

O banco de dados é relacional e utiliza MySQL.

### Tabelas principais:

#### Cliente
- id_cliente (PK)
- nome
- grupo
- CNPJ
- CEP
- Bairro
- Rua

#### Demandas
- id (PK)
- id_cliente (FK)
- descricao
- data_solicitacao
- entregueSouN (S/N)

#### DemandasMateriais
- id_demanda (FK)
- id_material (FK)
- quantidade

#### Estoque
- id_material (PK)
- descricao (apenas entrada)
- quantidade
- tipo_mov (ENTRADA / SAIDA)
- data_mov
- id_demanda (FK opcional)

#### Garantia
- id_garantia (PK)
- id_cliente (FK)
- id_material (FK)
- id_demanda (FK opcional)
- meses_garantia
- data_compra

#### Chamados
- id_chamado (PK)
- id_cliente (FK)
- descricao
- data_chamado

---

## 🔗 Relações Importantes

- Cliente → Demandas (1:N)
- Demandas → Materiais (N:N via tabela intermediária)
- Demandas → Estoque (afeta saída de material)
- Demandas → Garantia (apenas quando entregue = 'S')
- Estoque → movimentações (entrada/saída)

---

## ⚙️ Regras de Negócio

### 📦 Estoque
- Entrada: adiciona material com descrição
- Saída: referencia id_material e reduz quantidade
- Controle real feito via movimentações

### 📋 Demandas
- Pode cadastrar com múltiplos materiais
- Status "S" indica entrega concluída
- Pode gerar impacto no estoque

### 🛡 Garantia
- Só deve existir para demandas entregues
- Relaciona cliente + material + demanda

### 📞 Chamados
- Sempre iniciam como "abertos"
- Não possuem status na inserção

---

## 🔌 Integração com MySQL

- Utiliza JDBC para conexão
- Classe `Conexao` gerencia o acesso ao banco
- Queries são executadas via `PreparedStatement`

⚠️ IMPORTANTE:
A IA deve entender que:
- O banco não é gerado automaticamente pelo código
- Ele precisa existir previamente
- As tabelas devem estar corretamente configuradas com FKs

---

## 🛠 Ambiente de Desenvolvimento

- Linguagem: Java
- Interface: Swing
- Banco: MySQL / MariaDB
- IDE original: NetBeans
- Build: Ant

⚠️ Pode ser utilizado em outros ambientes como:
- Antigravity
- IntelliJ
- Eclipse

Desde que:
- O conector JDBC esteja configurado
- O classpath esteja correto

---

## ⚠️ Pontos de Atenção (Importante para IA)

Mesmo lendo o código, é necessário entender:

- A lógica do sistema depende fortemente do banco
- Muitas regras não estão explícitas no código (estão no fluxo)
- O estoque é baseado em movimentação, não valor fixo
- Demandas podem afetar múltiplas tabelas
- Garantia depende do status da demanda

---

## 🧠 Contexto Acadêmico

Este projeto NÃO é um sistema comercial completo.

Ele foi desenvolvido para:

- Demonstrar conhecimento técnico
- Seguir padrões de organização de código
- Simular um sistema real simplificado

---

## 🚀 Possíveis Evoluções (NÃO IMPLEMENTAR AGORA)

- Sistema de vendas
- Interface web
- Relatórios avançados
- Automação completa de estoque
- Integração com APIs

---

## 📌 Conclusão

O Syntra é um sistema modular que integra banco de dados, lógica de negócio e interface gráfica para resolver problemas reais de controle interno de uma oficina.

Seu foco principal é organização, rastreabilidade e consistência dos dados.
