/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */
package MyArkanoid;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;

public class GameObject extends Rectangle implements Serializable{
    protected Color myColor;
 private static final long serialVersionUID = 1L;
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
