package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */
public class Brick extends GameObject{

    
    boolean isVisible = true;

    public Brick(Color myColor, int x, int y, int width, int height) {
        super(myColor,x, y, width, height);
    }
    
    
    public void paint(Graphics gr){
        if( !isVisible)
            return;
        
        gr.setColor(myColor);
        gr.fillRect(x, y, width, height);
        gr.setColor(Color.DARK_GRAY);
        gr.drawRect(x, y, width, height);
        
    }
    public void reset() 
    {
        this.isVisible = true;
    }




    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private static final long serialVersionUID = 202505061807L;
    //:::::::::::::::::::::::::::  Copyright(c) M@nso  2025  :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
}
