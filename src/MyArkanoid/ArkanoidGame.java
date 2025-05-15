package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;

/**
 * Created on 06/05/2025, 17:48:47
 *
 * @author manso
 */
public class ArkanoidGame extends JComponent
        implements ActionListener, MouseMotionListener {

    Ball ball;
    ArrayList<Brick> bricks;
    Paddle pad;
    
    Timer gameTimer; // Timer principal do jogo (para mover a bola e checar colisões)
    Timer timeTimer; // Timer separado para contar o tempo
    int timeElapsed; // Variável para armazenar o tempo (em segundos)

    public ArkanoidGame() {
        start();
        timeElapsed = 0; // Inicia o contador de tempo
        gameTimer = new Timer(10, this); // A cada 10 milissegundos
        gameTimer.start();
        
        // Timer para o contador de tempo (incrementa a cada 1000 milissegundos)
        timeTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeElapsed++; // Incrementa o tempo em 1 segundo
            }
        });
        timeTimer.start(); // Inicia o contador de tempo
        
        addMouseMotionListener(this);
    }

    public void start() {
        ball = new Ball(Color.yellow, 20, 20, 5);
        bricks = new ArrayList<>();
        bricks.add(new Brick(Color.GREEN, 10, 10, 30, 10));
        bricks.add(new Brick(Color.MAGENTA, 50, 10, 30, 10));
        bricks.add(new Brick(Color.BLUE, 90, 10, 30, 10));
        bricks.add(new Brick(Color.ORANGE, 130, 10, 30, 10));
        bricks.add(new Brick(Color.PINK, 170, 10, 30, 10));
        bricks.add(new Brick(Color.YELLOW, 210, 10, 30, 10));
        bricks.add(new Brick(Color.MAGENTA, 250, 10, 30, 10));
        bricks.add(new Brick(Color.GREEN, 290, 10, 30, 10));

        // Define o paddle mais embaixo
        pad = new Paddle(Color.RED, 200, 350, 50, 10);
    }

    public void gameOver() {
        // Para o timer do jogo quando o jogo termina
        gameTimer.stop();
        // Para o timer de tempo
        timeTimer.stop();
        // Exibe a mensagem de Game Over
        System.out.println("Game Over!");
    }

    protected void paintComponent(Graphics gr) {
    super.paintComponent(gr);

    Graphics2D g2d = (Graphics2D) gr.create();

    // 🟡 Passo 1: Pintar fundo com transparência
    float alpha = 0.8f; // Ajusta a opacidade do fundo aqui (0.0f = totalmente transparente, 1.0f = opaco)
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    g2d.setColor(Color.LIGHT_GRAY);
    g2d.fillRect(0, 0, getWidth(), getHeight());

    // 🔵 Passo 2: Restaurar opacidade total para objetos do jogo
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    
    // Desenhar objetos com opacidade total
    ball.paint(g2d);

    for (Brick brick : bricks) {
        if (brick.isVisible) {
            brick.paint(g2d);
        }
    }

    pad.paint(g2d);

    // 🟢 Passo 3: Tempo com opacidade total
    g2d.setColor(Color.BLACK);
    g2d.drawString("Tempo: " + timeElapsed, getWidth() - 315, 369);

    g2d.dispose();
}

    

    
@Override
public void actionPerformed(ActionEvent e) {
    try {
        ball.move(this.getBounds());

        for (Brick brick : bricks) {
            if (brick.intersects(ball) && brick.isVisible) {
                brick.isVisible = false;

                // Ajusta a direção corretamente
                ball.reverseX(); // Inverte direção horizontal
                ball.reverseY(); // Inverte direção vertical

                break; // Evita múltiplas colisões simultâneas
            }
        }

        pad.collide(ball);
        repaint();
    } catch (ExceptionJogo ex) {
        ex.show();
        gameTimer.stop();
    }
}

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        pad.moveTo(e.getX(), getWidth());
    }
    
}