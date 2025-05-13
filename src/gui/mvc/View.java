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
package gui.mvc;

import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Created on 06/05/2021, 16:06:26
 *
 * @author IPT - M@nso 2021
 */
public class View extends JFrame {

     JLabel lblImg;
     JLabel lblRadius;
     JLabel lblArea;
     JTextField txtRadius;
     JTextField txtArea;
     JButton btCalculate;

    public View() {
        createView();
        //link view to controler
        btCalculate.addActionListener(new gui.mvc.Controller(this));
    }

    public void createView() {
        this.setTitle("Cálculo da área do circulo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // create graphical objects
        lblImg = new JLabel(new ImageIcon(getClass().getResource("/resources/circleArea.png")));
        lblRadius = new JLabel("Raio :");
        lblArea = new JLabel("Área :");
        txtRadius = new JTextField("0.0");
        txtArea = new JTextField("0.0");
        btCalculate = new JButton("Calcular Área");
        // create display
        Container window = this.getContentPane();
        window.setLayout(new BoxLayout(window, BoxLayout.Y_AXIS));
        window.add(lblImg);
        window.add(lblRadius);
        window.add(txtRadius);
        window.add(btCalculate);
        window.add(lblArea);
        window.add(txtArea);
        //organize itens in container
        this.pack();
        //center the window
        this.setLocationRelativeTo(null);
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202105061606L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2021  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
