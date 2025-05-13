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

package gui.areaCirculo;

import java.util.Scanner;



/**
 * Created on 06/05/2021, 12:07:53
 * @author IPT -  M@nso  2021 
 */

public class AreaConsole {  
    
    public static void main(String[] args) {
        Scanner keyb = new Scanner(System.in);
        System.out.println("Programa para calcular a area do circulo");
        System.out.print("Raio = ");
        double raio = keyb.nextDouble();
        double area = Math.PI * Math.pow(raio, 2);
        System.out.println("Área do círculo = " + area);
    }

}
