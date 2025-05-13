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
package gui.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 * Created on 06/05/2021, 16:00:40
 *
 * @author IPT - M@nso 2021
 */
public class EventSayHello implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent me) {
        JOptionPane.showMessageDialog(null, "hello from "
                + me.getSource().getClass());
    }

    @Override
    public void mousePressed(MouseEvent me) {
        // needed by contract MouseListener
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        // needed by contract MouseListener
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        // needed by contract MouseListener
    }

    @Override
    public void mouseExited(MouseEvent me) {
        // needed by contract MouseListener
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202105061600L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2021  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
