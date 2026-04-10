package telas;

import dao_tabela_atributos.dao_garantia;
import tabelaatributos.Garantia;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaGarantia extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private JTable jTable;
    private JTextField jTextCliente, jTextMaterial, jTextMeses, jTextData;

    public TelaGarantia() {
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
        setTitle("Garantias");
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

        JLabel titulo = new JLabel("Cadastrar Garantia");
        styleTitle(titulo);

        JLabel lCli = new JLabel("ID Cliente:");
        JLabel lMat = new JLabel("ID Material:");
        JLabel lMeses = new JLabel("Meses de Garantia:");
        JLabel lData = new JLabel("Data Compra (AAAA-MM-DD):");

        jTextCliente = new JTextField();
        jTextMaterial = new JTextField();
        jTextMeses = new JTextField();
        jTextData = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        styleButton(btnSalvar);
        btnSalvar.addActionListener(evt -> salvar());
        
        JButton btnLimpar = new JButton("Limpar");
        styleButton(btnLimpar);
        btnLimpar.addActionListener(evt -> {
            jTextCliente.setText("");
            jTextMaterial.setText("");
            jTextMeses.setText("");
            jTextData.setText("");
        });

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(0, 0, 30, 0); // Bottom margin
        painel.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new java.awt.Insets(8, 15, 8, 15);
        
        int row = 1;
        addFormRow(painel, lCli, jTextCliente, gbc, row++);
        addFormRow(painel, lMat, jTextMaterial, gbc, row++);
        addFormRow(painel, lMeses, jTextMeses, gbc, row++);
        addFormRow(painel, lData, jTextData, gbc, row++);

        JPanel btnPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 0));
        btnPanel.setBackground(new java.awt.Color(245, 245, 247));
        btnPanel.add(btnLimpar);
        btnPanel.add(btnSalvar);

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(30, 0, 0, 0); // Top margin
        painel.add(btnPanel, gbc);

        return painel;
    }

    private JPanel criarPainelVisualizar() {
        JPanel painel = new JPanel(new java.awt.BorderLayout(10, 10));
        painel.setBackground(new java.awt.Color(245, 245, 247));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Garantias Cadastradas");
        styleTitle(titulo);
        painel.add(titulo, java.awt.BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[] { "ID Garantia", "ID Cliente", "ID Material", "Meses", "Data Compra" }, 0) {
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
            Garantia g = new Garantia();
            g.setId_cliente(Integer.parseInt(jTextCliente.getText()));
            g.setId_material(Integer.parseInt(jTextMaterial.getText()));
            g.setMeses_garantia(Integer.parseInt(jTextMeses.getText()));
            g.setData_compra(jTextData.getText());

            dao_garantia dao = new dao_garantia();
            if (dao.inserirDados(g)) {
                JOptionPane.showMessageDialog(this, "Garantia inserida com sucesso!");
                jTextCliente.setText("");
                jTextMaterial.setText("");
                jTextMeses.setText("");
                jTextData.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao inserir garantia!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: Verifique os campos.");
        }
    }

    private void carregarDados() {
        tableModel.setRowCount(0);
        dao_garantia dao = new dao_garantia();
        List<Garantia> lista = dao.listarTodos();
        for (Garantia g : lista) {
            tableModel.addRow(new Object[] { g.getId_garantia(), g.getId_cliente(), g.getId_material(),
                    g.getMeses_garantia(), g.getData_compra() });
        }
    }

    private void editarSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma garantia.");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);

        String idCliente = JOptionPane.showInputDialog(this, "ID Cliente:", tableModel.getValueAt(row, 1));
        if (idCliente == null)
            return;
        String idMaterial = JOptionPane.showInputDialog(this, "ID Material:", tableModel.getValueAt(row, 2));
        if (idMaterial == null)
            return;
        String meses = JOptionPane.showInputDialog(this, "Meses Garantia:", tableModel.getValueAt(row, 3));
        if (meses == null)
            return;
        String data = JOptionPane.showInputDialog(this, "Data Compra:", tableModel.getValueAt(row, 4));

        Garantia g = new Garantia();
        g.setId_garantia(id);
        g.setId_cliente(Integer.parseInt(idCliente));
        g.setId_material(Integer.parseInt(idMaterial));
        g.setMeses_garantia(Integer.parseInt(meses));
        g.setData_compra(data != null ? data : "");

        dao_garantia dao = new dao_garantia();
        if (dao.atualizar(g)) {
            JOptionPane.showMessageDialog(this, "Atualizado!");
            carregarDados();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar!");
        }
    }

    private void excluirSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma garantia.");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        if (JOptionPane.showConfirmDialog(this, "Excluir garantia ID " + id + "?", "Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dao_garantia dao = new dao_garantia();
            if (dao.deletar(id)) {
                JOptionPane.showMessageDialog(this, "Excluído!");
                carregarDados();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir!");
            }
        }
    }
}
