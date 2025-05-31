package MyArkanoid;


import java.awt.Window;
import javax.swing.SwingUtilities;
/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */

public class Niveis extends javax.swing.JFrame {

    private MenuPausa menuPausa;
    private ArkanoidGame jogoAtual;
    public Niveis() {
        initComponents();
        setLocationRelativeTo(null);//coloca ao centro
        this.menuPausa = menuPausa;
        this.jogoAtual = jogoAtual;
    }

    public Niveis(MenuPausa menuPausa, ArkanoidGame jogoAtual) {
    initComponents();
    setLocationRelativeTo(null);
    this.menuPausa = menuPausa;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnivel2 = new javax.swing.JButton();
        btnivel1 = new javax.swing.JButton();
        voltar = new javax.swing.JButton();
        fundojogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 300));
        setMinimumSize(new java.awt.Dimension(500, 426));
        setPreferredSize(new java.awt.Dimension(500, 429));
        setResizable(false);
        getContentPane().setLayout(null);

        btnivel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btnivel2.setText("Nivel 2");
        btnivel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnivel2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnivel2);
        btnivel2.setBounds(200, 140, 90, 30);

        btnivel1.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btnivel1.setText("Nivel 1");
        btnivel1.setBorder(null);
        btnivel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnivel1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnivel1);
        btnivel1.setBounds(200, 70, 90, 30);

        voltar.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });
        getContentPane().add(voltar);
        voltar.setBounds(200, 330, 80, 30);

        fundojogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/smashtheagesnosniveis.png"))); // NOI18N
        fundojogo.setMaximumSize(new java.awt.Dimension(300, 300));
        fundojogo.setMinimumSize(new java.awt.Dimension(300, 300));
        getContentPane().add(fundojogo);
        fundojogo.setBounds(0, 0, 490, 390);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        this.setVisible(false); // Fecha o menu de níveis
    if (menuPausa != null) {
        menuPausa.setVisible(true); // Volta ao menu de pausa
    }
    }//GEN-LAST:event_voltarActionPerformed

    private void btnivel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnivel1ActionPerformed
       this.setVisible(false);

        // Fecha o jogo atual, se existir
        if (jogoAtual != null) {
            Window janelaDoJogo = SwingUtilities.getWindowAncestor(jogoAtual);
            if (janelaDoJogo != null) {
                janelaDoJogo.dispose();
            }
        }

        // Abre o nível 2
        playGame2 jogo2 = new playGame2();
        jogo2.setVisible(true);

        // Fecha o menu pausa também, se estiver aberto
        if (menuPausa != null) {
            menuPausa.dispose();
        }
    }//GEN-LAST:event_btnivel1ActionPerformed

    private void btnivel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnivel2ActionPerformed
                                        
    setVisible(false);
    new playGame2().setVisible(true);
// Fecha o menu de níveis
    this.setVisible(false);

    // Fecha o jogo atual, se existir
    if (jogoAtual != null) {
        Window janelaDoJogo = SwingUtilities.getWindowAncestor(jogoAtual);
        if (janelaDoJogo != null) {
            janelaDoJogo.dispose();
        }
    }

    // Abre o nível 2
    playGame2 jogo2 = new playGame2();
    jogo2.setVisible(true);

    // Fecha o menu pausa também, se estiver aberto
    if (menuPausa != null) {
        menuPausa.dispose();
    }
    }//GEN-LAST:event_btnivel2ActionPerformed

    
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
            java.util.logging.Logger.getLogger(Niveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Niveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Niveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Niveis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Niveis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnivel1;
    private javax.swing.JButton btnivel2;
    private javax.swing.JLabel fundojogo;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
