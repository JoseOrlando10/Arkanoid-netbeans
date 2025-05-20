/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */
package MyArkanoid;

import java.awt.Color;
import java.awt.Rectangle;

public class GameObject extends Rectangle{
    protected Color myColor;

    public GameObject(Color myColor, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.myColor = myColor;
    }
    
    

    public Color getMyColor() {
        return myColor;
    }

    public void setMyColor(Color myColor) {
        this.myColor = myColor;
    }
   
}
