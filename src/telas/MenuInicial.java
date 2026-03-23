package telas;

public class MenuInicial extends javax.swing.JFrame {

    public MenuInicial() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        btnClientes = new javax.swing.JButton();
        btnDemandas = new javax.swing.JButton();
        btnEstoque = new javax.swing.JButton();
        btnChamados = new javax.swing.JButton();
        btnGarantia = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setResizable(false);

        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("SISTEMA SYNTRA");
        jLabelTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnClientes.setText("Clientes");
        btnClientes.addActionListener(evt -> new TelaClientes().setVisible(true));

        btnDemandas.setText("Demandas");
        btnDemandas.addActionListener(evt -> new TelaDemandas().setVisible(true));

        btnEstoque.setText("Estoque");
        btnEstoque.addActionListener(evt -> new TelaEstoque().setVisible(true));

        btnChamados.setText("Chamados");
        btnChamados.addActionListener(evt -> new TelaChamados().setVisible(true));

        btnGarantia.setText("Garantia");
        btnGarantia.addActionListener(evt -> new TelaGarantia().setVisible(true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addComponent(jLabelTitulo, 300, 300, 300)
            .addGroup(layout.createSequentialGroup()
                .addGap(60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnClientes, 180, 180, 180)
                    .addComponent(btnDemandas, 180, 180, 180)
                    .addComponent(btnEstoque, 180, 180, 180)
                    .addComponent(btnChamados, 180, 180, 180)
                    .addComponent(btnGarantia, 180, 180, 180))
                .addGap(60))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(20)
                .addComponent(jLabelTitulo, 40, 40, 40)
                .addGap(30)
                .addComponent(btnClientes, 35, 35, 35)
                .addGap(15)
                .addComponent(btnDemandas, 35, 35, 35)
                .addGap(15)
                .addComponent(btnEstoque, 35, 35, 35)
                .addGap(15)
                .addComponent(btnChamados, 35, 35, 35)
                .addGap(15)
                .addComponent(btnGarantia, 35, 35, 35)
                .addGap(30)
        );

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new MenuInicial().setVisible(true));
    }

    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JButton btnClientes, btnDemandas, btnEstoque, btnChamados, btnGarantia;
}
