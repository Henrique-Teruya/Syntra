package telas;

import dao_tabela_atributos.dao_garantia;
import tabelaatributos.Garantia;

public class TelaInserirGarantia extends javax.swing.JFrame {

    public TelaInserirGarantia() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        jLabelIdCliente = new javax.swing.JLabel();
        jLabelIdMaterial = new javax.swing.JLabel();
        jLabelMeses = new javax.swing.JLabel();
        jLabelData = new javax.swing.JLabel();

        jTextIdCliente = new javax.swing.JTextField();
        jTextIdMaterial = new javax.swing.JTextField();
        jTextMeses = new javax.swing.JTextField();
        jTextData = new javax.swing.JTextField();

        jButtonSalvar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inserir Garantia");
        setResizable(false);

        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("INSERIR GARANTIA");
        jLabelTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelIdCliente.setText("ID Cliente:");
        jLabelIdMaterial.setText("ID Material:");
        jLabelMeses.setText("Meses Garantia:");
        jLabelData.setText("Data Compra (AAAA-MM-DD):");

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
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelIdCliente)
                            .addComponent(jLabelIdMaterial)
                            .addComponent(jLabelMeses)
                            .addComponent(jLabelData))
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextIdMaterial)
                            .addComponent(jTextMeses)
                            .addComponent(jTextData, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonLimpar)
                        .addGap(40, 40, 40)
                        .addComponent(jButtonSalvar)))
                .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18)

                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIdCliente)
                    .addComponent(jTextIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

                .addGap(12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIdMaterial)
                    .addComponent(jTextIdMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

                .addGap(12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMeses)
                    .addComponent(jTextMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

                .addGap(12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelData)
                    .addComponent(jTextData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

                .addGap(25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonLimpar))

                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Garantia g = new Garantia();

            g.setId_cliente(Integer.parseInt(jTextIdCliente.getText()));
            g.setId_material(Integer.parseInt(jTextIdMaterial.getText()));
            g.setMeses_garantia(Integer.parseInt(jTextMeses.getText()));
            g.setData_compra(jTextData.getText());

            dao_garantia dao = new dao_garantia();
            dao.inserirGarantia(g);

            System.out.println("Garantia inserida!");
        } catch (Exception e) {
            System.out.println("Erro ao inserir garantia: " + e.getMessage());
        }
    }

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {
        jTextIdCliente.setText("");
        jTextIdMaterial.setText("");
        jTextMeses.setText("");
        jTextData.setText("");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaInserirGarantia().setVisible(true));
    }

    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonLimpar;

    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelIdCliente;
    private javax.swing.JLabel jLabelIdMaterial;
    private javax.swing.JLabel jLabelMeses;
    private javax.swing.JLabel jLabelData;

    private javax.swing.JTextField jTextIdCliente;
    private javax.swing.JTextField jTextIdMaterial;
    private javax.swing.JTextField jTextMeses;
    private javax.swing.JTextField jTextData;
}
