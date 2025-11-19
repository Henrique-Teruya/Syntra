package telas;

import dao_tabela_atributos.dao_clientes;
import tabelaatributos.Cliente;

/**
 * Tela para inserir dados na tabela cliente
 * Baseado no modelo do professor Ozias G. Santos
 */
public class TelaInserirCliente extends javax.swing.JFrame {

    public TelaInserirCliente() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jLabelGrupo = new javax.swing.JLabel();
        jLabelCnpj = new javax.swing.JLabel();
        jLabelCep = new javax.swing.JLabel();
        jLabelBairro = new javax.swing.JLabel();
        jLabelRua = new javax.swing.JLabel();

        jTextNome = new javax.swing.JTextField();
        jTextGrupo = new javax.swing.JTextField();
        jTextCnpj = new javax.swing.JTextField();
        jTextCep = new javax.swing.JTextField();
        jTextBairro = new javax.swing.JTextField();
        jTextRua = new javax.swing.JTextField();

        jButtonSalvar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Clientes");
        setResizable(false);

        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("CADASTRO DE CLIENTE");
        jLabelTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelNome.setText("Nome:");
        jLabelGrupo.setText("Grupo:");
        jLabelCnpj.setText("CNPJ:");
        jLabelCep.setText("CEP:");
        jLabelBairro.setText("Bairro:");
        jLabelRua.setText("Rua:");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(evt -> jButtonSalvarActionPerformed(evt));

        jButtonLimpar.setText("Limpar");
        jButtonLimpar.addActionListener(evt -> jButtonLimparActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonLimpar)
                        .addGap(50, 50, 50)
                        .addComponent(jButtonSalvar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelNome)
                                .addComponent(jLabelGrupo)
                                .addComponent(jLabelCnpj)
                                .addComponent(jLabelCep)
                                .addComponent(jLabelBairro)
                                .addComponent(jLabelRua))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextNome)
                                .addComponent(jTextGrupo)
                                .addComponent(jTextCnpj)
                                .addComponent(jTextCep)
                                .addComponent(jTextBairro)
                                .addComponent(jTextRua, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelGrupo)
                    .addComponent(jTextGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCnpj)
                    .addComponent(jTextCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCep)
                    .addComponent(jTextCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBairro)
                    .addComponent(jTextBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRua)
                    .addComponent(jTextRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonLimpar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    // BOTÃO SALVAR
    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        Cliente cliente = new Cliente();
        cliente.setNome(jTextNome.getText());
        cliente.setGrupo(jTextGrupo.getText());
        cliente.setCNPJ(jTextCnpj.getText());
        cliente.setCEP(jTextCep.getText());
        cliente.setBairro(jTextBairro.getText());
        cliente.setRua(jTextRua.getText());

        dao_clientes dao = new dao_clientes();
        dao.inserirDados(cliente);
    }

    // BOTÃO LIMPAR
    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {
        jTextNome.setText("");
        jTextGrupo.setText("");
        jTextCnpj.setText("");
        jTextCep.setText("");
        jTextBairro.setText("");
        jTextRua.setText("");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaInserirCliente().setVisible(true));
    }

    // Declaração dos componentes
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelGrupo;
    private javax.swing.JLabel jLabelCnpj;
    private javax.swing.JLabel jLabelCep;
    private javax.swing.JLabel jLabelBairro;
    private javax.swing.JLabel jLabelRua;
    private javax.swing.JTextField jTextNome;
    private javax.swing.JTextField jTextGrupo;
    private javax.swing.JTextField jTextCnpj;
    private javax.swing.JTextField jTextCep;
    private javax.swing.JTextField jTextBairro;
    private javax.swing.JTextField jTextRua;
}
