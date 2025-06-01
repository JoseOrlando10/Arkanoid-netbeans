package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */
public class Brick extends GameObject implements Serializable{
    protected Color baseColor;
 private transient Image image;
    
    
    boolean isVisible = true;

    public Brick(Color baseColor, int x, int y, int width, int height) {
        super(baseColor, x, y, width, height); // chama o construtor da superclasse GameObject
        this.baseColor = baseColor;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
    
    public void reload() {
        try {
            image = javax.imageio.ImageIO.read(getClass().getResource("/resources/pedras.png"));
        } catch (Exception e) {
            image = null;
        }
    }
    private static final long serialVersionUID = 1L;

}
