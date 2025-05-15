package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Created on 06/05/2025, 18:20:10 
 * @author manso
 */
public class Paddle extends GameObject {

    public Paddle(Color myColor, int x, int y, int width, int height) {
        super(myColor, x, y, 50, 10);
    }

    public void paint(Graphics gr) {
        gr.setColor(myColor);
        gr.fillRect(x, y, width, height);
        gr.setColor(Color.DARK_GRAY);
        gr.drawRect(x, y, width, height);
    }

    public void moveDown(int dy) {
        this.y += dy;
    }

    // ✅ NOVO MÉTODO COM LIMITES
    public void moveTo(int mouseX, int panelWidth) {
        int newX = mouseX - width / 2;

        if (newX < 0) newX = 0;
        if (newX + width > panelWidth) newX = panelWidth - width;

        this.x = newX;
    }

    public void collide(Ball b) {
        if (b.intersects(this)) {
            b.vy *= -1;
            b.move();
        }
    }
}
    
    

