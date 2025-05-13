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
//::                                                               (c)2025   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////

package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created on 06/05/2025, 17:50:01 
 * @author manso - computer
 */
public class Ball  extends GameObject{
   
    int vx = 2;
    int vy = 2;

    public Ball(Color myColor, int x, int y, int radius) {
    super(myColor, x, y, radius, radius);
    try {
        eyeImage = ImageIO.read(getClass().getResource("/resources/eye.png"));
    } catch (IOException | IllegalArgumentException e) {
        System.err.println("Erro ao carregar imagem do olho: " + e.getMessage());
    }
}
    
    
    
   public void paint(Graphics gr) {
    if (eyeImage != null) {
        gr.drawImage(eyeImage, x, y, 15, 15, null);
    } else {
        // Desenho de fallback caso a imagem falhe
        gr.setColor(myColor);
        gr.fillOval(x, y, width, height);
        gr.setColor(Color.BLACK);
        gr.drawOval(x, y, width, height);
    }
}
    
    public void move(){
        translate(vx, vy);
    }
    private Image eyeImage;
    
    public void move( Rectangle bounds){
        //mover
        move();
        //resaltar
        if( this.x < 0 || this.x + this.width > bounds.width){
            this.vx *= -1;
            move();
        }
        if( this.y < 0 || this.y + this.height > bounds.height){
            this.vy *= -1;
            move();
        }
        
    }
    
    



}
