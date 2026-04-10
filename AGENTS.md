# AGENTS.md - Diretrizes do Projeto Syntra

Este documento serve como um guia para o desenvolvimento e manutenção do projeto Syntra, detalhando padrões de arquitetura, conexão com banco de dados e lógica de back-end.

## 1. Estrutura do Projeto

```
src/
├── conexaomysql/         → Conexão com banco de dados
│   └── Conexao.java
├── tabelaatributos/      → Classes modelo (POJO com getters/setters)
│   ├── Chamados.java
│   ├── Cliente.java
│   ├── Demandas.java
│   ├── DemandasMateriais.java
│   ├── Estoque.java
│   └── Garantia.java
├── Principal/            → Ponto de entrada do sistema
│   └── Principal.java
├── dao_tabela_atributos/ → DAOs (CRUD e regras básicas)
│   ├── dao_chamados.java
│   ├── dao_clientes.java
│   ├── dao_demandas.java
│   ├── dao_demandas_materiais.java
│   ├── dao_estoque.java
│   └── dao_garantia.java
└── telas/                → Interfaces Swing
    ├── MenuInicial.java  → Menu principal
    ├── TelaChamados.java
    ├── TelaClientes.java
    ├── TelaDemandas.java
    ├── TelaEstoque.java
    └── TelaGarantia.java
```

**Ponto de entrada:** `Principal.Principal.main()` — faz verificação de conexão e abre o `MenuInicial`.

---

## 2. Build e Execução

### Compilação Manual
```bash
mkdir -p build/classes && find src -name "*.java" | xargs javac -cp "lib/*" -d build/classes
```

### Execução
```bash
java -cp "build/classes:lib/*" telas.MenuInicial
```

### Dependências
- **MySQL Connector/J** — Required no classpath (deve estar em `lib/`)
- **FlatLaF** — UI library (já em `lib/flatlaf-3.4.jar`)

### Notas
- Não há build tool (Maven/Gradle) configurado.
- Não há testes unitários (JUnit ou similar).
- Arquivos `.class`, diretório `build/` e arquivos de IDE **não** devem ser commitados.

---

## 3. Conexão com MySQL
- A conexão é gerenciada pela classe `conexaomysql.Conexao`.
- O banco de dados utilizado é o `Syntra`.
- Credenciais estão codificadas em `Conexao.java`.
- **Importante:** Sempre verifique se `conexao.getConexao()` (variável `conectar` nos DAOs) é `null` antes de preparar/executar Statements para evitar `NullPointerException`.

---

## 4. Padrões de Código

### Convenções de Nomenclatura
- **Classes:** PascalCase (ex: `dao_clientes`, `TelaChamados`)
- **Métodos:** camelCase (ex: `inserirDados()`, `listarTodos()`)
- **Variáveis:** camelCase (ex: `conectar`, `stmt`, `rs`)
- **Constantes:** UPPER_CASE com underscore (ex: `MAX_TAMANHO`)

### Imports
Organize por ordem alfabética dentro de cada grupo:
```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexaomysql.Conexao;
import tabelaatributos.Cliente;
```

Grupos (separados por linha em branco):
1. `java.lang.*` (implícito)
2. `java.sql.*`
3. `java.util.*`
4. Pacotes internos (`conexaomysql.*`, `tabelaatributos.*`, `telas.*`)

### Tipos e Variáveis
- Prefira tipos primitivos (`int`, `boolean`) a wrapper classes (`Integer`, `Boolean`) quando não houver necessidade de `null`.
- Strings vazias (`""`) representam "valor ausente" para campos com constraints NOT NULL (CPF, CNPJ, etc.).
- Use `List<T>` para retornos de coleção, não arrays.

### Formatação
- Indentação: 4 espaços (não tabs).
- Chaves: estilo K&R (na mesma linha).
- Limite de linha: ~120 caracteres.
- Linha em branco: entre m��todos e grupos de imports.

### Error Handling
- DAOs retornam `boolean` (`true` = sucesso, `false` = erro).
- Erros devem ser logados com `System.out.println("Erro: ...")` ou `System.out.println("Erro SQL: ..." + e.getMessage())`.
- Valide parâmetros no início dos métodos: objetos nulos, koneksião nula, campos obrigatórios vazios.
- Retorne lista vazia (`new ArrayList<>()`) em caso de erro, nunca `null`.

---

## 5. Lógica de Back-end (DAOs)

### Padrão dos DAOs
Cada DAO deve conter, no mínimo:
- `inserirDados(Entidade)` — Insert
- `listarTodos()` — Select all (retorna lista vazia em caso de erro)
- `get[Entidade](int id)` — Busca por ID
- `atualizar(Entidade)` — Update
- `deletar(int id)` — Delete

### Regras específicas
- **Fallback de Listagem:** `listarTodos()` deve retornar `new ArrayList<>()` em caso de erro, não `null`.
- **Tratamento de Tipos Mistos:** Para registros com tipo variável (PESSOA/EMPRESA), limpe campos irrelevantes antes de retornar.
- **Constraints de Banco:**
  - `cliente.CPF` e `cliente.CNPJ` são NOT NULL — use `""` (string vazia), não `null`.
  - `demandas_materiais` usa coluna `quantidade_usada`, não `quantidade`.
- **Garantias:** Modelo e tabela usam `id_demanda` para referenciar demandas.
- **Estoque:** Usa tabela `estoque_mov` (movimentações). DAO tem método `getEstoque(int id_mov)`.

### Transações
- `dao_demandas.inserirDadosComMateriais()` usa transação (`setAutoCommit(false)` + `commit`/`rollback`).
- Transações são usadas **apenas** quando há múltiplos inserts relacionados.

---

## 6. Interface Gráfica (Swing)

### Design System (Apple / DESIGN.md)
- Todas as futuras modificações visuais **DEVEM** seguir rigorosamente as referências descritas no arquivo `DESIGN.md`. 
- **Cores**: Aplicar a paleta primária do design system (como cinza informacional `#f5f5f7` `245,245,247` para o fundo e `#1d1d1f` `29,29,31` para texto).
- **Acento / Botões**: Sempre utilize o **Apple Blue** (`#0071e3` `0,113,227`) para botões de ação e CTAs. Para botões no Swing: configurar `setFocusPainted(false)`, `setOpaque(true)` e `setBorderPainted(false)` usando fore color white, garantindo o visual flat.
- **Tipografia e Bordas**: Remover lixos visuais nativos (exemplo: `BorderFactory.createEtchedBorder()`). Títulos devem ser proeminentes, com tamanho grande (ex: BOLD 24 ou BOLD 40) e fontes limpas ("Helvetica Neue" ou similar). Sem backgrounds distrativos.

### Padrão de Abas
- `JTabbedPane` com aba 0 = "Inserir", aba 1 = "Visualizar".
- `ChangeListener` na aba de visualização invoca `carregarDados()`.

### Interações do Usuário
- Trate cancelamento de `JOptionPane.showInputDialog`: verifique se o retorno é `null`.
- Ao editar registro da tabela, use DAO para buscar objeto completo via ID antes de mostrar diálogo.

### Tabelas com Dados Mistos
- Use colunas unificadas (ex: "CPF/CNPJ", "Referência") mostrando apenas o dado relevante por tipo.

---

## 7. Git e Versionamento

- Não commite: arquivos `.class`, diretório `build/`, arquivos de IDE (`.idea/`, `nbproject/`).
- Commits devem ter mensagem clara (1-2 linhas explicando o "porquê", não o "quê").