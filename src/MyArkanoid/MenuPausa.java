package MyArkanoid;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 *
 */
public class MenuPausa extends javax.swing.JFrame {

    private ArkanoidGame jogo;

    static void setVisible() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Creates new form MenuPausa
     */
    public MenuPausa(ArkanoidGame jogo) {
        initComponents();
        setLocationRelativeTo(null);
        this.jogo = jogo; // Guardamos a instância atual do jogo
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btConti = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        btNiveis = new javax.swing.JButton();
        btRestart = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btSom = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 330));
        setMinimumSize(new java.awt.Dimension(400, 330));
        getContentPane().setLayout(null);

        btConti.setText("Continuar");
        btConti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btContiActionPerformed(evt);
            }
        });
        getContentPane().add(btConti);
        btConti.setBounds(150, 50, 90, 30);

        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });
        getContentPane().add(btSair);
        btSair.setBounds(150, 240, 90, 30);
        btSair.getAccessibleContext().setAccessibleName("esc_sair");

        btNiveis.setText("Niveis");
        btNiveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNiveisActionPerformed(evt);
            }
        });
        getContentPane().add(btNiveis);
        btNiveis.setBounds(150, 120, 90, 30);
        btNiveis.getAccessibleContext().setAccessibleName("esc_niveis");

        btRestart.setText("Reiniciar");
        btRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRestartActionPerformed(evt);
            }
        });
        getContentPane().add(btRestart);
        btRestart.setBounds(150, 180, 90, 30);

        btSave.setText("Save");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btSave);
        btSave.setBounds(300, 130, 72, 23);

        btSom.setText("🔊");
        btSom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alternarSom();
            }
        });
        getContentPane().add(btSom);
        btSom.setBounds(300, 180, 72, 23);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/smashtheagesmenupausa.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 400, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNiveisActionPerformed
        setVisible(false);
        new Niveis().setVisible(true);

        this.dispose();// TODO add your handling code here:

    }//GEN-LAST:event_btNiveisActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed

        if (jogo != null) {
            java.awt.Window janelaJogo = javax.swing.SwingUtilities.getWindowAncestor(jogo);
            if (janelaJogo != null) {
                janelaJogo.dispose();
            }
        }
        // Fecha o menu de pausa
        this.dispose();
        // Abre o menu inicial
        new arkanoide_exe.Arkanoide().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btSairActionPerformed

    private void btContiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btContiActionPerformed
        if (jogo != null) {
            jogo.continuarJogo(); // Recomeça o jogo
        }
        this.dispose(); // Fecha a janela do menu
        // TODO add your handling code here:
    }//GEN-LAST:event_btContiActionPerformed

    private void btRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRestartActionPerformed
        if (jogo != null) {
            jogo.resetJogo(); // Usa o método resetJogo() da classe ArkanoidGame
        }

        this.dispose(); // Fecha o menu de pausa

        new ArkanoidGame().setVisible(true);

    }//GEN-LAST:event_btRestartActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        if (chooser.showSaveDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            try {

                jogo.saveGame(chooser.getSelectedFile());
                javax.swing.JOptionPane.showMessageDialog(this, "Jogo guardado com sucesso!");
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Erro ao guardar: " + ex.getMessage());
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_btSaveActionPerformed

    private void alternarSom() {
        ArkanoidGame.somAtivo = !ArkanoidGame.somAtivo;
        if (ArkanoidGame.somAtivo) {
            btSom.setText("🔊");
            ArkanoidGame.playSound("/resources/sound.wav");
        } else {
            btSom.setText("🔇");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConti;
    private javax.swing.JButton btNiveis;
    private javax.swing.JButton btRestart;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSave;
    private javax.swing.JButton btSom;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
