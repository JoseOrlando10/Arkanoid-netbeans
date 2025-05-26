package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject {

    int vx = 2;
    int vy = 2;

    public Ball(int x, int y) {
        // Ajusta para raio maior (25 de diâmetro)
        super(Color.LIGHT_GRAY, x, y, 25, 25); 
    }

    public void paint(Graphics gr) {
        gr.setColor(myColor); // agora azul
        gr.fillOval(x, y, width, height);
        gr.setColor(Color.BLACK);
        gr.drawOval(x, y, width, height);
    }

    public void move() {
        translate(vx, vy);
    }

    public void move(java.awt.Rectangle bounds) throws ExceptionJogo {
        move();
        if (y + height >= bounds.height) {
            throw new ExceptionJogo("Perdeu o jogo! \n\n\n\t Pontuação Final: " + ArkanoidGame.getScore());
        }

        if (x < 0 || x + width > bounds.width) {
            vx *= -1;
            move();
        }
        if (y < 0 || y + height > bounds.height) {
            vy *= -1;
            move();
        }
    }

    public void reverseX() {
        this.vx = -vx;
    }

    public void reverseY() {
        this.vy = -vy;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void launch(int direction) {
        vx = 0;
        vy = -2;
    }
}
