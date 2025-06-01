package arkanoide_exe;

import MyArkanoid.Creditos;
import MyArkanoid.Niveis;
import MyArkanoid.playGame;
import java.awt.Image;
import javax.swing.JFrame;

/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 *
 */
public class Arkanoide extends javax.swing.JFrame {

    /**
     * Creates new form Arkanoide
     */
    public Arkanoide() {

        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btCreditos = new javax.swing.JButton();
        btniveis = new javax.swing.JButton();
        btsair = new javax.swing.JButton();
        btjogar = new javax.swing.JButton();
        btCarregar = new javax.swing.JButton();
        btSom = new javax.swing.JButton(MyArkanoid.ArkanoidGame.somAtivo ? "🔊" : "🔇");
        imagemfundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(702, 609));
        setResizable(false);
        getContentPane().setLayout(null);

        btCreditos.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btCreditos.setText("Créditos");
        btCreditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreditosActionPerformed(evt);
            }
        });
        getContentPane().add(btCreditos);
        btCreditos.setBounds(40, 510, 100, 39);

        btniveis.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btniveis.setText("Niveis");
        btniveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btniveisActionPerformed(evt);
            }
        });
        getContentPane().add(btniveis);
        btniveis.setBounds(290, 510, 100, 40);

        btsair.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btsair.setText("Sair");
        btsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsairActionPerformed(evt);
            }
        });
        getContentPane().add(btsair);
        btsair.setBounds(560, 510, 100, 40);

        btjogar.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btjogar.setText("Jogar");
        btjogar.setMaximumSize(new java.awt.Dimension(85, 27));
        btjogar.setMinimumSize(new java.awt.Dimension(85, 27));
        btjogar.setPreferredSize(new java.awt.Dimension(85, 27));
        btjogar.setRequestFocusEnabled(false);
        btjogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btjogarActionPerformed(evt);
            }
        });
        getContentPane().add(btjogar);
        btjogar.setBounds(290, 450, 100, 40);

        btCarregar.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btCarregar.setText("Carregar");
        btCarregar.setMaximumSize(new java.awt.Dimension(85, 27));
        btCarregar.setMinimumSize(new java.awt.Dimension(85, 27));
        btCarregar.setPreferredSize(new java.awt.Dimension(85, 27));
        btCarregar.setRequestFocusEnabled(false);
        btCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCarregarActionPerformed(evt);
            }
        });
        getContentPane().add(btCarregar);
        btCarregar.setBounds(560, 450, 100, 40);

        btSom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSomActionPerformed(evt);
            }
        });
        getContentPane().add(btSom);
        btSom.setBounds(610, 50, 72, 40);

        imagemfundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/smashtheageslogo.png"))); // NOI18N
        getContentPane().add(imagemfundo);
        imagemfundo.setBounds(0, 0, 696, 570);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreditosActionPerformed
        new Creditos(this, true).setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btCreditosActionPerformed

    private void btsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsairActionPerformed
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btsairActionPerformed

    private void btjogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btjogarActionPerformed
        setVisible(false);
        new playGame(this).setVisible(true);

    }//GEN-LAST:event_btjogarActionPerformed

    private void btniveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btniveisActionPerformed
        //dispose();
        setVisible(false);
        new Niveis().setVisible(true);
    }//GEN-LAST:event_btniveisActionPerformed
private static final long serialVersionUID = 1L;
    private void btCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCarregarActionPerformed
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        if (chooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            try {
                MyArkanoid.ArkanoidGame game = new MyArkanoid.ArkanoidGame(true); // skipStart = true
                game.loadGame(chooser.getSelectedFile());
                javax.swing.JFrame frame = new javax.swing.JFrame("Arkanoid - Jogo Carregado");
                frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
                frame.add(game);
                frame.pack(); // Usa o tamanho preferido do ArkanoidGame
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setExtendedState(JFrame.NORMAL);
                frame.setAlwaysOnTop(true);
                frame.setAlwaysOnTop(false);
                frame.toFront();
                frame.requestFocus();
                // NÃO uses frame.setSize(600, 450);
                this.dispose();
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btCarregarActionPerformed

    private void btSomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSomActionPerformed
        alternarSom();
    }//GEN-LAST:event_btSomActionPerformed

    private void alternarSom() {
        MyArkanoid.ArkanoidGame.somAtivo = !MyArkanoid.ArkanoidGame.somAtivo;
        if (MyArkanoid.ArkanoidGame.somAtivo) {
            btSom.setText("🔊");
            MyArkanoid.ArkanoidGame.playSound("/resources/sound.wav");//mudar som
        } else {
            btSom.setText("🔇");
        }
    }

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
            java.util.logging.Logger.getLogger(Arkanoide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Arkanoide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Arkanoide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Arkanoide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Arkanoide().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCarregar;
    private javax.swing.JButton btCreditos;
    private javax.swing.JButton btSom;
    private javax.swing.JButton btjogar;
    private javax.swing.JButton btniveis;
    private javax.swing.JButton btsair;
    private javax.swing.JLabel imagemfundo;
    // End of variables declaration//GEN-END:variables
}
