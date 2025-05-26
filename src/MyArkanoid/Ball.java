package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Ball extends GameObject {

    int vx = 2;
    int vy = 2;

    public Ball(Color myColor, int x, int y, int radius) {
        super(myColor, x, y, radius, radius);
        try {
            bolaImage = ImageIO.read(getClass().getResource("/resources/machado.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Erro ao carregar imagem da bola: " + e.getMessage());
        }
    }

    public void paint(Graphics gr) {
        if (bolaImage != null) {
            gr.drawImage(bolaImage, x, y, 50, 50, null);
        } else {
            // Desenho de fallback caso a imagem falhe
            gr.setColor(myColor);
            gr.fillOval(x, y, width, height);
            gr.setColor(Color.BLACK);
            gr.drawOval(x, y, width, height);
        }
    }

    public void move() {
        translate(vx, vy);
    }
    private Image bolaImage;

    public void move(Rectangle bounds) throws ExceptionJogo {
        //mover
        move();
        if (y + height >= bounds.height) {
            throw new ExceptionJogo("Perdeu o jogo! \n\n\n\t Pontuação Final: "+ ArkanoidGame.getScore());
        }

        ////bateu no fundo
        if (this.x < 0 || this.x + this.width > bounds.width) {
            this.vx *= -1;
            move();
        }
        if (this.y < 0 || this.y + this.height > bounds.height) {
            this.vy *= -1;
            move();
        }

    }

    public void reverseX() {
        this.vx = -vx; // Inverte a direção horizontal
    }

    public void reverseY() {
        this.vy = -vy; // Inverte a direção vertical
    }

    public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
}

    
    public void launch(int direction)
    {
        vx=0;
        vy=-2;
    }
}
