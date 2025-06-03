package arkanoide_exe;


/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */
public class Regras extends javax.swing.JDialog {

    
    public Regras(java.awt.Frame parent, boolean modal) {
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
        fotopedro = new javax.swing.JLabel();
        btsair = new javax.swing.JButton();
        fundocreditos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(521, 424));
        getContentPane().setLayout(null);

        txtareapedro.setEditable(false);
        txtareapedro.setColumns(20);
        txtareapedro.setLineWrap(true);
        txtareapedro.setRows(5);
        txtareapedro.setText("O jogo é constituido por 3 niveis. A cada nivel que passa, torna-se mais complicado pois ficas com mais blocos para destruir.\n\nTanto podes mover o teu paddle nas setas esquerda (<-) e direita (->) e clicar na tecla ESPAÇO para atirar a bola. Como podes mover o paddle no rato e atirar clicando no rato do lado esquerdo.\n\nGanhas 1 ponto por cada bloco destruido.\n\nTens 2 vidas se as perderes podes reiniciar o nivel.\n\nO tempo está a contar para teres a perseção de quanto tempo demoras a concluir o nivel.\n\nSe precisares de dar pausa basta clicares na tecla ESC , assim tens acesso ao menu de pausa onde consegues voltar para o menu inicial, reiniciar o nivel, continuar o nivel ou seja voltas a onde estavas a jogar e podes guardar o nivel para continuares a jogar mais tarde.\n\nSe quiseres carregar e jogar o nivel que guardas-te, basta clicares no botão de carregar situado no menu inicial e selecionar o teu save.\n\n");
        painelscrollpedro.setViewportView(txtareapedro);

        getContentPane().add(painelscrollpedro);
        painelscrollpedro.setBounds(180, 100, 320, 230);

        txtcreditos.setBackground(new java.awt.Color(255, 255, 255));
        txtcreditos.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        txtcreditos.setForeground(new java.awt.Color(255, 255, 255));
        txtcreditos.setText(" Regras");
        getContentPane().add(txtcreditos);
        txtcreditos.setBounds(190, 10, 160, 48);

        fotopedro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imgregras.png"))); // NOI18N
        getContentPane().add(fotopedro);
        fotopedro.setBounds(10, 70, 160, 190);

        btsair.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btsair.setText("Sair");
        btsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsairActionPerformed(evt);
            }
        });
        getContentPane().add(btsair);
        btsair.setBounds(400, 390, 90, 30);

        fundocreditos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/fundocreditos.png"))); // NOI18N
        getContentPane().add(fundocreditos);
        fundocreditos.setBounds(0, 0, 520, 460);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsairActionPerformed
        this.dispose(); // Fecha o menu das regras
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
            java.util.logging.Logger.getLogger(Regras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Regras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Regras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Regras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Regras dialog = new Regras(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel fotopedro;
    private javax.swing.JLabel fundocreditos;
    private javax.swing.JScrollPane painelscrollpedro;
    private javax.swing.JTextArea txtareapedro;
    private javax.swing.JLabel txtcreditos;
    // End of variables declaration//GEN-END:variables
}
