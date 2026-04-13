package telas;

import dao_tabela_atributos.dao_chamados;
import tabelaatributos.Chamados;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaChamados extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private JTable jTable;
    private JTextField jTextIdCliente, jTextDescricao, jTextData;

    public TelaChamados() {
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
        setTitle("Chamados");
        setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Inserir", criarPainelInserir());
        tabbedPane.addTab("Visualizar", criarPainelVisualizar());
        tabbedPane.addChangeListener(evt -> {
            if (tabbedPane.getSelectedIndex() == 1)
                carregarDados();
        });

        getContentPane().add(tabbedPane);
        setSize(620, 420);
        setLocationRelativeTo(null);
    }

    private JPanel criarPainelInserir() {
        JPanel painel = new JPanel(new java.awt.GridBagLayout());
        painel.setBackground(new java.awt.Color(245, 245, 247));
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();

        JLabel jLabelTitulo = new JLabel("Cadastro de Chamado");
        styleTitle(jLabelTitulo);

        JLabel lIdCli = new JLabel("ID Cliente:");
        JLabel lDesc = new JLabel("Descrição:");
        JLabel lData = new JLabel("Data Abertura (AAAA-MM-DD):");

        jTextIdCliente = new JTextField();
        jTextDescricao = new JTextField();
        jTextData = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        styleButton(btnSalvar);
        btnSalvar.addActionListener(evt -> salvar());
        JButton btnLimpar = new JButton("Limpar");
        styleButton(btnLimpar);
        btnLimpar.addActionListener(evt -> {
            jTextIdCliente.setText("");
            jTextDescricao.setText("");
            jTextData.setText("");
        });

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(0, 0, 30, 0); // Bottom margin for title
        painel.add(jLabelTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new java.awt.Insets(8, 15, 8, 15);
        
        int row = 1;
        addFormRow(painel, lIdCli, jTextIdCliente, gbc, row++);
        addFormRow(painel, lDesc, jTextDescricao, gbc, row++);
        addFormRow(painel, lData, jTextData, gbc, row++);

        JPanel btnPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 0));
        btnPanel.setBackground(new java.awt.Color(245, 245, 247));
        btnPanel.add(btnLimpar);
        btnPanel.add(btnSalvar);

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(30, 0, 0, 0); // Top margin for buttons
        painel.add(btnPanel, gbc);

        return painel;
    }    private JPanel criarPainelVisualizar() {
        JPanel painel = new JPanel(new java.awt.BorderLayout(10, 10));
        painel.setBackground(new java.awt.Color(245, 245, 247));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel jLabelTitulo = new JLabel("Chamados Cadastrados");
        styleTitle(jLabelTitulo);
        painel.add(jLabelTitulo, java.awt.BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[] { "ID", "ID Cliente", "Descrição", "Data Abertura", "Status" },
                0) {
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

    private void salvar() {
        try {
            String idCliTxt = jTextIdCliente.getText().trim();
            if (idCliTxt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID Cliente é obrigatório.");
                return;
            }
            int idCli = Integer.parseInt(idCliTxt);
            String desc = jTextDescricao.getText().trim();
            if (desc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Descrição é obrigatória.");
                return;
            }
            String data = jTextData.getText().trim();

            Chamados ch = new Chamados();
            ch.setId_cliente(idCli);
            ch.setDescricao(desc);
            ch.setData_abertura(data);
            ch.setStatus("ABERTO");

            dao_chamados dao = new dao_chamados();
            if (dao.inserirDados(ch)) {
                JOptionPane.showMessageDialog(this, "Chamado inserido com sucesso!");
                jTextIdCliente.setText("");
                jTextDescricao.setText("");
                jTextData.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao inserir chamado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar chamado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarDados() {
        try {
            tableModel.setRowCount(0);
            dao_chamados dao = new dao_chamados();
            List<Chamados> lista = dao.listarTodos();
            for (Chamados ch : lista) {
                tableModel.addRow(new Object[] { ch.getId_chamado(), ch.getId_cliente(), ch.getDescricao(),
                        ch.getData_abertura(), ch.getStatus() });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarSelecionado() {
        try {
            int row = jTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um chamado.");
                return;
            }
            int id = (int) tableModel.getValueAt(row, 0);

            String idCliente = JOptionPane.showInputDialog(this, "ID Cliente:", tableModel.getValueAt(row, 1));
            if (idCliente == null)
                return;
            String descricao = JOptionPane.showInputDialog(this, "Descrição:", tableModel.getValueAt(row, 2));
            if (descricao == null)
                return;
            String data = JOptionPane.showInputDialog(this, "Data Abertura:", tableModel.getValueAt(row, 3));
            String status = (String) JOptionPane.showInputDialog(this, "Status:", "Editar Status",
                    JOptionPane.QUESTION_MESSAGE, null, new String[] { "ABERTO", "EM ANDAMENTO", "FECHADO" },
                    tableModel.getValueAt(row, 4));
            if (status == null)
                return;

            Chamados ch = new Chamados();
            ch.setId_chamado(id);
            ch.setId_cliente(Integer.parseInt(idCliente));
            ch.setDescricao(descricao);
            ch.setData_abertura(data != null ? data : "");
            ch.setStatus(status);

            dao_chamados dao = new dao_chamados();
            if (dao.atualizar(ch)) {
                JOptionPane.showMessageDialog(this, "Atualizado!");
                carregarDados();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao editar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirSelecionado() {
        try {
            int row = jTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um chamado.");
                return;
            }
            int id = (int) tableModel.getValueAt(row, 0);
            if (JOptionPane.showConfirmDialog(this, "Excluir chamado ID " + id + "?", "Confirmar",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dao_chamados dao = new dao_chamados();
                if (dao.deletar(id)) {
                    JOptionPane.showMessageDialog(this, "Excluído!");
                    carregarDados();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir!");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
