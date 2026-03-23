package telas;

import dao_tabela_atributos.dao_estoque;
import tabelaatributos.Estoque;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaEstoque extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private JTable jTable;
    private JTextField jTextDescricao, jTextQuantidade, jTextDataMov;
    private JComboBox<String> jComboTipo;

    public TelaEstoque() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estoque");
        setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Inserir", criarPainelInserir());
        tabbedPane.addTab("Visualizar", criarPainelVisualizar());
        tabbedPane.addChangeListener(evt -> { if (tabbedPane.getSelectedIndex() == 1) carregarDados(); });

        getContentPane().add(tabbedPane);
        setSize(660, 420);
        setLocationRelativeTo(null);
    }

    private JPanel criarPainelInserir() {
        JPanel painel = new JPanel();
        JLabel titulo = new JLabel("MOVIMENTO DE ESTOQUE");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEtchedBorder());

        JLabel lTipo = new JLabel("Tipo:");
        JLabel lDesc = new JLabel("Descrição (apenas ENTRADA):");
        JLabel lQtd = new JLabel("Quantidade:");
        JLabel lData = new JLabel("Data (AAAA-MM-DD):");

        jComboTipo = new JComboBox<>(new String[]{"ENTRADA", "SAIDA"});
        jTextDescricao = new JTextField();
        jTextQuantidade = new JTextField();
        jTextDataMov = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(evt -> salvar());
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(evt -> { jTextDescricao.setText(""); jTextQuantidade.setText(""); jTextDataMov.setText(""); jComboTipo.setSelectedIndex(0); });

        GroupLayout layout = new GroupLayout(painel);
        painel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup().addComponent(btnLimpar).addGap(50).addComponent(btnSalvar))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(titulo, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(lTipo).addComponent(lDesc).addComponent(lQtd).addComponent(lData))
                                .addGap(18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboTipo, 0, 220, Short.MAX_VALUE)
                                    .addComponent(jTextDescricao).addComponent(jTextQuantidade)
                                    .addComponent(jTextDataMov)))))
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup().addContainerGap()
                .addComponent(titulo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lTipo).addComponent(jComboTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lDesc).addComponent(jTextDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lQtd).addComponent(jTextQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lData).addComponent(jTextDataMov, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnSalvar).addComponent(btnLimpar))
                .addContainerGap(20, Short.MAX_VALUE)
        );
        return painel;
    }

    private JPanel criarPainelVisualizar() {
        JPanel painel = new JPanel();
        JLabel titulo = new JLabel("MOVIMENTOS DE ESTOQUE");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEtchedBorder());

        tableModel = new DefaultTableModel(new String[]{"ID Material", "Descrição", "Quantidade", "ID Demanda", "Tipo Mov", "Data Mov"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        jTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(jTable);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton btnAtualizar = new JButton("Atualizar Lista");
        btnAtualizar.addActionListener(evt -> carregarDados());
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(evt -> editarSelecionado());
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(evt -> excluirSelecionado());

        GroupLayout layout = new GroupLayout(painel);
        painel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(titulo, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(scrollPane).addContainerGap())
                .addGroup(layout.createSequentialGroup().addGap(50).addComponent(btnAtualizar).addGap(40).addComponent(btnEditar).addGap(40).addComponent(btnExcluir).addGap(50))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup().addContainerGap()
                .addComponent(titulo, 40, 40, 40).addGap(10)
                .addComponent(scrollPane, 220, 220, Short.MAX_VALUE).addGap(12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnAtualizar).addComponent(btnEditar).addComponent(btnExcluir))
                .addContainerGap(12, Short.MAX_VALUE)
        );
        return painel;
    }

    private void salvar() {
        try {
            String tipo = jComboTipo.getSelectedItem().toString();
            String desc = jTextDescricao.getText().trim();
            int qtd = Integer.parseInt(jTextQuantidade.getText().trim());
            String data = jTextDataMov.getText().trim();

            if (qtd <= 0) { JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero."); return; }
            if (tipo.equals("ENTRADA") && desc.isEmpty()) { JOptionPane.showMessageDialog(this, "Descrição obrigatória para ENTRADA."); return; }

            Estoque mov = new Estoque();
            mov.setTipo_mov(tipo);
            mov.setDescricao(tipo.equals("ENTRADA") ? desc : null);
            mov.setQuantidade(qtd);
            mov.setData_mov(data);

            dao_estoque dao = new dao_estoque();
            if (dao.inserirDados(mov)) {
                JOptionPane.showMessageDialog(this, "Movimento registrado!");
                jTextDescricao.setText(""); jTextQuantidade.setText(""); jTextDataMov.setText(""); jComboTipo.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao registrar movimento.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida.");
        }
    }

    private void carregarDados() {
        tableModel.setRowCount(0);
        dao_estoque dao = new dao_estoque();
        List<Estoque> lista = dao.listarTodos();
        for (Estoque e : lista) {
            tableModel.addRow(new Object[]{e.getId_material(), e.getDescricao(), e.getQuantidade(), e.getId_demanda(), e.getTipo_mov(), e.getData_mov()});
        }
    }

    private void editarSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione um registro."); return; }
        int id = (int) tableModel.getValueAt(row, 0);

        String descricao = JOptionPane.showInputDialog(this, "Descrição:", tableModel.getValueAt(row, 1));
        if (descricao == null) return;
        String quantidade = JOptionPane.showInputDialog(this, "Quantidade:", tableModel.getValueAt(row, 2));
        if (quantidade == null) return;
        String idDemanda = JOptionPane.showInputDialog(this, "ID Demanda (0 se vazio):", tableModel.getValueAt(row, 3));
        String tipoMov = (String) JOptionPane.showInputDialog(this, "Tipo:", "Editar",
            JOptionPane.QUESTION_MESSAGE, null, new String[]{"ENTRADA", "SAIDA"}, tableModel.getValueAt(row, 4));
        if (tipoMov == null) return;
        String dataMov = JOptionPane.showInputDialog(this, "Data:", tableModel.getValueAt(row, 5));

        Estoque e = new Estoque();
        e.setId_material(id);
        e.setDescricao(descricao);
        e.setQuantidade(Integer.parseInt(quantidade));
        e.setId_demanda(idDemanda != null && !idDemanda.isEmpty() ? Integer.parseInt(idDemanda) : 0);
        e.setTipo_mov(tipoMov);
        e.setData_mov(dataMov != null ? dataMov : "");

        dao_estoque dao = new dao_estoque();
        if (dao.atualizar(e)) { JOptionPane.showMessageDialog(this, "Atualizado!"); carregarDados(); }
        else { JOptionPane.showMessageDialog(this, "Erro ao atualizar!"); }
    }

    private void excluirSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione um registro."); return; }
        int id = (int) tableModel.getValueAt(row, 0);
        if (JOptionPane.showConfirmDialog(this, "Excluir registro ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dao_estoque dao = new dao_estoque();
            if (dao.deletar(id)) { JOptionPane.showMessageDialog(this, "Excluído!"); carregarDados(); }
            else { JOptionPane.showMessageDialog(this, "Erro ao excluir!"); }
        }
    }
}
