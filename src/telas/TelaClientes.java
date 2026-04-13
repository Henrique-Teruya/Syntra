package telas;

import dao_tabela_atributos.dao_clientes;
import tabelaatributos.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaClientes extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private JTable jTable;

    // Componentes de inserção
    private JComboBox<String> jComboTipo;
    private JTextField jTextNome, jTextGrupo, jTextDocumento, jTextCep, jTextBairro, jTextRua;
    private JLabel jLabelDocumento;

    public TelaClientes() {
        initComponents();
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new java.awt.Color(0, 113, 227));
        btn.setForeground(java.awt.Color.WHITE);
        btn.setFont(new java.awt.Font("Helvetica Neue", java.awt.Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void styleTitle(JLabel label) {
        label.setFont(new java.awt.Font("Helvetica Neue", java.awt.Font.BOLD, 24));
        label.setForeground(new java.awt.Color(29, 29, 31));
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void addFormRow(JPanel painel, JLabel label, JComponent field, java.awt.GridBagConstraints gbc, int row) {
        gbc.gridx = 0; gbc.gridy = row;
        gbc.anchor = java.awt.GridBagConstraints.EAST;
        label.setFont(new java.awt.Font("Helvetica Neue", java.awt.Font.BOLD, 14));
        label.setForeground(new java.awt.Color(29, 29, 31));
        painel.add(label, gbc);

        gbc.gridx = 1; gbc.gridy = row;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        field.setPreferredSize(new java.awt.Dimension(250, 35));
        painel.add(field, gbc);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();

        // ====== ABA 1: INSERIR ======
        JPanel painelInserir = criarPainelInserir();
        tabbedPane.addTab("Inserir", painelInserir);

        // ====== ABA 2: VISUALIZAR ======
        JPanel painelVisualizar = criarPainelVisualizar();
        tabbedPane.addTab("Visualizar", painelVisualizar);

        // Ao mudar de aba, recarregar dados
        tabbedPane.addChangeListener(evt -> {
            if (tabbedPane.getSelectedIndex() == 1) {
                carregarDados();
            }
        });

        getContentPane().add(tabbedPane);
        setSize(720, 650);
        setLocationRelativeTo(null);
    }

    // ===================== PAINEL INSERIR =====================
    private JPanel criarPainelInserir() {
        JPanel painel = new JPanel(new java.awt.GridBagLayout());
        painel.setBackground(new java.awt.Color(245, 245, 247));
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();

        JLabel jLabelTitulo = new JLabel("Cadastro de Cliente");
        styleTitle(jLabelTitulo);

        JLabel jLabelTipo = new JLabel("Tipo:");
        JLabel jLabelNome = new JLabel("Nome:");
        JLabel jLabelGrupo = new JLabel("Grupo:");
        jLabelDocumento = new JLabel("CNPJ:");
        JLabel jLabelCep = new JLabel("CEP:");
        JLabel jLabelBairro = new JLabel("Bairro:");
        JLabel jLabelRua = new JLabel("Rua:");

        jComboTipo = new JComboBox<>(new String[]{"EMPRESA", "PESSOA"});
        jTextNome = new JTextField();
        jTextGrupo = new JTextField();
        jTextDocumento = new JTextField();
        jTextCep = new JTextField();
        jTextBairro = new JTextField();
        jTextRua = new JTextField();

        jComboTipo.addActionListener(evt -> {
            if ("PESSOA".equals(jComboTipo.getSelectedItem())) {
                jLabelDocumento.setText("CPF:");
            } else {
                jLabelDocumento.setText("CNPJ:");
            }
            jTextDocumento.setText("");
        });

        JButton jButtonSalvar = new JButton("Salvar");
        styleButton(jButtonSalvar);
        jButtonSalvar.addActionListener(evt -> salvar());

        JButton jButtonLimpar = new JButton("Limpar");
        styleButton(jButtonLimpar);
        jButtonLimpar.addActionListener(evt -> limparCampos());

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(0, 0, 30, 0); // Spacing below title
        painel.add(jLabelTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new java.awt.Insets(8, 15, 8, 15);
        
        int row = 1;
        addFormRow(painel, jLabelTipo, jComboTipo, gbc, row++);
        addFormRow(painel, jLabelNome, jTextNome, gbc, row++);
        addFormRow(painel, jLabelGrupo, jTextGrupo, gbc, row++);
        addFormRow(painel, jLabelDocumento, jTextDocumento, gbc, row++);
        addFormRow(painel, jLabelCep, jTextCep, gbc, row++);
        addFormRow(painel, jLabelBairro, jTextBairro, gbc, row++);
        addFormRow(painel, jLabelRua, jTextRua, gbc, row++);

        JPanel btnPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 0));
        btnPanel.setBackground(new java.awt.Color(245, 245, 247));
        btnPanel.add(jButtonLimpar);
        btnPanel.add(jButtonSalvar);

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(30, 0, 0, 0); // Spacing above buttons
        painel.add(btnPanel, gbc);

        return painel;
    }

    // ===================== PAINEL VISUALIZAR =====================
    private JPanel criarPainelVisualizar() {
        JPanel painel = new JPanel(new java.awt.BorderLayout(10, 10));
        painel.setBackground(new java.awt.Color(245, 245, 247));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel jLabelTitulo = new JLabel("Clientes Cadastrados");
        styleTitle(jLabelTitulo);
        painel.add(jLabelTitulo, java.awt.BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[] { "ID", "Nome", "Tipo", "CPF/CNPJ", "Grupo", "CEP", "Bairro", "Rua" }, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        jTable = new JTable(tableModel);
        jTable.setRowHeight(25);
        jTable.setFont(new java.awt.Font("Helvetica Neue", java.awt.Font.PLAIN, 14));
        jTable.getTableHeader().setFont(new java.awt.Font("Helvetica Neue", java.awt.Font.BOLD, 14));
        
        JScrollPane scrollPane = new JScrollPane(jTable);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        painel.add(scrollPane, java.awt.BorderLayout.CENTER);

        JButton btnAtualizar = new JButton("Atualizar Lista");
        styleButton(btnAtualizar);
        btnAtualizar.addActionListener(evt -> carregarDados());

        JButton btnEditar = new JButton("Editar");
        styleButton(btnEditar);
        btnEditar.addActionListener(evt -> editarSelecionado());

        JButton btnExcluir = new JButton("Excluir");
        styleButton(btnExcluir);
        btnExcluir.addActionListener(evt -> excluirSelecionado());

        JPanel btnPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 0));
        btnPanel.setBackground(new java.awt.Color(245, 245, 247));
        btnPanel.add(btnAtualizar);
        btnPanel.add(btnEditar);
        btnPanel.add(btnExcluir);

        painel.add(btnPanel, java.awt.BorderLayout.SOUTH);

        return painel;
    }

    // ===================== AÇÕES =====================
    private void salvar() {
        try {
            String tipo = jComboTipo.getSelectedItem().toString();
            String nome = jTextNome.getText().trim();
            String grupo = jTextGrupo.getText().trim();
            String documento = jTextDocumento.getText().trim();
            String cep = jTextCep.getText().trim();
            String bairro = jTextBairro.getText().trim();
            String rua = jTextRua.getText().trim();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nome é obrigatório.");
                return;
            }

            if (tipo.equals("PESSOA")) {
                String digits = documento.replaceAll("[^\\d]", "");
                if (!digits.matches("\\d{11}")) {
                    JOptionPane.showMessageDialog(this, "CPF inválido. Use 11 dígitos.");
                    return;
                }
            } else {
                String digits = documento.replaceAll("[^\\d]", "");
                if (!digits.matches("\\d{14}")) {
                    JOptionPane.showMessageDialog(this, "CNPJ inválido. Use 14 dígitos.");
                    return;
                }
            }

            Cliente c = new Cliente();
            c.setTipo(tipo);
            c.setNome(nome);
            c.setGrupo(grupo);
            if (tipo.equals("PESSOA")) {
                c.setCPF(documento);
                c.setCNPJ("");
            } else {
                c.setCNPJ(documento);
                c.setCPF("");
            }
            c.setCEP(cep);
            c.setBairro(bairro);
            c.setRua(rua);

            dao_clientes dao = new dao_clientes();
            if (dao.inserirDados(c)) {
                JOptionPane.showMessageDialog(this, "Cliente inserido com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao inserir cliente!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        jComboTipo.setSelectedIndex(0);
        jLabelDocumento.setText("CNPJ:");
        jTextNome.setText("");
        jTextGrupo.setText("");
        jTextDocumento.setText("");
        jTextCep.setText("");
        jTextBairro.setText("");
        jTextRua.setText("");
    }

    private void carregarDados() {
        try {
            tableModel.setRowCount(0);
            dao_clientes dao = new dao_clientes();
            List<Cliente> lista = dao.listarTodos();
            for (Cliente c : lista) {
                String documento = "PESSOA".equals(c.getTipo()) ? c.getCPF() : c.getCNPJ();
                tableModel.addRow(new Object[] {
                        c.getId_cliente(), c.getNome(), c.getTipo(), documento,
                        c.getGrupo(), c.getCEP(), c.getBairro(), c.getRua()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarSelecionado() {
        try {
            int row = jTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um cliente.");
                return;
            }

            int id = (int) tableModel.getValueAt(row, 0);

            dao_clientes dao = new dao_clientes();
            Cliente clienteOriginal = dao.getCliente(id);
            if (clienteOriginal == null) {
                JOptionPane.showMessageDialog(this, "Erro ao recuperar dados do cliente.");
                return;
            }

            String nome = JOptionPane.showInputDialog(this, "Nome:", clienteOriginal.getNome());
            if (nome == null)
                return;

            String tipo = (String) JOptionPane.showInputDialog(this, "Tipo:", "Editar Tipo",
                    JOptionPane.QUESTION_MESSAGE, null, new String[] { "EMPRESA", "PESSOA" }, clienteOriginal.getTipo());
            if (tipo == null)
                return;

            String docPrompt = "PESSOA".equals(tipo) ? "CPF:" : "CNPJ:";
            String docOriginal = "PESSOA".equals(tipo) ? clienteOriginal.getCPF() : clienteOriginal.getCNPJ();
            String documento = JOptionPane.showInputDialog(this, docPrompt, docOriginal);
            if (documento == null)
                return;

            String grupo = JOptionPane.showInputDialog(this, "Grupo:", clienteOriginal.getGrupo());
            if (grupo == null)
                return;
            String cep = JOptionPane.showInputDialog(this, "CEP:", clienteOriginal.getCEP());
            if (cep == null)
                return;
            String bairro = JOptionPane.showInputDialog(this, "Bairro:", clienteOriginal.getBairro());
            if (bairro == null)
                return;
            String rua = JOptionPane.showInputDialog(this, "Rua:", clienteOriginal.getRua());
            if (rua == null)
                return;

            Cliente c = new Cliente();
            c.setId_cliente(id);
            c.setNome(nome);
            c.setTipo(tipo);
            if ("PESSOA".equals(tipo)) {
                c.setCPF(documento);
                c.setCNPJ("");
            } else {
                c.setCNPJ(documento);
                c.setCPF("");
            }
            c.setGrupo(grupo);
            c.setCEP(cep);
            c.setBairro(bairro);
            c.setRua(rua);

            if (dao.atualizar(c)) {
                JOptionPane.showMessageDialog(this, "Cliente atualizado!");
                carregarDados();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao editar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirSelecionado() {
        try {
            int row = jTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um cliente.");
                return;
            }
            int id = (int) tableModel.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Excluir cliente ID " + id + "?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dao_clientes dao = new dao_clientes();
                if (dao.deletar(id)) {
                    JOptionPane.showMessageDialog(this, "Excluído!");
                    carregarDados();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir!");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
