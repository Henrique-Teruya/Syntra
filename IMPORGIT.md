# Guia de Importação e Uso do Git - Projeto Syntra

Este documento descreve os comandos essenciais do Git para trabalhar no projeto em diferentes ambientes (faculdade, trabalho, casa), especialmente onde o Git pode não estar configurado com seu usuário.

## 1. Importando o Projeto (Clonagem)

Para baixar o projeto pela primeira vez em um novo computador:

```bash
git clone <URL_DO_REPOSITORIO>
cd <NOME_DO_DIRETORIO>
```

## 2. Configurando seu Usuário Localmente

Em computadores públicos ou de terceiros (como na faculdade), é importante configurar seu nome e e-mail **apenas para este repositório**, para que seus commits sejam identificados corretamente sem alterar a configuração global do sistema.

**Dentro da pasta do projeto**, execute:

```bash
git config user.name "Seu Nome Completo"
git config user.email "seu.email@exemplo.com"
```

Para verificar se a configuração foi aplicada corretamente:
```bash
git config user.name
git config user.email
```

## 3. Fluxo de Trabalho Diário

Siga estes passos sempre que for trabalhar no código:

### A. Atualizar o projeto local
Antes de começar, baixe as alterações mais recentes do GitHub:
```bash
git pull origin main
```

### B. Verificar alterações
Veja quais arquivos você modificou:
```bash
git status
```

### C. Adicionar e Commitar
Adicione os arquivos modificados e crie uma versão (commit) com uma mensagem descritiva:
```bash
git add .
git commit -m "Descrição clara do que foi feito"
```

### D. Enviar para o GitHub
Envie suas alterações para o servidor:
```bash
git push origin main
```

## 4. Avisos Importantes

- **Não commite arquivos de build:** Evite enviar pastas como `build/`, `dist/` ou arquivos `.class`. O projeto já deve ter um `.gitignore` para isso, mas fique atento.
- **Conflitos:** Se o `git pull` falhar devido a conflitos, você precisará resolver as diferenças nos arquivos antes de continuar.
- **Credenciais:** Em computadores diferentes, o Git pode pedir sua senha ou token do GitHub ao fazer `push` ou `pull`. Tenha seu Personal Access Token (PAT) à mão se necessário.

## 5. Resumo de Comandos Úteis

| Comando | Descrição |
| :--- | :--- |
| `git clone` | Copia o repositório remoto para sua máquina. |
| `git pull` | Traz as novidades do servidor para seu PC. |
| `git add .` | Prepara todas as suas mudanças para o commit. |
| `git commit -m "..."` | Salva suas mudanças localmente com uma mensagem. |
| `git push` | Envia seus commits locais para o servidor. |
| `git status` | Mostra o estado atual das suas modificações. |
| `git log` | Mostra o histórico de commits. |
| `git checkout -b nome-da-branch` | Cria e muda para uma nova ramificação de trabalho. |
