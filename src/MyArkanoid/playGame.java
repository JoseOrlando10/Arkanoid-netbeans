package MyArkanoid;

import javax.swing.JFrame;

/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */

public class playGame extends javax.swing.JFrame {
    
    private JFrame parent;

    /**
     * Creates new form playGame
     */
    public playGame(JFrame parent) {
        initComponents();
        setLocationRelativeTo(null);// coloca no centro do ecra
        this.parent = parent;
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        arkanoidGame1 = new MyArkanoid.ArkanoidGame();
        imgnivel1 = new javax.swing.JLabel();

        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(702, 565));
        setMinimumSize(new java.awt.Dimension(702, 565));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(702, 565));
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
        arkanoidGame1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        arkanoidGame1.setForeground(new java.awt.Color(255, 51, 51));
        arkanoidGame1.setMaximumSize(new java.awt.Dimension(600, 450));
        arkanoidGame1.setMinimumSize(new java.awt.Dimension(600, 450));
        arkanoidGame1.setLayout(new javax.swing.OverlayLayout(arkanoidGame1));
        getContentPane().add(arkanoidGame1);
        arkanoidGame1.setBounds(70, 50, 560, 470);

        imgnivel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/nivel1.png"))); // NOI18N
        imgnivel1.setMaximumSize(new java.awt.Dimension(702, 563));
        imgnivel1.setMinimumSize(new java.awt.Dimension(702, 700));
        imgnivel1.setName(""); // NOI18N
        imgnivel1.setPreferredSize(new java.awt.Dimension(702, 563));
        getContentPane().add(imgnivel1);
        imgnivel1.setBounds(0, 0, 700, 563);

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
    private javax.swing.JLabel imgnivel1;
    // End of variables declaration//GEN-END:variables
}
