package arkanoide_exe;

import MyArkanoid.ArkanoidGame;
import arkanoide_exe.Niveis;
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

    // Referência ao jogo atual para poder controlar o estado do jogo a partir do menu de pausa
    private ArkanoidGame jogo;
    private javax.swing.JButton btSom;
    /**
     * Construtor do menu de pausa.
     * Recebe a instância do jogo para poder continuar, reiniciar, salvar, etc.
     */
    public MenuPausa(ArkanoidGame jogo) {
        initComponents();
        setLocationRelativeTo(null); // Centraliza a janela
        this.jogo = jogo; // Guarda a referência ao jogo
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
        btSair.setBounds(110, 240, 150, 30);
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
        btSave.setText("Guardar");
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

    // Botão "Continuar": fecha o menu de pausa e retoma o jogo
    private void btContiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btContiActionPerformed
        if (jogo != null) {
            jogo.continuarJogo(); // Retoma o timer e a ação do jogo
        }
        this.dispose(); // Fecha o menu de pausa
    }//GEN-LAST:event_btContiActionPerformed

    // Botão "Abandonar Jogo": fecha o jogo atual e volta ao menu inicial
    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        if (jogo != null) {
            java.awt.Window janelaJogo = javax.swing.SwingUtilities.getWindowAncestor(jogo);
            if (janelaJogo != null) {
                janelaJogo.dispose(); // Fecha a janela do jogo
            }
        }
        this.dispose(); // Fecha o menu de pausa
        new arkanoide_exe.Arkanoide().setVisible(true); // Abre o menu inicial
    }//GEN-LAST:event_btSairActionPerformed

    // Botão "Niveis": abre a janela de seleção de níveis
    private void btNiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNiveisActionPerformed
        this.setVisible(false); // Esconde o menu de pausa
        Niveis niveis = new Niveis(this, jogo); // Cria janela de níveis, passando referência ao menu e ao jogo
        niveis.setVisible(true); // Mostra a janela de níveis
    }//GEN-LAST:event_btNiveisActionPerformed

    // Botão "Reiniciar": reinicia o nível atual do jogo
    private void btRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRestartActionPerformed
        if (jogo != null) {
            jogo.reiniciarNivelAtual(); // Reinicia o nível (bricks, bola, paddle, vidas)
        }
        this.dispose(); // Fecha o menu de pausa
    }//GEN-LAST:event_btRestartActionPerformed

    // Botão "Save": permite guardar o estado atual do jogo num ficheiro
    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        jogo.pararJogo(); // Pausa o jogo antes de salvar

        JFileChooser fc = new JFileChooser(".");
        int result = fc.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile();

            // Garante que o ficheiro tem extensão .save
            if (!arquivo.getName().toLowerCase().endsWith(".save")) {
                arquivo = new File(arquivo.getAbsolutePath() + ".save");
            }

            try {
                jogo.saveGame(arquivo); // Salva o estado do jogo
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
