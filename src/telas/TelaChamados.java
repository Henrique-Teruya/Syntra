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

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chamados");
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
        JLabel jLabelTitulo = new JLabel("CADASTRO DE CHAMADO");
        jLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelTitulo.setBorder(BorderFactory.createEtchedBorder());

        JLabel lIdCli = new JLabel("ID Cliente:");
        JLabel lDesc = new JLabel("Descrição:");
        JLabel lData = new JLabel("Data Abertura (AAAA-MM-DD):");

        jTextIdCliente = new JTextField();
        jTextDescricao = new JTextField();
        jTextData = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(evt -> salvar());
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(evt -> { jTextIdCliente.setText(""); jTextDescricao.setText(""); jTextData.setText(""); });

        GroupLayout layout = new GroupLayout(painel);
        painel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup().addComponent(btnLimpar).addGap(50).addComponent(btnSalvar))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelTitulo, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(lIdCli).addComponent(lDesc).addComponent(lData))
                                .addGap(18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextIdCliente).addComponent(jTextDescricao)
                                    .addComponent(jTextData, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))))
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup().addContainerGap()
                .addComponent(jLabelTitulo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lIdCli).addComponent(jTextIdCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lDesc).addComponent(jTextDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lData).addComponent(jTextData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnSalvar).addComponent(btnLimpar))
                .addContainerGap(20, Short.MAX_VALUE)
        );
        return painel;
    }

    private JPanel criarPainelVisualizar() {
        JPanel painel = new JPanel();
        JLabel jLabelTitulo = new JLabel("CHAMADOS CADASTRADOS");
        jLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelTitulo.setBorder(BorderFactory.createEtchedBorder());

        tableModel = new DefaultTableModel(new String[]{"ID", "ID Cliente", "Descrição", "Data Abertura", "Status"}, 0) {
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
                .addComponent(jLabelTitulo, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(scrollPane).addContainerGap())
                .addGroup(layout.createSequentialGroup().addGap(50).addComponent(btnAtualizar).addGap(40).addComponent(btnEditar).addGap(40).addComponent(btnExcluir).addGap(50))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup().addContainerGap()
                .addComponent(jLabelTitulo, 40, 40, 40).addGap(10)
                .addComponent(scrollPane, 220, 220, Short.MAX_VALUE).addGap(12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnAtualizar).addComponent(btnEditar).addComponent(btnExcluir))
                .addContainerGap(12, Short.MAX_VALUE)
        );
        return painel;
    }

    private void salvar() {
        try {
            String idCliTxt = jTextIdCliente.getText().trim();
            if (idCliTxt.isEmpty()) { JOptionPane.showMessageDialog(this, "ID Cliente é obrigatório."); return; }
            int idCli = Integer.parseInt(idCliTxt);
            String desc = jTextDescricao.getText().trim();
            if (desc.isEmpty()) { JOptionPane.showMessageDialog(this, "Descrição é obrigatória."); return; }
            String data = jTextData.getText().trim();

            Chamados ch = new Chamados();
            ch.setId_cliente(idCli);
            ch.setDescricao(desc);
            ch.setData_abertura(data);
            ch.setStatus("ABERTO");

            dao_chamados dao = new dao_chamados();
            if (dao.inserirDados(ch)) {
                JOptionPane.showMessageDialog(this, "Chamado inserido com sucesso!");
                jTextIdCliente.setText(""); jTextDescricao.setText(""); jTextData.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao inserir chamado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID Cliente inválido (somente números).");
        }
    }

    private void carregarDados() {
        tableModel.setRowCount(0);
        dao_chamados dao = new dao_chamados();
        List<Chamados> lista = dao.listarTodos();
        for (Chamados ch : lista) {
            tableModel.addRow(new Object[]{ch.getId_chamado(), ch.getId_cliente(), ch.getDescricao(), ch.getData_abertura(), ch.getStatus()});
        }
    }

    private void editarSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione um chamado."); return; }
        int id = (int) tableModel.getValueAt(row, 0);

        String idCliente = JOptionPane.showInputDialog(this, "ID Cliente:", tableModel.getValueAt(row, 1));
        if (idCliente == null) return;
        String descricao = JOptionPane.showInputDialog(this, "Descrição:", tableModel.getValueAt(row, 2));
        if (descricao == null) return;
        String data = JOptionPane.showInputDialog(this, "Data Abertura:", tableModel.getValueAt(row, 3));
        String status = (String) JOptionPane.showInputDialog(this, "Status:", "Editar Status",
            JOptionPane.QUESTION_MESSAGE, null, new String[]{"ABERTO", "EM ANDAMENTO", "FECHADO"}, tableModel.getValueAt(row, 4));
        if (status == null) return;

        Chamados ch = new Chamados();
        ch.setId_chamado(id);
        ch.setId_cliente(Integer.parseInt(idCliente));
        ch.setDescricao(descricao);
        ch.setData_abertura(data != null ? data : "");
        ch.setStatus(status);

        dao_chamados dao = new dao_chamados();
        if (dao.atualizar(ch)) { JOptionPane.showMessageDialog(this, "Atualizado!"); carregarDados(); }
        else { JOptionPane.showMessageDialog(this, "Erro ao atualizar!"); }
    }

    private void excluirSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione um chamado."); return; }
        int id = (int) tableModel.getValueAt(row, 0);
        if (JOptionPane.showConfirmDialog(this, "Excluir chamado ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dao_chamados dao = new dao_chamados();
            if (dao.deletar(id)) { JOptionPane.showMessageDialog(this, "Excluído!"); carregarDados(); }
            else { JOptionPane.showMessageDialog(this, "Erro ao excluir!"); }
        }
    }
}
