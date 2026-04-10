package telas;

public class MenuInicial extends javax.swing.JFrame {

    public MenuInicial() {
        initComponents();
    }

    private void styleButton(javax.swing.JButton btn) {
        btn.setBackground(new java.awt.Color(0, 113, 227)); // #0071e3 Apple Blue
        btn.setForeground(java.awt.Color.WHITE);
        btn.setFont(new java.awt.Font("Helvetica Neue", java.awt.Font.PLAIN, 17));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        getContentPane().setBackground(new java.awt.Color(245, 245, 247)); // #f5f5f7 Light Gray

        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Syntra");
        jLabelTitulo.setFont(new java.awt.Font("Helvetica Neue", java.awt.Font.BOLD, 40));
        jLabelTitulo.setForeground(new java.awt.Color(29, 29, 31)); // #1d1d1f Near Black

        btnClientes.setText("Clientes");
        styleButton(btnClientes);
        btnClientes.addActionListener(evt -> new TelaClientes().setVisible(true));

        btnDemandas.setText("Demandas");
        styleButton(btnDemandas);
        btnDemandas.addActionListener(evt -> new TelaDemandas().setVisible(true));

        btnEstoque.setText("Estoque");
        styleButton(btnEstoque);
        btnEstoque.addActionListener(evt -> new TelaEstoque().setVisible(true));

        btnChamados.setText("Chamados");
        styleButton(btnChamados);
        btnChamados.addActionListener(evt -> new TelaChamados().setVisible(true));

        btnGarantia.setText("Garantia");
        styleButton(btnGarantia);
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
                .addGap(40)
                .addComponent(jLabelTitulo, 50, 50, 50)
                .addGap(40)
                .addComponent(btnClientes, 44, 44, 44)
                .addGap(15)
                .addComponent(btnDemandas, 44, 44, 44)
                .addGap(15)
                .addComponent(btnEstoque, 44, 44, 44)
                .addGap(15)
                .addComponent(btnChamados, 44, 44, 44)
                .addGap(15)
                .addComponent(btnGarantia, 44, 44, 44)
                .addGap(40)
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
