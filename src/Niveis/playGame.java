package Niveis;

import MyArkanoid.ArkanoidGame;
import javax.swing.JFrame;

/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */

public class playGame extends javax.swing.JFrame {
        private int nivelAtual;
    private JFrame parent;
    
    /**
     * Creates new form playGame
     */
    public playGame(JFrame parent, ArkanoidGame jogoAtual) {
        initComponents();
        this.nivelAtual = 1;
        setLocationRelativeTo(null);// coloca no centro do ecra
        this.parent = parent;
        
    }
    

private static final long serialVersionUID = 1L;
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        arkanoidGame1 = new MyArkanoid.ArkanoidGame();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(680, 570));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        arkanoidGame1.setBackground(new java.awt.Color(0, 0, 0));
        arkanoidGame1.setForeground(new java.awt.Color(255, 51, 204));
        arkanoidGame1.setMaximumSize(new java.awt.Dimension(600, 450));
        arkanoidGame1.setMinimumSize(new java.awt.Dimension(600, 450));
        arkanoidGame1.setLayout(new javax.swing.OverlayLayout(arkanoidGame1));
        getContentPane().add(arkanoidGame1);
        arkanoidGame1.setBounds(0, 0, 680, 570);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    parent.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyArkanoid.ArkanoidGame arkanoidGame1;
    // End of variables declaration//GEN-END:variables
}
