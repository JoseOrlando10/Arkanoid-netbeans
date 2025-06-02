package arkanoide_exe;

import MyArkanoid.ArkanoidGame;
import MyArkanoid.Creditos;
import MyArkanoid.Niveis;
import MyArkanoid.playGame;
import java.awt.Image;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 *
 */
public class Arkanoide extends javax.swing.JFrame {

    private Clip musicaMenu;
    private FloatControl volumeControl;

    /**
     * Creates new form Arkanoide
     */
    public Arkanoide() {

        initComponents();
        setLocationRelativeTo(null);
        tocarMusicaMenu();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

    // Botão Créditos: abre a janela de créditos
    btCreditos = new javax.swing.JButton();
    // Botão Níveis: abre a seleção de níveis
    btniveis = new javax.swing.JButton();
    // Botão Sair: fecha o programa
    btsair = new javax.swing.JButton();
    // Botão Jogar: inicia o jogo principal
    btjogar = new javax.swing.JButton();
    // Botão Carregar: permite carregar um jogo salvo
    btCarregar = new javax.swing.JButton();
    // Botão Som: ativa/desativa o som do menu
    btSom = new javax.swing.JButton(MyArkanoid.ArkanoidGame.somAtivo ? "🔊" : "🔇");
    // Label da imagem de fundo/logo
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

    // Ação do botão Créditos
    private void btCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreditosActionPerformed
        // Abre a janela de créditos
        new Creditos(this, true).setVisible(true);
    }//GEN-LAST:event_btCreditosActionPerformed

    // Ação do botão Sair
    private void btsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsairActionPerformed
        // Fecha a janela principal (sai do jogo)
        dispose();
    }//GEN-LAST:event_btsairActionPerformed

    // Ação do botão Jogar
    private void btjogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btjogarActionPerformed
        // Esconde o menu e abre o jogo principal
        setVisible(false);
        new playGame(this, null).setVisible(true);
        reduzirVolume(-20.0f); // Reduz o volume da música do menu
    }//GEN-LAST:event_btjogarActionPerformed

    // Ação do botão Níveis
    private void btniveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btniveisActionPerformed
        // Esconde o menu e abre a seleção de níveis
        setVisible(false);
        new Niveis(null, null).setVisible(true);
    }//GEN-LAST:event_btniveisActionPerformed

    // Ação do botão Carregar
    private void btCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCarregarActionPerformed
        // Permite escolher e carregar um ficheiro de jogo salvo
        File pastaAtual = new File(".");
        File[] arquivosSalvos = pastaAtual.listFiles((dir, name) -> name.endsWith(".save"));

        if (arquivosSalvos == null || arquivosSalvos.length == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum jogo salvo encontrado.");
            return;
        }

        JFileChooser chooser = new JFileChooser(pastaAtual);
        chooser.setDialogTitle("Carregar jogo");

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File arquivo = chooser.getSelectedFile();
                MyArkanoid.ArkanoidGame novoJogo = new ArkanoidGame();
                novoJogo.loadGame(arquivo);

                JFrame frame = new JFrame("Arkanoid - Jogo Carregado");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(novoJogo);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                this.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btCarregarActionPerformed

    // Ação do botão Som
    private void btSomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSomActionPerformed
        // Ativa ou desativa o som do menu
        alternarSom();
    }//GEN-LAST:event_btSomActionPerformed
    private void tocarMusicaMenu() {
        try {
            File arquivoMusica = new File("src/resources/menusom.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivoMusica);
            musicaMenu = AudioSystem.getClip();
            musicaMenu.open(audioStream);

            // Controle de volume
            volumeControl = (FloatControl) musicaMenu.getControl(FloatControl.Type.MASTER_GAIN);

            // Só toca se o som estiver ativo
            if (MyArkanoid.ArkanoidGame.somAtivo) {
                musicaMenu.loop(Clip.LOOP_CONTINUOUSLY);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void alternarSom() {
        MyArkanoid.ArkanoidGame.somAtivo = !MyArkanoid.ArkanoidGame.somAtivo;
        if (MyArkanoid.ArkanoidGame.somAtivo) {
            btSom.setText("🔊");
            // Se a música do menu está carregada, volta a tocar
            if (musicaMenu != null) {
                musicaMenu.loop(Clip.LOOP_CONTINUOUSLY);
                volumeControl.setValue(0.0f); // Volume normal
            }
            // Também podes tocar o efeito de clique, se quiseres
            // MyArkanoid.ArkanoidGame.playSound("/resources/sound.wav");
        } else {
            btSom.setText("🔇");
            // Se a música do menu está a tocar, para
            if (musicaMenu != null) {
                musicaMenu.stop();
            }
        }
    }

    private void reduzirVolume(float decibels) {
        if (volumeControl != null) {
            volumeControl.setValue(decibels); // Ex: -10.0f para reduzir o volume
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel /
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        / If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         
For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html */
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
        }//</editor-fold>,

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Arkanoide().setVisible(true);
            }
        });
    }

    private static final long serialVersionUID = 1L;
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
