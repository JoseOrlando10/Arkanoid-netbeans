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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * Created on 06/05/2021, 16:08:46
 * @author IPT -  M@nso  2021 
 */

public class Controller implements ActionListener {

    View myView;
    Model myModel;

    public Controller(View myView) {
        this.myView = myView;
        this.myModel = new Model();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       //get radius from view
       double r = Double.parseDouble(myView.txtRadius.getText());
       //update model
       myModel.setRadius(r);
       //update view from model
       myView.txtArea.setText( myModel.getArea() +"");
    }



    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202105061608L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2021  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
