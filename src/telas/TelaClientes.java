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
        setSize(720, 480);
        setLocationRelativeTo(null);
    }

    // ===================== PAINEL INSERIR =====================
    private JPanel criarPainelInserir() {
        JPanel painel = new JPanel();

        JLabel jLabelTitulo = new JLabel("CADASTRO DE CLIENTE");
        jLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelTitulo.setBorder(BorderFactory.createEtchedBorder());

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
        jButtonSalvar.addActionListener(evt -> salvar());

        JButton jButtonLimpar = new JButton("Limpar");
        jButtonLimpar.addActionListener(evt -> limparCampos());

        GroupLayout layout = new GroupLayout(painel);
        painel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonLimpar)
                            .addGap(50)
                            .addComponent(jButtonSalvar))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelTitulo, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTipo)
                                    .addComponent(jLabelNome)
                                    .addComponent(jLabelGrupo)
                                    .addComponent(jLabelDocumento)
                                    .addComponent(jLabelCep)
                                    .addComponent(jLabelBairro)
                                    .addComponent(jLabelRua))
                                .addGap(18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboTipo, 0, 250, Short.MAX_VALUE)
                                    .addComponent(jTextNome)
                                    .addComponent(jTextGrupo)
                                    .addComponent(jTextDocumento)
                                    .addComponent(jTextCep)
                                    .addComponent(jTextBairro)
                                    .addComponent(jTextRua)))))
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTipo).addComponent(jComboTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome).addComponent(jTextNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelGrupo).addComponent(jTextGrupo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDocumento).addComponent(jTextDocumento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCep).addComponent(jTextCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBairro).addComponent(jTextBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRua).addComponent(jTextRua, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar).addComponent(jButtonLimpar))
                .addContainerGap(20, Short.MAX_VALUE)
        );
        return painel;
    }

    // ===================== PAINEL VISUALIZAR =====================
    private JPanel criarPainelVisualizar() {
        JPanel painel = new JPanel();

        JLabel jLabelTitulo = new JLabel("CLIENTES CADASTRADOS");
        jLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelTitulo.setBorder(BorderFactory.createEtchedBorder());

        tableModel = new DefaultTableModel(
            new String[]{"ID", "Nome", "Tipo", "CPF", "CNPJ", "Grupo", "CEP", "Bairro", "Rua"}, 0) {
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
                .addComponent(jLabelTitulo, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane)
                    .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                    .addGap(50)
                    .addComponent(btnAtualizar).addGap(40)
                    .addComponent(btnEditar).addGap(40)
                    .addComponent(btnExcluir).addGap(50))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo, 40, 40, 40)
                .addGap(10)
                .addComponent(scrollPane, 250, 250, Short.MAX_VALUE)
                .addGap(12)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtualizar).addComponent(btnEditar).addComponent(btnExcluir))
                .addContainerGap(12, Short.MAX_VALUE)
        );
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

            if (nome.isEmpty()) { JOptionPane.showMessageDialog(this, "Nome é obrigatório."); return; }

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
            if (tipo.equals("PESSOA")) { c.setCPF(documento); c.setCNPJ(""); }
            else { c.setCNPJ(documento); c.setCPF(""); }
            c.setCEP(cep);
            c.setBairro(bairro);
            c.setRua(rua);

            dao_clientes dao = new dao_clientes();
            if (dao.inserirDados(c)) {
                JOptionPane.showMessageDialog(this, "Cliente inserido com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao inserir cliente!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void limparCampos() {
        jComboTipo.setSelectedIndex(0);
        jLabelDocumento.setText("CNPJ:");
        jTextNome.setText(""); jTextGrupo.setText(""); jTextDocumento.setText("");
        jTextCep.setText(""); jTextBairro.setText(""); jTextRua.setText("");
    }

    private void carregarDados() {
        tableModel.setRowCount(0);
        dao_clientes dao = new dao_clientes();
        List<Cliente> lista = dao.listarTodos();
        for (Cliente c : lista) {
            tableModel.addRow(new Object[]{
                c.getId_cliente(), c.getNome(), c.getTipo(), c.getCPF(), c.getCNPJ(),
                c.getGrupo(), c.getCEP(), c.getBairro(), c.getRua()
            });
        }
    }

    private void editarSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione um cliente."); return; }

        int id = (int) tableModel.getValueAt(row, 0);
        String nome = JOptionPane.showInputDialog(this, "Nome:", tableModel.getValueAt(row, 1));
        if (nome == null) return;
        String tipo = (String) JOptionPane.showInputDialog(this, "Tipo:", "Editar Tipo",
            JOptionPane.QUESTION_MESSAGE, null, new String[]{"EMPRESA", "PESSOA"}, tableModel.getValueAt(row, 2));
        if (tipo == null) return;
        String cpf = JOptionPane.showInputDialog(this, "CPF:", tableModel.getValueAt(row, 3));
        if (cpf == null) return;
        String cnpj = JOptionPane.showInputDialog(this, "CNPJ:", tableModel.getValueAt(row, 4));
        if (cnpj == null) return;
        String grupo = JOptionPane.showInputDialog(this, "Grupo:", tableModel.getValueAt(row, 5));
        if (grupo == null) return;
        String cep = JOptionPane.showInputDialog(this, "CEP:", tableModel.getValueAt(row, 6));
        if (cep == null) return;
        String bairro = JOptionPane.showInputDialog(this, "Bairro:", tableModel.getValueAt(row, 7));
        if (bairro == null) return;
        String rua = JOptionPane.showInputDialog(this, "Rua:", tableModel.getValueAt(row, 8));
        if (rua == null) return;

        Cliente c = new Cliente();
        c.setId_cliente(id);
        c.setNome(nome);
        c.setTipo(tipo);
        c.setCPF(cpf);
        c.setCNPJ(cnpj);
        c.setGrupo(grupo != null ? grupo : "");
        c.setCEP(cep != null ? cep : "");
        c.setBairro(bairro != null ? bairro : "");
        c.setRua(rua != null ? rua : "");

        dao_clientes dao = new dao_clientes();
        if (dao.atualizar(c)) {
            JOptionPane.showMessageDialog(this, "Cliente atualizado!");
            carregarDados();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar!");
        }
    }

    private void excluirSelecionado() {
        int row = jTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Selecione um cliente."); return; }
        int id = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Excluir cliente ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dao_clientes dao = new dao_clientes();
            if (dao.deletar(id)) {
                JOptionPane.showMessageDialog(this, "Excluído!");
                carregarDados();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir!");
            }
        }
    }
}
