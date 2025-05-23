package MyArkanoid;

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btConti.setText("Continuar");
        btConti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btContiActionPerformed(evt);
            }
        });

        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        btNiveis.setText("Niveis");
        btNiveis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNiveisActionPerformed(evt);
            }
        });

        btRestart.setText("ReStart");
        btRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRestartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btRestart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btConti, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(btNiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btConti, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btNiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btSair.getAccessibleContext().setAccessibleName("esc_sair");
        btNiveis.getAccessibleContext().setAccessibleName("esc_niveis");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNiveisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNiveisActionPerformed
        setVisible(false);
        new Niveis().setVisible(true);

        this.dispose();// TODO add your handling code here:

    }//GEN-LAST:event_btNiveisActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        System.exit(0);// TODO add your handling code here:
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConti;
    private javax.swing.JButton btNiveis;
    private javax.swing.JButton btRestart;
    private javax.swing.JButton btSair;
    // End of variables declaration//GEN-END:variables
}
