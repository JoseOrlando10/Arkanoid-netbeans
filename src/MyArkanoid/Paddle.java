package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Paddle extends GameObject {

    private Image image;
    private final int speed = 17; // Velocidade de movimento com as setas

    public Paddle(Color myColor, int x, int y, int width, int height) {
        super(myColor, x, y, width, height);

        try {
            image = ImageIO.read(getClass().getResource("/resources/tronco.jpg")); // Caminho relativo ao src
        } catch (IOException | IllegalArgumentException ex) {
            System.err.println("Erro ao carregar imagem do paddle: " + ex.getMessage());
        }
    }
// adeus
    public void paint(Graphics gr) {
        if (image != null) {
            gr.drawImage(image, x, y, width, height, null);
        } else {
            // Fallback caso a imagem não carregue
            gr.setColor(myColor);
            gr.fillRect(x, y, width, height);
            gr.setColor(Color.DARK_GRAY);
            gr.drawRect(x, y, width, height);
        }
    }

    public void moveTo(int mouseX, int panelWidth) {
        int newX = mouseX - width / 2;
        if (newX < 0) newX = 0;
        if (newX + width > panelWidth) newX = panelWidth - width;
        this.x = newX;
    }

    public void moveLeft() {
        x -= speed;
        if (x < 0) x = 0;
    }

    public void moveRight(int panelWidth) {
        x += speed;
        if (x + width > panelWidth) {
            x = panelWidth - width;
        }
    }

    public void collide(Ball b) {
        if (b.intersects(this)) {
            b.vy *= -1;
            b.move();
        }
    }
    
}