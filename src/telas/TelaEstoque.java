package telas;

import dao_tabela_atributos.dao_estoque;
import tabelaatributos.Estoque;
import utils.DateUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaEstoque extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private JTable jTable;
    private JTextField jTextIdMaterial, jTextDescricao, jTextQuantidade, jTextDataMov;
    private JComboBox<String> jComboTipo;

    public TelaEstoque() {
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
        setTitle("Estoque");
        setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Inserir", criarPainelInserir());
        tabbedPane.addTab("Visualizar", criarPainelVisualizar());
        tabbedPane.addChangeListener(evt -> {
            if (tabbedPane.getSelectedIndex() == 1)
                carregarDados();
        });

        getContentPane().add(tabbedPane);
        setSize(660, 480);
        setLocationRelativeTo(null);
    }

    private JPanel criarPainelInserir() {
        JPanel painel = new JPanel(new java.awt.GridBagLayout());
        painel.setBackground(new java.awt.Color(245, 245, 247));
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();

        JLabel titulo = new JLabel("Movimento de Estoque");
        styleTitle(titulo);

        JLabel lTipo = new JLabel("Tipo:");
        JLabel lIdMat = new JLabel("ID Material:");
        JLabel lDesc = new JLabel("Descrição:");
        JLabel lQtd = new JLabel("Quantidade:");
        JLabel lData = new JLabel("Data (DD/MM/AAAA):");

        jComboTipo = new JComboBox<>(new String[] { "ENTRADA", "SAIDA" });
        jTextIdMaterial = new JTextField();
        jTextDescricao = new JTextField();
        jTextQuantidade = new JTextField();
        jTextDataMov = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        styleButton(btnSalvar);
        btnSalvar.addActionListener(evt -> salvar());

        JButton btnLimpar = new JButton("Limpar");
        styleButton(btnLimpar);
        btnLimpar.addActionListener(evt -> {
            jTextIdMaterial.setText("");
            jTextDescricao.setText("");
            jTextQuantidade.setText("");
            jTextDataMov.setText("");
            jComboTipo.setSelectedIndex(0);
        });

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(0, 0, 30, 0); // Spacing below title
        painel.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new java.awt.Insets(8, 15, 8, 15);
        
        int row = 1;
        addFormRow(painel, lTipo, jComboTipo, gbc, row++);
        addFormRow(painel, lIdMat, jTextIdMaterial, gbc, row++);
        addFormRow(painel, lDesc, jTextDescricao, gbc, row++);
        addFormRow(painel, lQtd, jTextQuantidade, gbc, row++);
        addFormRow(painel, lData, jTextDataMov, gbc, row++);

        JPanel btnPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 0));
        btnPanel.setBackground(new java.awt.Color(245, 245, 247));
        btnPanel.add(btnLimpar);
        btnPanel.add(btnSalvar);

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(30, 0, 0, 0); // Spacing above buttons
        painel.add(btnPanel, gbc);

        return painel;
    }    private JPanel criarPainelVisualizar() {
        JPanel painel = new JPanel(new java.awt.BorderLayout(10, 10));
        painel.setBackground(new java.awt.Color(245, 245, 247));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Movimentos de Estoque");
        styleTitle(titulo);
        painel.add(titulo, java.awt.BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[] { "ID Mov", "ID Material", "Descrição", "Quantidade", "Tipo Mov", "Data Mov" }, 0) {
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
            String tipo = jComboTipo.getSelectedItem().toString();
            int idMat = Integer.parseInt(jTextIdMaterial.getText().trim());
            String desc = jTextDescricao.getText().trim();
            int qtd = Integer.parseInt(jTextQuantidade.getText().trim());
            String data = jTextDataMov.getText().trim();

            if (qtd <= 0) {
                JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero.");
                return;
            }

            Estoque mov = new Estoque();
            mov.setTipo_mov(tipo);
            mov.setId_material(idMat);
            mov.setDescricao(desc);
            mov.setQuantidade(qtd);
            mov.setData_mov(DateUtils.uiToDb(data));

            dao_estoque dao = new dao_estoque();
            if (dao.inserirDados(mov)) {
                JOptionPane.showMessageDialog(this, "Movimento registrado!");
                jTextIdMaterial.setText("");
                jTextDescricao.setText("");
                jTextQuantidade.setText("");
                jTextDataMov.setText("");
                jComboTipo.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao registrar movimento.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID Material ou Quantidade inválida.");
        }
    }

    private void carregarDados() {
        tableModel.setRowCount(0);
        dao_estoque dao = new dao_estoque();
        List<Estoque> lista = dao.listarTodos();
        for (Estoque e : lista) {
            tableModel.addRow(new Object[] { e.getId_mov(), e.getId_material(), e.getDescricao(), e.getQuantidade(),
                    e.getTipo_mov(), DateUtils.dbToUi(e.getData_mov()) });
        }
    }

    private void editarSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro.");
            return;
        }
        int idMov = (int) tableModel.getValueAt(row, 0);

        dao_estoque dao = new dao_estoque();
        Estoque original = dao.getEstoque(idMov);

        if (original == null) {
            JOptionPane.showMessageDialog(this, "Erro ao recuperar dados.");
            return;
        }

        String tipoMov = (String) JOptionPane.showInputDialog(this, "Tipo:", "Editar",
                JOptionPane.QUESTION_MESSAGE, null, new String[] { "ENTRADA", "SAIDA" }, original.getTipo_mov());
        if (tipoMov == null)
            return;

        String descricao = JOptionPane.showInputDialog(this, "Descrição:", original.getDescricao());
        if (descricao == null)
            return;

        String quantidadeStr = JOptionPane.showInputDialog(this, "Quantidade:", original.getQuantidade());
        if (quantidadeStr == null)
            return;
        int quantidade = Integer.parseInt(quantidadeStr);

        String dataMov = JOptionPane.showInputDialog(this, "Data (DD/MM/AAAA):", DateUtils.dbToUi(original.getData_mov()));
        if (dataMov == null)
            return;

        Estoque e = new Estoque();
        e.setId_mov(idMov);
        e.setId_material(original.getId_material()); // Não é dado opção de editar o id_material
        e.setTipo_mov(tipoMov);
        e.setDescricao(descricao);
        e.setQuantidade(quantidade);
        e.setData_mov(DateUtils.uiToDb(dataMov));

        if (dao.atualizar(e)) {
            JOptionPane.showMessageDialog(this, "Atualizado!");
            carregarDados();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar!");
        }
    }

    private void excluirSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro.");
            return;
        }
        int idMov = (int) tableModel.getValueAt(row, 0);
        if (JOptionPane.showConfirmDialog(this, "Excluir movimento ID " + idMov + "?", "Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dao_estoque dao = new dao_estoque();
            if (dao.deletar(idMov)) {
                JOptionPane.showMessageDialog(this, "Excluído!");
                carregarDados();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir!");
            }
        }
    }
}
