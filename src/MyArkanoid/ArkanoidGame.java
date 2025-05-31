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
import javax.management.Query;
import MyArkanoid.ExceptionJogo;
import java.awt.Image;
import java.io.*;
import javax.sound.sampled.*;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 *
 */
public class ArkanoidGame extends JComponent
        implements ActionListener, MouseMotionListener, KeyListener, MouseListener {

    Ball ball;
    ArrayList<Brick> bricks;
    Paddle pad;

    Timer gameTimer; // Timer principal do jogo (para mover a bola e checar colisões)
    Timer timeTimer; // Timer separado para contar o tempo
    int timeElapsed; // Variável para armazenar o tempo (em segundos)
    boolean ballReadyToMove = false;

    public static boolean somAtivo = true;

    private int score = 0; // Inicializa a pontuação
    private MenuPausa menuPausa;
    //private int vidas = 2; // Número máximo de vidas
    private Timer fallTimer;
    private boolean isGameOver = false;

    private String caminhoFundo = "/resources/fundo.png";
    private transient Image imageFundo;

    public ArkanoidGame() {
        this(false);
        setPreferredSize(new java.awt.Dimension(700, 570)); // ou o tamanho do teu jogo
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

        addMouseMotionListener(this);// deixa de funcionar o rato
        addMouseListener(this);
        reloadFundo();
    }

    public ArkanoidGame(boolean skipStart) {
        setPreferredSize(new java.awt.Dimension(700, 570));
        if (!skipStart) {
            start();
        }
        addKeyListener(this);
        setFocusable(true);
        timeElapsed = 0;
        gameTimer = new Timer(10, this);
        timeTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeElapsed++;
            }
        });
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    public void mouseClicked(MouseEvent e) {
        if (!ballReadyToMove) {
            Random random = new Random();
            int direction = random.nextBoolean() ? 1 : -1;
            ball.launch(direction);
            ballReadyToMove = true;
            gameTimer.start();
            timeTimer.start();
        }
    }
   public class EstilosBricks extends Brick {

    public EstilosBricks(Color baseColor, int x, int y, int width, int height) {
        super(baseColor, x, y, width, height);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Antialias para cantos suaves
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Gradiente vertical
        GradientPaint gp = new GradientPaint(
            x, y, baseColor.brighter(),
            x, y + height, baseColor.darker());

        g2d.setPaint(gp);
        RoundRectangle2D.Double rect = new RoundRectangle2D.Double(x, y, width, height, 15, 15);
        g2d.fill(rect);

        // Borda brilhante
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(baseColor.brighter().brighter());
        g2d.draw(rect);

        // Highlight (reflexo)
        GradientPaint highlight = new GradientPaint(
            x, y, new Color(255, 255, 255, 150),
            x, y + height / 2, new Color(255, 255, 255, 0));
        g2d.setPaint(highlight);
        g2d.fill(new RoundRectangle2D.Double(x + 2, y + 2, width - 4, height / 2, 15, 15));

        g2d.dispose();
    }
}
    public void start() {
        // Define o paddle mais embaixo
        pad = new Paddle(Color.RED, 200, 480, 50, 10);
        ball = new Ball(pad.x + pad.width / 2 - 20, pad.y - 40);

        ///Bricks
        bricks = new ArrayList<>();
        int larguraTela = Math.max(getWidth(), 680); // Largura da tela
        int alturaBrick = 25;
        int larguraBrick = 55;
        int espacamento = 10; // Espaço entre os bricks
        int bricksPorLinha = 9; // Número de bricks por linha

        int[] linhasY = {75,115,155}; // Posições Y das linhas

        for (int y : linhasY) {
            // Calcula a largura total ocupada pelos bricks e espaços na linha
            int larguraTotalLinha = (larguraBrick * bricksPorLinha) + (espacamento * (bricksPorLinha - 1));

            // Centraliza a linha horizontalmente
            int startX = (larguraTela - larguraTotalLinha) / 2;

            // Cria cada brick na linha
            for (int i = 0; i < bricksPorLinha; i++) {
                int x = startX + (i * (larguraBrick + espacamento));
                //Cor base dos bricks (podes variar por linha se quisermos
                Color corBrick = new Color(0, 150, 255); //azul futurista
                        bricks.add(new EstilosBricks(corBrick, x, y, larguraBrick, alturaBrick));
            }
        }//fim dos bricks

    }

    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        for (Brick brick : bricks) {
        brick.paint(g);
        }
        if (imageFundo != null) {
            g2d.drawImage(imageFundo, 0, 0, getWidth(), getHeight(), null);
        } else {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        // Pintar fundo com transparência
        float alpha = 0.0f; // Ajusta a opacidade do fundo aqui (0.0f = totalmente transparente, 1.0f = opaco)
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
       // Dentro do paintComponent(Graphics g):


         // Define uma fonte maior, por exemplo SansSerif, estilo normal, tamanho 18
          Font fonteMaior = new Font("Serif", Font.PLAIN, 20);
          g2d.setFont(fonteMaior);
        // Tempo com opacidade total
        g2d.setColor(Color.BLACK);
        g2d.drawString("Tempo: " + timeElapsed, getWidth() - 650, 30);

        //Pontuação
        g2d.setColor(Color.BLACK);//Posição do Score aparece depois da ,
        g2d.drawString("Pontuação: " + score, getWidth() - 550, 30);

        // Vidas
        g2d.setColor(Color.BLACK);
        g2d.drawString("Vidas: " + vidas, getWidth() - 100, 30);

        g2d.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        if (!ballReadyToMove) {
            repaint();
            return;
        }

        try {
            ball.move(getBounds(), this); // CORRIGIDO AQUI

            for (Brick brick : bricks) {
                if (brick.intersects(ball) && brick.isVisible) {
                    brick.isVisible = false;
                    score++;

                    ball.reverseX();
                    ball.reverseY();
                    break;
                }
            }

            pad.collide(ball);
            repaint();
        } catch (ExceptionJogo ex) {
            ex.show();
            gameTimer.stop();
        }

        verificarFimJogo();
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    pad.moveTo(e.getX(), getWidth());
    if (!ballReadyToMove) {
        ball.setPosition(pad.x + pad.width / 2 - 10, pad.y - 27);
    }
}
    public void setPaddleY(int newY) {
    pad.y = newY;
}
    public int getScore() {
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
    private int vidas = 2;

    public void decrementarVidas() {
        vidas--;
    }

    public int getVidas() {
        return vidas;
    }

    public void resetBola() {
        ballReadyToMove = false; // Permite novo disparo
        ball.setPosition(pad.x + pad.width / 2 - ball.width / 2, pad.y - ball.height);
        repaint();
    }

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

            ball.setPosition(pad.x + pad.width / 2 - 10, pad.y - 27);

        }
        //Lançar a bola ao pressionar Espaço
        if (key == KeyEvent.VK_SPACE && !ballReadyToMove) {
            Random random = new Random();
            int direction = random.nextBoolean() ? 1 : -1;
            ball.launch(direction);
            ballReadyToMove = true;
            gameTimer.start(); // Agora o jogo começa
            timeTimer.start();
        }

        if (key == KeyEvent.VK_ESCAPE) {
    gameTimer.stop();
    if (menuPausa == null || !menuPausa.isVisible()) {
        menuPausa = new MenuPausa(this);
        menuPausa.setVisible(true);
    }
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
        pararJogo(); // para ambos os timers

    score = 0;
    timeElapsed = 0;
    isGameOver = false;
    ballReadyToMove = false;
    vidas = 2;

    // Recriar o mesmo nível que já estava no jogo
    start();

    // Reiniciar os timers
    gameTimer.start();
    timeTimer.start();

    repaint();
}

    public void verificarFimJogo() {
        boolean existemBricks = false;
        for (Brick brick : bricks) {
            if (brick.isVisible) {
                existemBricks = true;
                break;
            }
        }

        if (!existemBricks) {
            gameTimer.stop();
            int escolha = JOptionPane.showOptionDialog(null, "Parabens! Concluiste o 1 nivel", "nivel Conluido", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Proximo Nivel", "Niveis"}, "Proximo nivel");

            java.awt.Window janelaAtual = javax.swing.SwingUtilities.getWindowAncestor(this);
            if (escolha == 0) {
                if (janelaAtual != null) {
                    janelaAtual.dispose();
                }
                new playGame2().setVisible(true);
            } else {
                if (janelaAtual != null) {
                    janelaAtual.dispose();
                }
                new Niveis().setVisible(true);
            }
        }
    }

    public static void playSound(String caminho) {
        if (!somAtivo) {
            return; // só toca se o som estiver ativo
        }
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                    ArkanoidGame.class.getResource(caminho));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            System.out.println("Erro ao tocar som: " + e.getMessage());
        }
    }

    public void saveGame(File file) throws IOException {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
        out.writeObject(ball);
        out.writeObject(pad);
        out.writeObject(bricks);
        out.writeInt(score);
        out.writeInt(vidas);
        out.writeInt(timeElapsed);
        out.writeBoolean(ballReadyToMove);
        out.writeObject(caminhoFundo); // <-- Adiciona isto!
    }
}

    @SuppressWarnings("unchecked")
    public void loadGame(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            ball = (Ball) in.readObject();
            ball.reload();
            pad = (Paddle) in.readObject();
            bricks = (ArrayList<Brick>) in.readObject();
            score = in.readInt();
            vidas = in.readInt();
            timeElapsed = in.readInt();
            ballReadyToMove = in.readBoolean();
            caminhoFundo = (String) in.readObject(); // <-- Adiciona isto!
            reloadFundo();

            // Recarregar imagens dos bricks
            for (Brick brick : bricks) {
                if (brick instanceof ImageBrick) {
                    ((ImageBrick) brick).reloadImage();
                }
            }

            // Parar timers antigos se existirem
            if (gameTimer != null) {
                gameTimer.stop();
            }
            if (timeTimer != null) {
                timeTimer.stop();
            }

            // Recriar e iniciar timers
            gameTimer = new Timer(10, this);
            timeTimer = new Timer(1000, e -> timeElapsed++);
            gameTimer.start();
            timeTimer.start();

            // Re-adicionar listeners
            for (KeyListener kl : getKeyListeners()) {
                removeKeyListener(kl);
            }
            addKeyListener(this);
            setFocusable(true);

            for (MouseListener ml : getMouseListeners()) {
                removeMouseListener(ml);
            }
            addMouseListener(this);

            for (MouseMotionListener mml : getMouseMotionListeners()) {
                removeMouseMotionListener(mml);
            }
            addMouseMotionListener(this);

            requestFocusInWindow(); // <-- isto é essencial!
            repaint();

        }
    }

    public void reloadFundo() {
        try {
            imageFundo = javax.imageio.ImageIO.read(getClass().getResource(caminhoFundo));
        } catch (Exception e) {
            imageFundo = null;
        }
    }
}
