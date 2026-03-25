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

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Garantias");
        setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Inserir", criarPainelInserir());
        tabbedPane.addTab("Visualizar", criarPainelVisualizar());
        tabbedPane.addChangeListener(evt -> { if (tabbedPane.getSelectedIndex() == 1) carregarDados(); });

        getContentPane().add(tabbedPane);
        setSize(620, 420);
        setLocationRelativeTo(null);
    }

    private JPanel criarPainelInserir() {
        JPanel painel = new JPanel();
        JLabel titulo = new JLabel("CADASTRAR GARANTIA");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEtchedBorder());

        JLabel lCli = new JLabel("ID Cliente:");
        JLabel lMat = new JLabel("ID Material:");
        JLabel lMeses = new JLabel("Meses de Garantia:");
        JLabel lData = new JLabel("Data Compra (AAAA-MM-DD):");

        jTextCliente = new JTextField();
        jTextMaterial = new JTextField();
        jTextMeses = new JTextField();
        jTextData = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(evt -> salvar());
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(evt -> { jTextCliente.setText(""); jTextMaterial.setText(""); jTextMeses.setText(""); jTextData.setText(""); });

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
                                    .addComponent(lCli).addComponent(lMat).addComponent(lMeses).addComponent(lData))
                                .addGap(18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextCliente).addComponent(jTextMaterial)
                                    .addComponent(jTextMeses).addComponent(jTextData, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))))
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup().addContainerGap()
                .addComponent(titulo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lCli).addComponent(jTextCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lMat).addComponent(jTextMaterial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lMeses).addComponent(jTextMeses, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lData).addComponent(jTextData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnSalvar).addComponent(btnLimpar))
                .addContainerGap(20, Short.MAX_VALUE)
        );
        return painel;
    }

    private JPanel criarPainelVisualizar() {
        JPanel painel = new JPanel();
        JLabel titulo = new JLabel("GARANTIAS CADASTRADAS");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEtchedBorder());

        tableModel = new DefaultTableModel(new String[]{"ID Garantia", "ID Cliente", "ID Material", "ID Demanda", "Meses", "Data Compra"}, 0) {
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
                jTextCliente.setText(""); jTextMaterial.setText(""); jTextMeses.setText(""); jTextData.setText("");
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
            tableModel.addRow(new Object[]{g.getId_garantia(), g.getId_cliente(), g.getId_material(), g.getId_demanda(), g.getMeses_garantia(), g.getData_compra()});
        }
    }

    private void editarSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione uma garantia."); return; }
        int id = (int) tableModel.getValueAt(row, 0);

        String idCliente = JOptionPane.showInputDialog(this, "ID Cliente:", tableModel.getValueAt(row, 1));
        if (idCliente == null) return;
        String idMaterial = JOptionPane.showInputDialog(this, "ID Material:", tableModel.getValueAt(row, 2));
        if (idMaterial == null) return;
        String idDemanda = JOptionPane.showInputDialog(this, "ID Demanda:", tableModel.getValueAt(row, 3));
        if (idDemanda == null) return;
        String meses = JOptionPane.showInputDialog(this, "Meses Garantia:", tableModel.getValueAt(row, 4));
        if (meses == null) return;
        String data = JOptionPane.showInputDialog(this, "Data Compra:", tableModel.getValueAt(row, 5));

        Garantia g = new Garantia();
        g.setId_garantia(id);
        g.setId_cliente(Integer.parseInt(idCliente));
        g.setId_material(Integer.parseInt(idMaterial));
        g.setId_demanda(Integer.parseInt(idDemanda));
        g.setMeses_garantia(Integer.parseInt(meses));
        g.setData_compra(data != null ? data : "");

        dao_garantia dao = new dao_garantia();
        if (dao.atualizar(g)) { JOptionPane.showMessageDialog(this, "Atualizado!"); carregarDados(); }
        else { JOptionPane.showMessageDialog(this, "Erro ao atualizar!"); }
    }

    private void excluirSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione uma garantia."); return; }
        int id = (int) tableModel.getValueAt(row, 0);
        if (JOptionPane.showConfirmDialog(this, "Excluir garantia ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dao_garantia dao = new dao_garantia();
            if (dao.deletar(id)) { JOptionPane.showMessageDialog(this, "Excluído!"); carregarDados(); }
            else { JOptionPane.showMessageDialog(this, "Erro ao excluir!"); }
        }
    }
}
