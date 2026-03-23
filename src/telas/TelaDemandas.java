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

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Demandas");
        setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Inserir", criarPainelInserir());
        tabbedPane.addTab("Visualizar", criarPainelVisualizar());
        tabbedPane.addChangeListener(evt -> { if (tabbedPane.getSelectedIndex() == 1) carregarDados(); });

        getContentPane().add(tabbedPane);
        setSize(620, 480);
        setLocationRelativeTo(null);
    }

    private JPanel criarPainelInserir() {
        JPanel painel = new JPanel();
        JLabel titulo = new JLabel("INSERIR DEMANDA");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEtchedBorder());

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

        JButton btnAddMat = new JButton("Adicionar Item");
        btnAddMat.addActionListener(evt -> adicionarItem());
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(evt -> salvar());
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(evt -> limpar());

        GroupLayout layout = new GroupLayout(painel);
        painel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(titulo, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lIdCli).addComponent(lDesc).addComponent(lData).addComponent(lEntr)
                        .addComponent(lMatId).addComponent(lMatQtd))
                    .addGap(18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextIdCliente).addComponent(jTextDescricao).addComponent(jTextData)
                        .addComponent(jTextEntregue).addComponent(jTextMatId)
                        .addComponent(jTextMatQtd, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                    .addContainerGap())
                .addGroup(layout.createSequentialGroup().addContainerGap()
                    .addComponent(btnAddMat).addGap(18).addComponent(jLabelItensCount).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup().addGap(80).addComponent(btnLimpar).addGap(60).addComponent(btnSalvar).addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup().addContainerGap()
                .addComponent(titulo, 40, 40, 40).addGap(12)
                .addGroup(layout.createParallelGroup().addComponent(lIdCli).addComponent(jTextIdCliente, 25, 25, 25)).addGap(8)
                .addGroup(layout.createParallelGroup().addComponent(lDesc).addComponent(jTextDescricao, 25, 25, 25)).addGap(8)
                .addGroup(layout.createParallelGroup().addComponent(lData).addComponent(jTextData, 25, 25, 25)).addGap(8)
                .addGroup(layout.createParallelGroup().addComponent(lEntr).addComponent(jTextEntregue, 25, 25, 25)).addGap(15)
                .addGroup(layout.createParallelGroup().addComponent(lMatId).addComponent(jTextMatId, 25, 25, 25)).addGap(8)
                .addGroup(layout.createParallelGroup().addComponent(lMatQtd).addComponent(jTextMatQtd, 25, 25, 25)).addGap(8)
                .addGroup(layout.createParallelGroup().addComponent(btnAddMat).addComponent(jLabelItensCount)).addGap(15)
                .addGroup(layout.createParallelGroup().addComponent(btnLimpar).addComponent(btnSalvar)).addGap(12)
        );
        return painel;
    }

    private JPanel criarPainelVisualizar() {
        JPanel painel = new JPanel();
        JLabel titulo = new JLabel("DEMANDAS CADASTRADAS");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEtchedBorder());

        tableModel = new DefaultTableModel(new String[]{"ID", "ID Cliente", "Descrição", "Data Solicitação", "Entregue"}, 0) {
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
                .addComponent(titulo, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
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

    private void adicionarItem() {
        try {
            int idMat = Integer.parseInt(jTextMatId.getText().trim());
            int qtd = Integer.parseInt(jTextMatQtd.getText().trim());
            if (qtd <= 0) { JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero."); return; }
            itens.add(new int[]{idMat, qtd});
            jLabelItensCount.setText("Itens: " + itens.size());
            jTextMatId.setText(""); jTextMatQtd.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID material ou quantidade inválidos.");
        }
    }

    private void salvar() {
        try {
            Demandas d = new Demandas();
            d.setId_cliente(Integer.parseInt(jTextIdCliente.getText().trim()));
            String desc = jTextDescricao.getText().trim();
            if (desc.isEmpty()) { JOptionPane.showMessageDialog(this, "Descrição não pode estar vazia."); return; }
            d.setDescricao(desc);
            d.setData_solicitacao(jTextData.getText().trim());
            String ent = jTextEntregue.getText().trim().toUpperCase();
            if (!ent.equals("S") && !ent.equals("N")) { JOptionPane.showMessageDialog(this, "Entregue deve ser 'S' ou 'N'."); return; }
            d.setEntregueSouN(ent);

            dao_demandas dao = new dao_demandas();
            boolean ok = dao.inserirDadosComMateriais(d, itens);
            if (ok) { JOptionPane.showMessageDialog(this, "Demanda inserida!"); limpar(); }
            else { JOptionPane.showMessageDialog(this, "Erro ao inserir demanda."); }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID Cliente inválido (número).");
        }
    }

    private void limpar() {
        jTextIdCliente.setText(""); jTextDescricao.setText(""); jTextData.setText("");
        jTextEntregue.setText(""); jTextMatId.setText(""); jTextMatQtd.setText("");
        itens.clear(); jLabelItensCount.setText("Itens: 0");
    }

    private void carregarDados() {
        tableModel.setRowCount(0);
        dao_demandas dao = new dao_demandas();
        List<Demandas> lista = dao.listarTodos();
        for (Demandas d : lista) {
            tableModel.addRow(new Object[]{d.getId(), d.getId_cliente(), d.getDescricao(), d.getData_solicitacao(), d.getEntregueSouN()});
        }
    }

    private void editarSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione uma demanda."); return; }
        int id = (int) tableModel.getValueAt(row, 0);

        String idCliente = JOptionPane.showInputDialog(this, "ID Cliente:", tableModel.getValueAt(row, 1));
        if (idCliente == null) return;
        String descricao = JOptionPane.showInputDialog(this, "Descrição:", tableModel.getValueAt(row, 2));
        if (descricao == null) return;
        String data = JOptionPane.showInputDialog(this, "Data Solicitação:", tableModel.getValueAt(row, 3));
        String entregue = (String) JOptionPane.showInputDialog(this, "Entregue:", "Editar",
            JOptionPane.QUESTION_MESSAGE, null, new String[]{"S", "N"}, tableModel.getValueAt(row, 4));
        if (entregue == null) return;

        Demandas d = new Demandas();
        d.setId(id);
        d.setId_cliente(Integer.parseInt(idCliente));
        d.setDescricao(descricao);
        d.setData_solicitacao(data != null ? data : "");
        d.setEntregueSouN(entregue);

        dao_demandas dao = new dao_demandas();
        if (dao.atualizar(d)) { JOptionPane.showMessageDialog(this, "Atualizada!"); carregarDados(); }
        else { JOptionPane.showMessageDialog(this, "Erro ao atualizar!"); }
    }

    private void excluirSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione uma demanda."); return; }
        int id = (int) tableModel.getValueAt(row, 0);
        if (JOptionPane.showConfirmDialog(this, "Excluir demanda ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dao_demandas dao = new dao_demandas();
            if (dao.deletar(id)) { JOptionPane.showMessageDialog(this, "Excluída!"); carregarDados(); }
            else { JOptionPane.showMessageDialog(this, "Erro ao excluir!"); }
        }
    }
}
