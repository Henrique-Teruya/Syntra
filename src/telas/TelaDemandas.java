package telas;

import dao_tabela_atributos.dao_demandas;
import tabelaatributos.Demandas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TelaDemandas extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private JTable jTable;
    private JTextField jTextIdCliente, jTextDescricao, jTextData, jTextEntregue, jTextMatId, jTextMatQtd;
    private JLabel jLabelItensCount;
    private final List<int[]> itens = new ArrayList<>();

    public TelaDemandas() {
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
        setTitle("Demandas");
        setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Inserir", criarPainelInserir());
        tabbedPane.addTab("Visualizar", criarPainelVisualizar());
        tabbedPane.addChangeListener(evt -> {
            if (tabbedPane.getSelectedIndex() == 1)
                carregarDados();
        });

        getContentPane().add(tabbedPane);
        setSize(620, 650);
        setLocationRelativeTo(null);
    }

    private JPanel criarPainelInserir() {
        JPanel painel = new JPanel(new java.awt.GridBagLayout());
        painel.setBackground(new java.awt.Color(245, 245, 247));
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();

        JLabel titulo = new JLabel("Inserir Demanda");
        styleTitle(titulo);

        JLabel lIdCli = new JLabel("ID Cliente:");
        JLabel lDesc = new JLabel("Descrição:");
        JLabel lData = new JLabel("Data (AAAA-MM-DD):");
        JLabel lEntr = new JLabel("Entregue (S/N):");
        JLabel lMatId = new JLabel("ID Material:");
        JLabel lMatQtd = new JLabel("Qtd:");

        jTextIdCliente = new JTextField();
        jTextDescricao = new JTextField();
        jTextData = new JTextField();
        jTextEntregue = new JTextField();
        jTextMatId = new JTextField();
        jTextMatQtd = new JTextField();
        jLabelItensCount = new JLabel("Itens: 0");
        jLabelItensCount.setFont(new java.awt.Font("Helvetica Neue", java.awt.Font.BOLD, 14));

        JButton btnAddMat = new JButton("Adicionar Item");
        styleButton(btnAddMat);
        btnAddMat.addActionListener(evt -> adicionarItem());
        JButton btnSalvar = new JButton("Salvar");
        styleButton(btnSalvar);
        btnSalvar.addActionListener(evt -> salvar());
        JButton btnLimpar = new JButton("Limpar");
        styleButton(btnLimpar);
        btnLimpar.addActionListener(evt -> limpar());

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(0, 0, 30, 0); // Bottom margin for title
        painel.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new java.awt.Insets(8, 15, 8, 15);
        
        int row = 1;
        addFormRow(painel, lIdCli, jTextIdCliente, gbc, row++);
        addFormRow(painel, lDesc, jTextDescricao, gbc, row++);
        addFormRow(painel, lData, jTextData, gbc, row++);
        addFormRow(painel, lEntr, jTextEntregue, gbc, row++);
        addFormRow(painel, lMatId, jTextMatId, gbc, row++);
        addFormRow(painel, lMatQtd, jTextMatQtd, gbc, row++);

        JPanel itemPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 0));
        itemPanel.setBackground(new java.awt.Color(245, 245, 247));
        itemPanel.add(btnAddMat);
        itemPanel.add(jLabelItensCount);

        gbc.gridx = 1; gbc.gridy = row++; 
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        painel.add(itemPanel, gbc);

        JPanel btnPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 0));
        btnPanel.setBackground(new java.awt.Color(245, 245, 247));
        btnPanel.add(btnLimpar);
        btnPanel.add(btnSalvar);

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2; 
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(30, 0, 0, 0); // Top margin for buttons
        painel.add(btnPanel, gbc);

        return painel;
    }

    private JPanel criarPainelVisualizar() {
        JPanel painel = new JPanel(new java.awt.BorderLayout(10, 10));
        painel.setBackground(new java.awt.Color(245, 245, 247));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Demandas Cadastradas");
        styleTitle(titulo);
        painel.add(titulo, java.awt.BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[] { "ID", "ID Cliente", "Descrição", "Data Solicitação", "Entregue" }, 0) {
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

    private void adicionarItem() {
        try {
            int idMat = Integer.parseInt(jTextMatId.getText().trim());
            int qtd = Integer.parseInt(jTextMatQtd.getText().trim());
            if (qtd <= 0) {
                JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero.");
                return;
            }
            itens.add(new int[] { idMat, qtd });
            jLabelItensCount.setText("Itens: " + itens.size());
            jTextMatId.setText("");
            jTextMatQtd.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID material ou quantidade inválidos.");
        }
    }

    private void salvar() {
        try {
            Demandas d = new Demandas();
            d.setId_cliente(Integer.parseInt(jTextIdCliente.getText().trim()));
            String desc = jTextDescricao.getText().trim();
            if (desc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Descrição não pode estar vazia.");
                return;
            }
            d.setDescricao(desc);
            d.setData_solicitacao(jTextData.getText().trim());
            String ent = jTextEntregue.getText().trim().toUpperCase();
            if (!ent.equals("S") && !ent.equals("N")) {
                JOptionPane.showMessageDialog(this, "Entregue deve ser 'S' ou 'N'.");
                return;
            }
            d.setEntregueSouN(ent);

            dao_demandas dao = new dao_demandas();
            boolean ok = dao.inserirDadosComMateriais(d, itens);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Demanda inserida!");
                limpar();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao inserir demanda.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID Cliente inválido (número).");
        }
    }

    private void limpar() {
        jTextIdCliente.setText("");
        jTextDescricao.setText("");
        jTextData.setText("");
        jTextEntregue.setText("");
        jTextMatId.setText("");
        jTextMatQtd.setText("");
        itens.clear();
        jLabelItensCount.setText("Itens: 0");
    }

    private void carregarDados() {
        tableModel.setRowCount(0);
        dao_demandas dao = new dao_demandas();
        List<Demandas> lista = dao.listarTodos();
        for (Demandas d : lista) {
            tableModel.addRow(new Object[] { d.getId(), d.getId_cliente(), d.getDescricao(), d.getData_solicitacao(),
                    d.getEntregueSouN() });
        }
    }

    private void editarSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma demanda.");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);

        String idCliente = JOptionPane.showInputDialog(this, "ID Cliente:", tableModel.getValueAt(row, 1));
        if (idCliente == null)
            return;
        String descricao = JOptionPane.showInputDialog(this, "Descrição:", tableModel.getValueAt(row, 2));
        if (descricao == null)
            return;
        String data = JOptionPane.showInputDialog(this, "Data Solicitação:", tableModel.getValueAt(row, 3));
        String entregue = (String) JOptionPane.showInputDialog(this, "Entregue:", "Editar",
                JOptionPane.QUESTION_MESSAGE, null, new String[] { "S", "N" }, tableModel.getValueAt(row, 4));
        if (entregue == null)
            return;

        Demandas d = new Demandas();
        d.setId(id);
        d.setId_cliente(Integer.parseInt(idCliente));
        d.setDescricao(descricao);
        d.setData_solicitacao(data != null ? data : "");
        d.setEntregueSouN(entregue);

        dao_demandas dao = new dao_demandas();
        if (dao.atualizar(d)) {
            JOptionPane.showMessageDialog(this, "Atualizada!");
            carregarDados();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar!");
        }
    }

    private void excluirSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma demanda.");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        if (JOptionPane.showConfirmDialog(this, "Excluir demanda ID " + id + "?", "Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dao_demandas dao = new dao_demandas();
            if (dao.deletar(id)) {
                JOptionPane.showMessageDialog(this, "Excluída!");
                carregarDados();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir!");
            }
        }
    }
}
