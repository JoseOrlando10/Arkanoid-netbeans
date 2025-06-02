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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe da bola do jogo, com suporte a efeitos visuais (gradiente).
 */
public class Ball extends GameObject implements Serializable {

    private transient Image image;
    int vx = 2; // Velocidade horizontal
    int vy = 2; // Velocidade vertical

    // Parâmetros do efeito visual (gradiente)
    private float gradCenterFactor = 1f / 3f; // Centro do gradiente (proporção)
    private float gradRadiusFactor = 1f;      // Raio do gradiente (proporção)

    // Construtor padrão (cor e posição)
    public Ball(int x, int y) {
        super(Color.LIGHT_GRAY, x, y, 25, 25);
    }
    
    // Construtor com cor
    public Ball(Color color, int x, int y) {
        super(color, x, y, 25, 25);
    }

    // Construtor com cor e efeito visual
    public Ball(Color color, int x, int y, float gradCenterFactor, float gradRadiusFactor) {
        super(color, x, y, 25, 25);
        this.gradCenterFactor = gradCenterFactor;
        this.gradRadiusFactor = gradRadiusFactor;
    }

    // Desenha a bola com gradiente e brilho
    public void paint(Graphics gr) {
        Graphics2D g2d = (Graphics2D) gr.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int diameter = width;
        RadialGradientPaint gradient = new RadialGradientPaint(
            x + diameter * gradCenterFactor, 
            y + diameter * gradCenterFactor, 
            diameter * gradRadiusFactor,
            new float[] {0f, 1f},
            new Color[] {Color.WHITE, myColor}
        );
        g2d.setPaint(gradient);
        g2d.fillOval(x, y, diameter, diameter);
        g2d.setColor(new Color(100, 100, 100));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x, y, diameter, diameter);
        g2d.setColor(new Color(255, 255, 255, 150));
        g2d.fillOval(x + diameter / 4, y + diameter / 4, diameter / 5, diameter / 5);
        g2d.dispose();
    }

    // Getters para guardar/restaurar o efeito visual da bola
    public float getGradCenterFactor() { return gradCenterFactor; }
    public float getGradRadiusFactor() { return gradRadiusFactor; }

    // Movimento da bola (apenas incrementa x/y)
    public void move() {
        translate(vx, vy);
    }

    // Movimento da bola com colisões e lógica de fim de jogo
    public void move(Rectangle bounds, ArkanoidGame game) throws ExceptionJogo {
        move();
        if (y + height >= bounds.height) {
            game.decrementarVidas();
            if (game.getVidas() > 0) {
                game.resetBola();
            } else {
                throw new ExceptionJogo("Perdeu o jogo! \n\n\n\t Pontuação Final: " + game.getScore());
            }
            return;
        }
        if (x < 0 || x + width > bounds.width) {
            vx *= -1;
            move();
        }
        if (y < 0) {
            vy *= -1;
            move();
        }
    }

    // Inverte direção horizontal
    public void reverseX() { this.vx = -vx; }
    // Inverte direção vertical
    public void reverseY() { this.vy = -vy; }
    // Define posição da bola
    public void setPosition(int x, int y) { this.x = x; this.y = y; }
    // Lança a bola (define velocidade inicial)
    public void launch(int direction) { vx = 0; vy = -6; }

    // Serialização personalizada (caso precises de guardar mais campos)
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.reload();
    }
    public void reload() {}
    private static final long serialVersionUID = 1L;
}
