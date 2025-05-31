package MyArkanoid;

/**
 *
 * @author pedro
 */
public class playGame2 extends javax.swing.JFrame {
    
    /**
     * Creates new form playGame2
     */
    public playGame2() {  
        initComponents();
        setLocationRelativeTo(null);// coloca no centro do ecra
        // Ajusta a altura do paddle (por exemplo, para y=450)
    arkanoidGame1.setPaddleY(480);
     arkanoidGame1.start2();  // chamo o nivel 2
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        arkanoidGame1 = new MyArkanoid.ArkanoidGame();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 570));
        setMinimumSize(new java.awt.Dimension(680, 570));
        setName("nivel2"); // NOI18N
        setPreferredSize(new java.awt.Dimension(695, 570));
        setResizable(false);
        getContentPane().setLayout(null);

        arkanoidGame1.setBackground(new java.awt.Color(0, 0, 0));
        arkanoidGame1.setForeground(new java.awt.Color(255, 51, 51));
        arkanoidGame1.setMaximumSize(new java.awt.Dimension(600, 450));
        arkanoidGame1.setMinimumSize(new java.awt.Dimension(600, 450));
        arkanoidGame1.setLayout(new javax.swing.OverlayLayout(arkanoidGame1));
        getContentPane().add(arkanoidGame1);
        arkanoidGame1.setBounds(0, 0, 680, 570);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(playGame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(playGame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(playGame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(playGame2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new playGame2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyArkanoid.ArkanoidGame arkanoidGame1;
    // End of variables declaration//GEN-END:variables
}
