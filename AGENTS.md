# AGENTS.md - Diretrizes do Projeto Syntra

Este documento serve como um guia para o desenvolvimento e manutenção do projeto Syntra, detalhando padrões de arquitetura, conexão com banco de dados e lógica de back-end.

## 1. Estrutura do Projeto
- **Modelos de Dados:** Localizados em `src/tabelaatributos/`.
- **DAOs (Data Access Objects):** Localizados em `src/dao_tabela_atributos/`.
- **Conexão MySQL:** Localizada em `src/conexaomysql/Conexao.java`.
- **Telas (Swing):** Localizadas em `src/telas/`.

## 2. Conexão com MySQL
- A conexão é gerenciada pela classe `conexaomysql.Conexao`.
- O banco de dados utilizado é o `Syntra`.
- As credenciais estão atualmente codificadas na classe `Conexao.java`.
- **Importante:** Sempre verifique se o retorno de `conexao.getConexao()` (geralmente armazenado na variável `conectar` nos DAOs) é nulo antes de tentar preparar ou executar Statements para evitar `NullPointerException`.

## 3. Lógica de Back-end (DAOs)
- **Fallback de Listagem:** O método `listarTodos()` deve retornar uma lista vazia (`new ArrayList<>()`) em caso de erro de conexão ou falha na consulta, em vez de retornar `null`.
- **Tratamento de Tipos Mistos:** Em tabelas que armazenam diferentes tipos de entidades (ex: `cliente` como PESSOA ou EMPRESA), o método `listarTodos()` deve garantir que campos irrelevantes para o tipo específico do registro sejam limpos ou anulados antes de retornar o objeto para a interface.
- **Constraints de Banco:**
  - A tabela `cliente` possui restrições `NOT NULL` nas colunas `CPF` e `CNPJ`. Utilize strings vazias (`""`) em vez de `null` quando um desses campos não se aplicar ao tipo de cliente.
  - A tabela `demandas_materiais` utiliza a coluna `quantidade_usada`, e não `quantidade`.
- **Garantias:** A tabela e o modelo `Garantia` utilizam o campo `id_demanda` para referenciar demandas específicas.

## 4. Interface Gráfica (Swing)
- **Padrão de Abas:** As telas utilizam consistentemente um `JTabbedPane` onde a aba de índice 1 é a de "Visualização".
- **Carregamento de Dados:** A mudança para a aba de visualização deve disparar um `ChangeListener` que invoca o método `carregarDados()`.
- **Inputs do Usuário:** Sempre verifique se o retorno de `JOptionPane.showInputDialog` é nulo para tratar corretamente o cancelamento pelo usuário sem causar erros colaterais.
- **Tabelas com Dados Mistos:** Use colunas unificadas (ex: "CPF/CNPJ", "Referência") para exibir apenas o dado relevante ao tipo de registro na linha.
- **Edição de Registros:** Ao editar um registro a partir de uma tabela, recupere o objeto completo do banco de dados via DAO usando o ID do registro para garantir que todos os campos subjacentes estejam devidamente preenchidos nos diálogos de edição.

## 5. Compilação e Build
- **Comando de Compilação Manual:**
  ```bash
  mkdir -p build/classes && find src -name "*.java" | xargs javac -d build/classes
  ```
- **Classpath:** A compilação exige o JAR do MySQL Connector/J no classpath.
- **Git:** Arquivos binários `.class` e o diretório `build/` **não** devem ser commitados.
