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

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Created on 06/05/2021, 15:46:38
 *
 * @author IPT - M@nso 2021
 */
public class Example05_GridLayout extends JFrame {

    public Example05_GridLayout() {
        //build frame        
        super("GridLayout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        initComponents();
    }

    public void initComponents() {
        JPanel c = new JPanel();
        //gestor de layout 2x2 com 10 pixeis entre celulas
        c.setLayout(new GridLayout(2, 2, 10,10));
        
        c.add(new JButton("um"));
        c.add(new JTextField("text"));
        c.add(new JButton("dois"));
        c.add(new JButton("três"));

        this.getContentPane().add(c);

    }

    public static void main(String[] args) {
        new Example05_GridLayout().setVisible(true);
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202105061546L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2021  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
