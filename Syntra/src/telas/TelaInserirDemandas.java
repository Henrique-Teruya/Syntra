package telas;

import dao_tabela_atributos.dao_demandas;
import tabelaatributos.Demandas;

/**
 * Tela para inserir dados na tabela demandas
 * Segue o modelo do professor Ozias G. Santos
 */
public class TelaInserirDemandas extends javax.swing.JFrame {

    public TelaInserirDemandas() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        jLabelIdCliente = new javax.swing.JLabel();
        jLabelDescricao = new javax.swing.JLabel();
        jLabelData = new javax.swing.JLabel();
        jLabelEntregue = new javax.swing.JLabel();

        jTextIdCliente = new javax.swing.JTextField();
        jTextDescricao = new javax.swing.JTextField();
        jTextData = new javax.swing.JTextField();

        jComboEntregue = new javax.swing.JComboBox<>();

        jButtonSalvar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Demandas");
        setResizable(false);

        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("CADASTRO DE DEMANDAS");
        jLabelTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelIdCliente.setText("ID Cliente:");
        jLabelDescricao.setText("Descrição:");
        jLabelData.setText("Data Solicitação (AAAA-MM-DD):");
        jLabelEntregue.setText("Entregue (S/N):");

        jComboEntregue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "N" }));

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelIdCliente)
                            .addComponent(jLabelDescricao)
                            .addComponent(jLabelData)
                            .addComponent(jLabelEntregue))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextIdCliente)
                            .addComponent(jTextDescricao)
                            .addComponent(jTextData)
                            .addComponent(jComboEntregue, 0, 200, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonLimpar)
                        .addGap(50, 50, 50)
                        .addComponent(jButtonSalvar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIdCliente)
                    .addComponent(jTextIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDescricao)
                    .addComponent(jTextDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelData)
                    .addComponent(jTextData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEntregue)
                    .addComponent(jComboEntregue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonLimpar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        Demandas demanda = new Demandas();
        demanda.setId(Integer.parseInt(jTextIdCliente.getText()));
        demanda.setDescricao(jTextDescricao.getText());
        demanda.setData_solicitacao(jTextData.getText());
        demanda.setEntregueSouN((String) jComboEntregue.getSelectedItem());

        dao_demandas dao = new dao_demandas();
        dao.inserirDados(demanda);
    }

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {
        jTextIdCliente.setText("");
        jTextDescricao.setText("");
        jTextData.setText("");
        jComboEntregue.setSelectedIndex(0);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaInserirDemandas().setVisible(true));
    }

    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JComboBox<String> jComboEntregue;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelIdCliente;
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelEntregue;
    private javax.swing.JTextField jTextIdCliente;
    private javax.swing.JTextField jTextDescricao;
    private javax.swing.JTextField jTextData;
}
