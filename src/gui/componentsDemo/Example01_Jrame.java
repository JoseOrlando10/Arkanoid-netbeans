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

import javax.swing.JFrame;



/**
 * Created on 06/05/2021, 15:34:16
 * @author IPT -  M@nso  2021 
 */

public class Example01_Jrame {  
    
    public static void main(String[] args) {
        //objeto da janela
        JFrame window = new JFrame("Hello World");
        // o que acontece quando fechar a janela
        // EXIT_ON_CLOSE fecha a aplicação
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //tamanho da janela
        window.setSize(1000, 500);
        //centrada no ecrã
        window.setLocationRelativeTo(null);
        //Mostrar a janela 
        window.setVisible(true);
    }




    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202105061534L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2021  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
