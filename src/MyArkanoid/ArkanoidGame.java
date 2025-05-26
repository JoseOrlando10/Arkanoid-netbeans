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
        implements ActionListener, MouseMotionListener, KeyListener {

    Ball ball;
    ArrayList<Brick> bricks;
    Paddle pad;

    Timer gameTimer; // Timer principal do jogo (para mover a bola e checar colisões)
    Timer timeTimer; // Timer separado para contar o tempo
    int timeElapsed; // Variável para armazenar o tempo (em segundos)
    boolean ballReadyToMove = false;

    private static int score = 0; // Inicializa a pontuação

 
    private Timer fallTimer;
    private boolean isGameOver = false;

    public ArkanoidGame() {
        start();
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
        ball = new Ball(Color.yellow, pad.x + pad.width / 2 - 10, pad.y - 45, 5);

        ///Bricks
        bricks = new ArrayList<>();
        int larguraTela = Math.max(getWidth(), 560); // Largura da tela
        int alturaBrick = 20;
        int larguraBrick = 50;
        int espacamento = 5; // Espaço entre os bricks
        int bricksPorLinha = 1; // Número de bricks por linha

        int[] linhasY = {25}; // Posições Y das linhas

        for (int y : linhasY) {
            // Calcula a largura total ocupada pelos bricks e espaços na linha
            int larguraTotalLinha = (larguraBrick * bricksPorLinha) + (espacamento * (bricksPorLinha - 1));

            // Centraliza a linha horizontalmente
            int startX = (larguraTela - larguraTotalLinha) / 2;

            // Cria cada brick na linha
            for (int i = 0; i < bricksPorLinha; i++) {
                int x = startX + (i * (larguraBrick + espacamento));
                bricks.add(new ImageBrick("/resources/pedras.png", x, y, larguraBrick, alturaBrick));
            }
        }//fim dos bricks

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

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {

    }

    /*private class FallingBrick {

        float x, y;
        float yVel = 5; // Velocidade fixa para todos
        int width, height;
        Color color = Color.DARK_GRAY; // Cor uniforme

        public FallingBrick(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void update() {
            y += yVel; // Movimento apenas vertical
        }

        public void paint(Graphics2D g2d) {
            g2d.setColor(color);
            g2d.fillRect((int) x, (int) y, width, height);
        }
    }

    private void createFallingBricks() {
        for (Brick brick : bricks) {
            if (brick.isVisible) {
                fallingBricks.add(new FallingBrick(
                        brick.x,
                        brick.y,
                        brick.width,
                        brick.height
                ));
            }
        }
        fallTimer.start();
    }*/

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //seta esquerda
        if (key == KeyEvent.VK_LEFT) {
            pad.moveLeft();
        } else if (key == KeyEvent.VK_RIGHT) {
            pad.moveRight(getWidth()); // Limite direito do jogo
        }
        // A bola acompanha o Paddle até ser lançada
        if (!ballReadyToMove) {
            ball.setPosition(pad.x + pad.width / 2 - 10, pad.y - 45);
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

    public void pararJogo() {
        gameTimer.stop();
        timeTimer.stop();
    }

    public void resetJogo() {
        pararJogo();
        score = 0;
        timeElapsed = 0;

        // Limpar todos os elementos antes de reiniciar
        bricks.clear();

        ballReadyToMove = false;
        ball.setPosition(pad.x + pad.width / 2 - 10, pad.y - 45);

        start(); // Recria os elementos
        repaint();

    }
    
    public void iniciarProximoNivel() {
    // Limpa os elementos do nível atual
    bricks.clear();
    score = 0;
    timeElapsed = 0;

    // Carrega novos elementos para o próximo nível
    start();

    JOptionPane.showMessageDialog(null, "Bem-vindo ao próximo nível!", "Novo Nível", JOptionPane.INFORMATION_MESSAGE);
}
    
    public void salvarProgresso() {
    // Simulação de um sistema de salvamento simples
    String progresso = "Tempo: " + timeElapsed + "\nPontuação: " + score;
    
    JOptionPane.showMessageDialog(null, "Progresso salvo!\n" + progresso, "Salvamento", JOptionPane.INFORMATION_MESSAGE);
    
    // Aqui, idealmente, você salvaria essas informações em um arquivo ou banco de dados
}

    


}
