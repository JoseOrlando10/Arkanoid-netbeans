package arkanoide_exe;

import MyArkanoid.ArkanoidGame;
import Niveis.playGame;
import Niveis.playGame2;
import Niveis.playGame3;
import java.awt.Window;
import javax.swing.SwingUtilities;

/**
 * Janela de seleção de níveis do jogo Arkanoid.
 * Permite ao jogador escolher o nível para jogar, voltar ao menu ou sair do jogo atual.
 */
public class Niveis extends javax.swing.JFrame {

    private MenuPausa menuPausa;      // Referência ao menu de pausa (se vier de lá)
    private ArkanoidGame jogoAtual;   // Referência ao jogo atual (para fechar se necessário)

    // Construtor: recebe referências ao menu de pausa e ao jogo atual
    public Niveis(MenuPausa menuPausa, ArkanoidGame jogoAtual) {
        initComponents();
        setLocationRelativeTo(null); // Centraliza a janela
        this.menuPausa = menuPausa;
        this.jogoAtual = jogoAtual;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnivel2 = new javax.swing.JButton();
        btnivel1 = new javax.swing.JButton();
        voltar = new javax.swing.JButton();
        btnivel3 = new javax.swing.JButton();
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

        btnivel3.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        btnivel3.setText("Nivel 3");
        btnivel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnivel3ActionPerformed(evt);
            }
        });
        getContentPane().add(btnivel3);
        btnivel3.setBounds(200, 210, 90, 30);

        fundojogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/smashtheagesnosniveis.png"))); // NOI18N
        fundojogo.setMaximumSize(new java.awt.Dimension(300, 300));
        fundojogo.setMinimumSize(new java.awt.Dimension(300, 300));
        getContentPane().add(fundojogo);
        fundojogo.setBounds(0, 0, 490, 390);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Botão "Voltar": fecha a janela de níveis e volta ao menu principal
    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose(); // Fecha a janela de seleção de níveis
        new arkanoide_exe.Arkanoide().setVisible(true); // Abre o menu principal
    }

    // Botão "Nivel 1": inicia o nível 1
    private void btnivel1ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false); // Esconde a janela de níveis

        // Fecha o jogo atual, se existir
        if (jogoAtual != null) {
            Window janelaDoJogo = SwingUtilities.getWindowAncestor(jogoAtual);
            if (janelaDoJogo != null) {
                janelaDoJogo.dispose();
            }
        }

        // Abre o nível 1 (playGame)
        playGame jogo = new playGame(menuPausa, jogoAtual);
        jogo.setVisible(true);

        // Fecha o menu pausa também, se estiver aberto
        if (menuPausa != null) {
            menuPausa.dispose();
        }
    }

    // Botão "Nivel 2": inicia o nível 2
    private void btnivel2ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false); // Esconde a janela de níveis

        // Fecha o jogo atual, se existir
        if (jogoAtual != null) {
            Window janelaDoJogo = SwingUtilities.getWindowAncestor(jogoAtual);
            if (janelaDoJogo != null) {
                janelaDoJogo.dispose();
            }
        }

        // Abre o nível 2 (playGame2)
        playGame2 jogo2 = new playGame2();
        jogo2.setVisible(true);

        // Fecha o menu pausa também, se estiver aberto
        if (menuPausa != null) {
            menuPausa.dispose();
        }
    }

    // Botão "Nivel 3": inicia o nível 3
    private void btnivel3ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false); // Esconde a janela de níveis

        // Fecha o jogo atual, se existir
        if (jogoAtual != null) {
            Window janelaDoJogo = SwingUtilities.getWindowAncestor(jogoAtual);
            if (janelaDoJogo != null) {
                janelaDoJogo.dispose();
            }
        }

        // Abre o nível 3 (playGame3)
        playGame3 jogo2 = new playGame3();
        jogo2.setVisible(true);

        // Fecha o menu pausa também, se estiver aberto
        if (menuPausa != null) {
            menuPausa.dispose();
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
                new Niveis(null,null).setVisible(true);
            }
        });
    }
    // Declaração dos componentes da interface gráfica
    private javax.swing.JButton btnivel1;   // Botão para o nível 1
    private javax.swing.JButton btnivel2;   // Botão para o nível 2
    private javax.swing.JButton btnivel3;   // Botão para o nível 3
    private javax.swing.JLabel fundojogo;   // Imagem de fundo
    private javax.swing.JButton voltar;     // Botão para voltar ao menu principal

    private static final long serialVersionUID = 1L;
    // End of variables declaration                   
}
