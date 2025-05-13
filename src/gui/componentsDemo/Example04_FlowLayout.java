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

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Created on 06/05/2021, 15:46:38
 *
 * @author IPT - M@nso 2021
 */
public class Example04_FlowLayout extends JFrame {

    public Example04_FlowLayout() {
        //build frame        
        super("FlowLayout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        initComponents();
    }

    public void initComponents() {
        //contentor
        JPanel c = new JPanel();
        //gestor de layout
        c.setLayout( new FlowLayout());
        //adicionar objetos ao contentor
        c.add(new JButton("1"));
        c.add(new JTextField(9));
        c.add(new JButton("dois"));
        c.add(new JButton("três"));
        // Adicionar o contentor ao Frame
        this.getContentPane().add(c);

    }
    
    public static void main(String[] args) {
        new Example04_FlowLayout().setVisible(true);
    }
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202105061546L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2021  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
