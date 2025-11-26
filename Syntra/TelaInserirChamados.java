package telas;

import dao_tabela_atributos.dao_chamados;
import tabelaatributos.Chamados;

public class TelaInserirChamados extends javax.swing.JFrame {

    public TelaInserirChamados() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        jLabelIdCliente = new javax.swing.JLabel();
        jLabelDescricao = new javax.swing.JLabel();
        jLabelData = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();

        jTextIdCliente = new javax.swing.JTextField();
        jTextDescricao = new javax.swing.JTextField();
        jTextData = new javax.swing.JTextField();
        jTextStatus = new javax.swing.JTextField();

        jButtonSalvar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inserir Chamados");
        setResizable(false);

        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("INSERIR CHAMADO");
        jLabelTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelIdCliente.setText("ID Cliente:");
        jLabelDescricao.setText("Descrição:");
        jLabelData.setText("Data Abertura (AAAA-MM-DD):");
        jLabelStatus.setText("Status:");

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
                            .addComponent(jLabelDescricao)
                            .addComponent(jLabelData)
                            .addComponent(jLabelStatus))
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jTextData)
                            .addComponent(jTextStatus)))
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
                    .addComponent(jLabelDescricao)
                    .addComponent(jTextDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

                .addGap(12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelData)
                    .addComponent(jTextData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

                .addGap(12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelStatus)
                    .addComponent(jTextStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))

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
            Chamados c = new Chamados();
            c.setId_cliente(Integer.parseInt(jTextIdCliente.getText()));
            c.setDescricao(jTextDescricao.getText());
            c.setData_abertura(jTextData.getText());
            c.setStatus(jTextStatus.getText());

            dao_chamados dao = new dao_chamados();
            dao.inserirChamado(c);

            System.out.println("Chamado inserido!");
        } catch (Exception e) {
            System.out.println("Erro ao inserir chamado: " + e.getMessage());
        }
    }

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {
        jTextIdCliente.setText("");
        jTextDescricao.setText("");
        jTextData.setText("");
        jTextStatus.setText("");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaInserirChamados().setVisible(true));
    }

    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonLimpar;

    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelIdCliente;
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelStatus;

    private javax.swing.JTextField jTextIdCliente;
    private javax.swing.JTextField jTextDescricao;
    private javax.swing.JTextField jTextData;
    private javax.swing.JTextField jTextStatus;
}
