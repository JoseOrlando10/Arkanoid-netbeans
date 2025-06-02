package MyArkanoid;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 *
 */
public class MenuPausa extends javax.swing.JFrame {

    private javax.swing.JButton btSom;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 330));
        setMinimumSize(new java.awt.Dimension(400, 330));
        getContentPane().setLayout(null);

        btConti.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btConti.setText("Continuar");
        btConti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btContiActionPerformed(evt);
            }
        });
        getContentPane().add(btConti);
        btConti.setBounds(140, 50, 100, 30);

        btSair.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btSair.setText("Abandonar Jogo");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });
        getContentPane().add(btSair);
        btSair.setBounds(120, 240, 145, 30);
        btSair.getAccessibleContext().setAccessibleName("esc_sair");

        btNiveis.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btNiveis.setText("Niveis");
        btNiveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNiveisActionPerformed(evt);
            }
        });
        getContentPane().add(btNiveis);
        btNiveis.setBounds(140, 180, 100, 30);
        btNiveis.getAccessibleContext().setAccessibleName("esc_niveis");

        btRestart.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btRestart.setText("Reiniciar");
        btRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRestartActionPerformed(evt);
            }
        });
        getContentPane().add(btRestart);
        btRestart.setBounds(140, 110, 100, 30);

        btSave.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btSave.setText("Save");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btSave);
        btSave.setBounds(290, 240, 90, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/smashtheagesmenupausa.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 400, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNiveisActionPerformed
        this.setVisible(false);
        Niveis niveis = new Niveis(this, jogo); // passa a referência do MenuPausa!
        niveis.setVisible(true);

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
            jogo.reiniciarNivelAtual(); // Reinicia o nível atual
        }
        this.dispose(); // Fecha o menu de pausa

    }//GEN-LAST:event_btRestartActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed

        jogo.pararJogo(); // Pausa o jogo

        JFileChooser fc = new JFileChooser(".");
        int result = fc.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile(); // CORRIGIDO aqui

            // Adiciona extensão .save se não tiver
            if (!arquivo.getName().toLowerCase().endsWith(".save")) {
                arquivo = new File(arquivo.getAbsolutePath() + ".save");
            }

            try {
                jogo.saveGame(arquivo); // método deve aceitar File
                JOptionPane.showMessageDialog(this, "Jogo salvo com sucesso!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
            }

        }

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
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
