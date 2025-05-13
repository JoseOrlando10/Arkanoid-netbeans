//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
//::     I N S T I T U T O    P O L I T E C N I C O   D E   T O M A R        ::
//::     Escola Superior de Tecnologia de Tomar                              ::
//::     e-mail: manso@ipt.pt                                                ::
//::     url   : http://orion.ipt.pt/~manso                                  ::
//::                                                                         ::
//::     This software was build with the purpose of investigate and         ::
//::     learning.                                                           ::
//::                                                                         ::
//::                                                               (c)2021   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package gui.componentsDemo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created on 06/05/2021, 15:46:38
 *
 * @author IPT - M@nso 2021
 */
public class Example09_NullLayout extends JFrame {

    public Example09_NullLayout() {
        //build frame        
        super("Null Layout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        initComponents();
    }

    public void initComponents() {
        JPanel container = new JPanel();
        container.setLayout(null);
        JButton bt = new JButton("Botão");
        bt.setBounds(10, 10, 150, 50);
        container.add(bt);

        JLabel lbl = new JLabel("Texto");
        lbl.setBounds(0,125 , 150, 50);
        container.add(lbl);

        this.getContentPane().add(container);

    }

    public static void main(String[] args) {
        new Example09_NullLayout().setVisible(true);
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202105061546L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2021  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
