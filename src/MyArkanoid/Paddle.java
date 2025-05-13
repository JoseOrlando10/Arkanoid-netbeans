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
//::     learning.                                                           ::***
//::                                                                         ::
//::                                                               (c)2025   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////

package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Created on 06/05/2025, 18:20:10 
 * @author manso - computer
 */
public class Paddle extends GameObject{


    public Paddle(Color myColor, int x, int y, int width, int height) {
        super(myColor, x, y, 50, 10);
    }
    
     public void paint(Graphics gr){
        gr.setColor(myColor);
        gr.fillRect(x, y, width, height);
        gr.setColor(Color.DARK_GRAY);
        gr.drawRect(x, y, width, height);
        
    }
     public void moveDown(int dy) {
    this.y += dy;
}
     public void moveTo(int px){
         this.x = px;
     }
     
     public void collide(Ball b){
         if( b.intersects(this)){
             b.vy *=-1;
             b.move();
         }
     }
    
    
}
