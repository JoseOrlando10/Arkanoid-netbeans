package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import MyArkanoid.ImageBrick;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */


public class ArkanoidGame extends JComponent
        implements ActionListener, MouseMotionListener, MouseListener, KeyListener {

    Ball ball;
    ArrayList<Brick> bricks;
    Paddle pad;

    Timer gameTimer; // Timer principal do jogo (para mover a bola e checar colisões)
    Timer timeTimer; // Timer separado para contar o tempo
    int timeElapsed; // Variável para armazenar o tempo (em segundos)
    boolean ballReadyToMove = false;
    
    private static int score = 0; // Inicializa a pontuação 

    public ArkanoidGame () {
        start();
        addMouseListener(this);
        addKeyListener(this);//Adiciona esta linha para captar teclas
        setFocusable(true);//recebe dados do teclado
        
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

        //addMouseMotionListener(this);// deixa de funcionar o rato
    }

    public void start() {
        // Define o paddle mais embaixo
        pad = new Paddle(Color.RED, 200, 430, 50, 10);
        ball = new Ball(Color.yellow, pad.x + pad.width / 2 - 10, pad.y - 20, 5);
        
       
        ///Bricks, criação Aleatoria
        bricks = new ArrayList<>();
        Random random = new Random();
        int numBricks = 30; // Número de bricks aleatórios
        int larguraTela = Math.max(getWidth(), 800);// Garante que o tamanho da tela seja válido

        int[] linhasY = {25, 50, 75};//onde começam aparecer blocos

        for (int i = 0; i < numBricks; i++) {
            int x, y;
            int width = 30;
            int height = 10;
            boolean sobreposto;
            do {
                x = random.nextInt(Math.max(larguraTela - width, 1));
                y = linhasY[random.nextInt(linhasY.length)]; // Escolhe aleatoriamente uma das 3 linhas
                sobreposto = false;

                // Verifica se o novo brick está sobrepondo algum existente
                for (Brick brick : bricks) {
                    if (new Rectangle(x, y, width, height).intersects(brick.getBounds())) {
                        sobreposto = true;
                        break;
                    }
                }
            } while (sobreposto); // Se houver sobreposição, gera uma nova posição

            bricks.add(new ImageBrick("/resources/pedras.png", x, y, width, height));
        }
        //fim dos bricks
    }

    

    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        
        Graphics2D g2d = (Graphics2D) gr.create();

        // Pintar fundo com transparência
        float alpha = 0.8f; // Ajusta a opacidade do fundo aqui (0.0f = totalmente transparente, 1.0f = opaco)
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Restaurar opacidade total para objetos do jogo
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        // Desenhar objetos com opacidade total
        ball.paint(g2d);

        for (Brick brick : bricks) {
            if (brick.isVisible) {
                brick.paint(g2d);
            }
        }

        pad.paint(g2d);

        // Tempo com opacidade total
        g2d.setColor(Color.BLACK);
        g2d.drawString("Tempo: " + timeElapsed, getWidth() - 550, 460);

        //Pontuação
        gr.setColor(Color.BLACK);//Posição do Score aparece depois da ,
        gr.drawString("Pontuação: " + score, getWidth() - 480, 460);

        g2d.dispose();
    }

    public void actionPerformed(ActionEvent e) {
    if (!ballReadyToMove) {
        repaint(); // A bola ainda não se move
        return;
    }

    try {
        ball.move(this.getBounds());

        for (Brick brick : bricks) {
            if (brick.intersects(ball) && brick.isVisible) {
                brick.isVisible = false;
                score++; // Incrementa Pontuação

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



    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    pad.moveTo(e.getX(), getWidth());

    if (!ballReadyToMove) {
        ball.setPosition(pad.x + pad.width / 2 - 10, pad.y - 20);
    }
}



    public static int getScore() {
        return score;
    }

    public void mouseClicked(MouseEvent e) {
    if (!ballReadyToMove) {
        Random random = new Random();
        int direction = random.nextBoolean() ? 1 : -1; // Aleatório: esquerda (-1) ou direita (1)
        ball.launch(direction); // Lança a bola
        ballReadyToMove = true;
        gameTimer.start(); // Agora o jogo começa
        }
    }
    
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    //seta esquerda
    if (key == KeyEvent.VK_LEFT) {
        pad.moveLeft();
    } else if (key == KeyEvent.VK_RIGHT) {
        pad.moveRight(getWidth()); // Limite direito do jogo
    }
    // A bola acompanha o Paddle até ser lançad
    if (!ballReadyToMove) {
        ball.setPosition(pad.x + pad.width / 2 - 10, pad.y - 20);
    }
    //Lançar a bola ao pressionar Espaço
    if (key == KeyEvent.VK_SPACE && !ballReadyToMove) {
        Random random = new Random();
        int direction = random.nextBoolean() ? 1 : -1;
        ball.launch(direction); 
        ballReadyToMove = true;
        gameTimer.start(); // Agora o jogo começa
    }
    
    if (key == KeyEvent.VK_ESCAPE) {
    gameTimer.stop();
    MenuPausa menu = new MenuPausa(this);
    menu.setVisible(true);
}
    repaint();
    

    
}


public void keyReleased(KeyEvent e) {
    // opcional, se quiseres parar o paddle depois
}


public void keyTyped(KeyEvent e) {
    // não precisas de usar
}



public void continuarJogo() {
    gameTimer.start(); // Reinicia o jogo
    //MenuPausa.setVisible(false);
}


}