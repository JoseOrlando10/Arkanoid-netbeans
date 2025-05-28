package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

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

    public void move(Rectangle bounds, ArkanoidGame game) throws ExceptionJogo {
        move();

        // Se a bola caiu (atingiu o fundo)
        if (y + height >= bounds.height) {
            game.decrementarVidas();
            if (game.getVidas() > 0) {
                game.resetBola();
            } else {
                throw new ExceptionJogo("Perdeu o jogo! \n\n\n\t Pontuação Final: " + ArkanoidGame.getScore());
            }
            return; // IMPORTANTE: não continue processando colisões!
        }

        // Colisão lateral
        if (x < 0 || x + width > bounds.width) {
            vx *= -1;
            move();
        }
        // Colisão superior
        if (y < 0) {
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
        vy = -6;
    }
}
