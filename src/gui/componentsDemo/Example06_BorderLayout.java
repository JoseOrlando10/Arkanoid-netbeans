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

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created on 06/05/2021, 15:46:38
 *
 * @author IPT - M@nso 2021
 */
public class Example06_BorderLayout extends JFrame {

    public Example06_BorderLayout() {
        //build frame        
        super("BorderLayout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        initComponents();
    }

    public void initComponents() {
        JPanel c = new JPanel(new BorderLayout());
        JButton b1 = new JButton("North");
        c.add(b1, BorderLayout.NORTH);
        JButton b2 = new JButton("EAST");
        c.add(b2, BorderLayout.EAST);
        JButton b3 = new JButton("SOUTH");
        c.add(b3, BorderLayout.SOUTH);
        JButton b4 = new JButton("WEST");
        c.add(b4, BorderLayout.WEST);
        JButton b5 = new JButton("CENTER");
        c.add(b5, BorderLayout.CENTER);

        this.getContentPane().add(c);

    }

    public static void main(String[] args) {
        new Example06_BorderLayout().setVisible(true);
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202105061546L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2021  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
