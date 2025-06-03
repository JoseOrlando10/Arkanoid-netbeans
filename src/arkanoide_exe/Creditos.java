package arkanoide_exe;


/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */
public class Creditos extends javax.swing.JDialog {

    /**
     * Creates new form AboutDialog
     */
    public Creditos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);// coloca no centro do ecra
    }

   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelscrollpedro = new javax.swing.JScrollPane();
        txtareapedro = new javax.swing.JTextArea();
        txtcreditos = new javax.swing.JLabel();
        painelscrolljose = new javax.swing.JScrollPane();
        txtareajose = new javax.swing.JTextArea();
        fotopedro = new javax.swing.JLabel();
        fotojose = new javax.swing.JLabel();
        btsair = new javax.swing.JButton();
        fundocreditos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(521, 424));
        getContentPane().setLayout(null);

        txtareapedro.setEditable(false);
        txtareapedro.setColumns(20);
        txtareapedro.setLineWrap(true);
        txtareapedro.setRows(5);
        txtareapedro.setText("Nome: Pedro Afonso Redol Cotralha Inácio Coelho\n\nCurso: Engenharia Informática\n\nNúmero: 25026\n\nTurma: B\n\nEmail: aluno25026@ipt.pt");
        painelscrollpedro.setViewportView(txtareapedro);

        getContentPane().add(painelscrollpedro);
        painelscrollpedro.setBounds(160, 240, 310, 160);

        txtcreditos.setBackground(new java.awt.Color(255, 255, 255));
        txtcreditos.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        txtcreditos.setForeground(new java.awt.Color(255, 255, 255));
        txtcreditos.setText(" Créditos");
        getContentPane().add(txtcreditos);
        txtcreditos.setBounds(190, 10, 160, 48);

        txtareajose.setEditable(false);
        txtareajose.setColumns(20);
        txtareajose.setLineWrap(true);
        txtareajose.setRows(5);
        txtareajose.setText("Nome: José Orlando Lourenço Martins\n\nCurso: Engenharia Informática\n\nNúmero: 24269\n\nTurma: A\n\nEmail: aluno24269@ipt.pt");
        painelscrolljose.setViewportView(txtareajose);

        getContentPane().add(painelscrolljose);
        painelscrolljose.setBounds(160, 70, 310, 160);

        fotopedro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pedro.jpg"))); // NOI18N
        getContentPane().add(fotopedro);
        fotopedro.setBounds(20, 240, 130, 160);

        fotojose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/josé.jpg"))); // NOI18N
        getContentPane().add(fotojose);
        fotojose.setBounds(20, 70, 130, 160);

        btsair.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btsair.setText("Sair");
        btsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsairActionPerformed(evt);
            }
        });
        getContentPane().add(btsair);
        btsair.setBounds(420, 420, 90, 30);

        fundocreditos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/fundocreditos.png"))); // NOI18N
        getContentPane().add(fundocreditos);
        fundocreditos.setBounds(0, 0, 520, 460);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsairActionPerformed
        this.dispose(); // Fecha os creditos
        new arkanoide_exe.Arkanoide().setVisible(true); // Abre o menu inicial
    }//GEN-LAST:event_btsairActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Creditos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Creditos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Creditos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Creditos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Creditos dialog = new Creditos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btsair;
    private javax.swing.JLabel fotojose;
    private javax.swing.JLabel fotopedro;
    private javax.swing.JLabel fundocreditos;
    private javax.swing.JScrollPane painelscrolljose;
    private javax.swing.JScrollPane painelscrollpedro;
    private javax.swing.JTextArea txtareajose;
    private javax.swing.JTextArea txtareapedro;
    private javax.swing.JLabel txtcreditos;
    // End of variables declaration//GEN-END:variables
}
