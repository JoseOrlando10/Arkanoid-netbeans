package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.RadialGradientPaint;
import java.awt.BasicStroke;
import java.awt.Color;

public class Ball extends GameObject implements Serializable {

    private transient Image image;
    int vx = 2;
    int vy = 2;

    public Ball(int x, int y) {
        // Ajusta para raio maior (25 de diâmetro)
        super(Color.LIGHT_GRAY, x, y, 25, 25);
    }

    public void paint(Graphics gr) {
    Graphics2D g2d = (Graphics2D) gr.create();

    // Ativa antialias para suavizar bordas
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    int diameter = width;  // usa o width já definido (25 no seu caso)

    // Degradê radial: luz no topo esquerdo, sombra no canto oposto
    RadialGradientPaint gradient = new RadialGradientPaint(
        x + diameter / 3f, y + diameter / 3f, diameter,
        new float[] {0f, 1f},
        new Color[] {Color.WHITE, new Color(200, 200, 200)}
    );

    g2d.setPaint(gradient);
    g2d.fillOval(x, y, diameter, diameter);

    // Borda mais escura e suave
    g2d.setColor(new Color(100, 100, 100));
    g2d.setStroke(new BasicStroke(2));
    g2d.drawOval(x, y, diameter, diameter);

    // Pequeno reflexo branco para dar brilho
    g2d.setColor(new Color(255, 255, 255, 150));
    g2d.fillOval(x + diameter / 4, y + diameter / 4, diameter / 5, diameter / 5);

    g2d.dispose();
}

    public void move() {
        translate(vx, vy);
    }

    public void move(Rectangle bounds, ArkanoidGame game) throws ExceptionJogo {

        move();
        if (y + height >= bounds.height) {
            game.decrementarVidas();
            if (game.getVidas() > 0) {
                game.resetBola(); // Permite novo disparo
            } else {
                throw new ExceptionJogo("Perdeu o jogo! \n\n\n\t Pontuação Final: " + game.getScore());
            }
            return;
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

    public void reload() {
        try {
            image = javax.imageio.ImageIO.read(getClass().getResource("/resources/ball.png"));
        } catch (Exception e) {
            image = null;
        }
    }
    private static final long serialVersionUID = 1L;

}
