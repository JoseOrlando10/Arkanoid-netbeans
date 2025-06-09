package MyArkanoid;
//ola
// Importações necessárias para gráficos, eventos, som, etc.
import arkanoide_exe.MenuPausa;
import Niveis.playGame2;
import Niveis.playGame3;
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
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 * Classe principal do jogo Arkanoid.
 * Responsável por toda a lógica, desenho, eventos e timers do jogo.
 */
public class ArkanoidGame extends JComponent
        implements ActionListener, MouseMotionListener, KeyListener, MouseListener {

    // Objetos principais do jogo
    Ball ball; // Bola do jogo
    ArrayList<Brick> bricks; // Lista de bricks
    Paddle pad; // Paddle do jogador

    // Timers para lógica do jogo e tempo
    Timer gameTimer; // Timer principal do jogo (movimento, colisões)
    Timer timeTimer; // Timer para contar o tempo de jogo
    int timeElapsed; // Tempo decorrido em segundos

    boolean ballReadyToMove = false; // Indica se a bola já foi lançada
    private int vidas = 2; // Número de vidas do jogador
    public static boolean somAtivo = true; // Se o som está ativo
    private static Clip musicaMenu; // Música do menu (se usada)
    private int score = 0; // Pontuação do jogador
    private MenuPausa menuPausa; // Menu de pausa
    private int currentLevel = 1; // Nível atual
    private int totalLevels = 3; // Total de níveis
    private Timer fallTimer; // Timer para efeitos de queda (se usado)
    private boolean isGameOver = false; // Se o jogo terminou
    private int nivelAtual; // (não usado diretamente)
    private String caminhoFundo = "/resources/fundo.png"; // Caminho do fundo
    private transient Image imageFundo; // Imagem de fundo
    private int nivelMaxDesbloqueado = 1; // Nível máximo desbloqueado

    // Construtor padrão
    public ArkanoidGame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setPreferredSize(new java.awt.Dimension(700, 570)); // Tamanho da janela
        start(); // Inicia o jogo (nível 1)
        addKeyListener(this); // Permite captar teclas
        setFocusable(true); // Permite receber foco do teclado

        timeElapsed = 0; // Inicia o tempo
        gameTimer = new Timer(10, this); // Timer principal (10ms)
        gameTimer.start();

        // Timer para contar o tempo (1 segundo)
        timeTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeElapsed++; // Incrementa o tempo
            }
        });

        addMouseMotionListener(this); // Permite mover o paddle com o rato
        addMouseListener(this); // Permite lançar a bola com o rato
        reloadFundo(); // Carrega a imagem de fundo
    }

    // Construtor alternativo (pode saltar o start)
    public ArkanoidGame(boolean skipStart) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    // Evento de clique do rato: lança a bola se ainda não foi lançada
    public void mouseClicked(MouseEvent e) {
        if (!ballReadyToMove) {
            Random random = new Random();
            int direction = random.nextBoolean() ? 1 : -1;
            ball.launch(direction); // Lança a bola numa direção aleatória
            ballReadyToMove = true;
            gameTimer.start();
            timeTimer.start();
        }
    }

    // Classe interna para bricks com estilo visual especial
    public class EstilosBricks extends Brick {
        public EstilosBricks(Color baseColor, int x, int y, int width, int height) {
            super(baseColor, x, y, width, height);
        }

        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint(
                    x, y, baseColor.brighter(),
                    x, y + height, baseColor.darker());
            g2d.setPaint(gp);
            RoundRectangle2D.Double rect = new RoundRectangle2D.Double(x, y, width, height, 15, 15);
            g2d.fill(rect);
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(baseColor.brighter().brighter());
            g2d.draw(rect);
            GradientPaint highlight = new GradientPaint(
                    x, y, new Color(255, 255, 255, 150),
                    x, y + height / 2, new Color(255, 255, 255, 0));
            g2d.setPaint(highlight);
            g2d.fill(new RoundRectangle2D.Double(x + 2, y + 2, width - 4, height / 2, 15, 15));
            g2d.dispose();
        }
    }

    // Inicia o nível 1
    public void start() {
        currentLevel = 1;
        pad = new Paddle(Color.RED, 200, 480, 50, 10); // Cria o paddle
        ball = new Ball(Color.LIGHT_GRAY, pad.x + pad.width / 2 - 20, pad.y - 40); // Cria a bola

        // Cria os bricks do nível 1
        bricks = new ArrayList<>();
        int larguraTela = Math.max(getWidth(), 680);
        int alturaBrick = 25;
        int larguraBrick = 55;
        int espacamento = 60;
        int[] bricksPorLinha = {5, 4};
        int[] linhasY = {75, 115};
        for (int linha = 0; linha < linhasY.length; linha++) {
            int y = linhasY[linha];
            int numBricks = bricksPorLinha[linha];
            int larguraTotalLinha = (larguraBrick * numBricks) + (espacamento * (numBricks - 1));
            int startX = (larguraTela - larguraTotalLinha) / 2;
            for (int i = 0; i < numBricks; i++) {
                int x = startX + (i * (larguraBrick + espacamento));
                Color corBrick = new Color(100, 149, 237);
                bricks.add(new EstilosBricks(corBrick, x, y, larguraBrick, alturaBrick));
            }
        }
        verificarFimDeNivel();
    }

    // Inicia o nível 2 (estrutura semelhante ao start)
    public void start2() {
        currentLevel = 2;
        pad = new Paddle(Color.RED, 200, 480, 50, 10);
        ball = new Ball(Color.YELLOW, pad.x + pad.width / 2 - 20, pad.y - 40);
        bricks = new ArrayList<>();
        int larguraTela = Math.max(getWidth(), 680);
        int alturaBrick = 25;
        int larguraBrick = 55;
        int espacamento = 70;
        int[] bricksPorLinha = {5, 4, 3};
        int[] linhasY = {75, 115, 155};
        for (int linha = 0; linha < linhasY.length; linha++) {
            int y = linhasY[linha];
            int numBricks = bricksPorLinha[linha];
            int larguraTotalLinha = (larguraBrick * numBricks) + (espacamento * (numBricks - 1));
            int startX = (larguraTela - larguraTotalLinha) / 2;
            for (int i = 0; i < numBricks; i++) {
                int x = startX + (i * (larguraBrick + espacamento));
                Color corBrick = new Color(255, 255, 0);
                bricks.add(new EstilosBricks(corBrick, x, y, larguraBrick, alturaBrick));
            }
        }
        verificarFimDeNivel();
    }

    // Inicia o nível 3 (estrutura semelhante)
    public void start3() {
        currentLevel = 3;
        pad = new Paddle(Color.RED, 200, 480, 50, 10);
        ball = new Ball(Color.RED, pad.x + pad.width / 2 - 20, pad.y - 40);
        bricks = new ArrayList<>();
        int larguraTela = Math.max(getWidth(), 680);
        int alturaBrick = 25;
        int larguraBrick = 55;
        int espacamento = 90;
        int[] bricksPorLinha = {5, 4, 3, 2, 1};
        int[] linhasY = {75, 115, 155, 195, 235};
        for (int linha = 0; linha < linhasY.length; linha++) {
            int y = linhasY[linha];
            int numBricks = bricksPorLinha[linha];
            int larguraTotalLinha = (larguraBrick * numBricks) + (espacamento * (numBricks - 1));
            int startX = (larguraTela - larguraTotalLinha) / 2;
            for (int i = 0; i < numBricks; i++) {
                int x = startX + (i * (larguraBrick + espacamento));
                Color corBrick = new Color(255, 0, 0);
                bricks.add(new EstilosBricks(corBrick, x, y, larguraBrick, alturaBrick));
            }
        }
        verificarFimDeNivel();
    }

    // Desenha todos os elementos do jogo
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Desenha o fundo
        if (imageFundo != null) {
            g2d.drawImage(imageFundo, 0, 0, getWidth(), getHeight(), null);
        } else {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        // Pintar fundo com transparência (opcional)
        float alpha = 0.0f;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        // Desenha bola, bricks e paddle
        ball.paint(g2d);
        for (Brick brick : bricks) {
            if (brick.isVisible) {
                brick.paint(g2d);
            }
        }
        pad.paint(g2d);

        // Desenha informações (tempo, nível, score, vidas)
        Font fonteMaior = new Font("Serif", Font.PLAIN, 20);
        g2d.setFont(fonteMaior);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Tempo: " + timeElapsed, getWidth() - 650, 30);
        g2d.drawString("Nível " + currentLevel + " / " + totalLevels, getWidth() - 210, 30);
        g2d.drawString("Pontuação: " + score, getWidth() - 550, 30);
        g2d.drawString("Vidas: " + vidas, getWidth() - 100, 30);

        g2d.dispose();
    }

    // Lógica principal do jogo (chamada pelo gameTimer)
    public void actionPerformed(ActionEvent e) {
        if (!ballReadyToMove) {
            repaint();
            return;
        }

        try {
            ball.move(getBounds(), this); // Move a bola e verifica colisões

            // Verifica colisão com bricks
            for (Brick brick : bricks) {
                if (brick.intersects(ball) && brick.isVisible) {
                    brick.isVisible = false; // Brick partido
                    score++; // Aumenta score
                    ball.reverseX();
                    ball.reverseY();
                    ArkanoidGame.playSound("/resources/somdepartir.wav");
                    break;
                }
            }

            pad.collide(ball); // Verifica colisão com paddle
            repaint();
        } catch (ExceptionJogo ex) {
            ex.show(); // Mostra mensagem de erro (ex: perdeu todas as vidas)
            gameTimer.stop();
        }

        if (vidas <= 0) {
            mostrarGameOver(); // Mostra janela de game over
            return;
        }

        verificarFimDeNivel(); // Verifica se o nível terminou
    }

    // Eventos do rato e teclado (mover paddle, lançar bola, etc.)
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {
        pad.moveTo(e.getX(), getWidth());
        if (!ballReadyToMove) {
            ball.setPosition(pad.x + pad.width / 2 - 10, pad.y - 27);
        }
    }
    public void setPaddleY(int newY) { pad.y = newY; }
    public int getScore() { return score; }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    // Vidas
    public void decrementarVidas() { vidas--; }
    public int getVidas() { return vidas; }

    // Reseta a bola para cima do paddle
    public void resetBola() {
        ballReadyToMove = false;
        ball.setPosition(pad.x + pad.width / 2 - ball.width / 2, pad.y - ball.height);
        repaint();
    }

    // Teclado: mover paddle, lançar bola, pausar, etc.
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            pad.moveLeft();
        } else if (key == KeyEvent.VK_RIGHT) {
            pad.moveRight(getWidth());
        }
        if (!ballReadyToMove) {
            ball.setPosition(pad.x + pad.width / 2 - 10, pad.y - 27);
        }
        if (key == KeyEvent.VK_SPACE && !ballReadyToMove) {
            Random random = new Random();
            int direction = random.nextBoolean() ? 1 : -1;
            ball.launch(direction);
            ballReadyToMove = true;
            gameTimer.start();
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
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    // Continua o jogo após pausa
    public void continuarJogo() {
        gameTimer.start();
    }

    // Para ambos os timers
    public void pararJogo() {
        gameTimer.stop();
        timeTimer.stop();
    }

    // Reinicia o jogo do zero (nível 1, score 0, etc.)
    public void resetJogo() {
        pararJogo();
        score = 0;
        timeElapsed = 0;
        isGameOver = false;
        ballReadyToMove = false;
        vidas = 2;
        start();
        gameTimer.start();
        timeTimer.start();
        repaint();
    }

    // Reinicia o nível atual (mantendo score, mas resetando bricks, bola, paddle, vidas)
    public void reiniciarNivelAtual() {
        pararJogo();
        this.bricks.clear();
        carregarNivel(currentLevel, true);
        Color corBola = ball.getMyColor();
        float gradCenter = ball.getGradCenterFactor();
        float gradRadius = ball.getGradRadiusFactor();
        pad = new Paddle(Color.RED, 200, 480, 50, 10);
        ball = new Ball(corBola, pad.x + pad.width / 2 - 20, pad.y - 40, gradCenter, gradRadius);
        ballReadyToMove = false;
        isGameOver = false;
        this.vidas = 2;
        gameTimer.start();
        timeTimer.start();
        repaint();
    }

    // Verifica se o nível terminou (todos os bricks partidos)
    public void verificarFimDeNivel() {
        boolean nivelCompleto = true;
        for (Brick brick : bricks) {
            if (brick.isVisible) {
                nivelCompleto = false;
                break;
            }
        }
        if (nivelCompleto) {
            gameTimer.stop();
            timeTimer.stop();
            String mensagem = "Parabéns! Concluíste o nível " + currentLevel + "!";
            String[] opcoes;
            if (currentLevel < totalLevels) {
                opcoes = new String[]{"Próximo Nível", "Sair"};
            } else {
                opcoes = new String[]{"Menu Inicial"};
            }
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    mensagem,
                    "Nível Concluído",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );
            java.awt.Window janelaAtual = javax.swing.SwingUtilities.getWindowAncestor(this);
            if (escolha == 0) {
                if (currentLevel < totalLevels) {
                    if (janelaAtual != null) {
                        janelaAtual.dispose();
                    }
                    switch (currentLevel) {
                        case 1:
                            new playGame2().setVisible(true);
                            break;
                        case 2:
                            new playGame3().setVisible(true);
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, "Parabéns! Todos os níveis foram concluídos!");
                            new arkanoide_exe.Arkanoide().setVisible(true);
                            break;
                    }
                } else {
                    if (janelaAtual != null) {
                        janelaAtual.dispose();
                    }
                    new arkanoide_exe.Arkanoide().setVisible(true);
                }
            } else if (escolha == 1) {
                if (janelaAtual != null) {
                    janelaAtual.dispose();
                }
                new arkanoide_exe.Arkanoide().setVisible(true);
            }
        }
    }

    // Cria os bricks de cada nível (usado para reset/reiniciar)
    public void carregarNivel(int nivel, boolean criarBricks) {
        this.currentLevel = nivel;
        if (criarBricks) {
            this.bricks.clear();
            if (nivel == 1) {
                // --- CÓDIGO DOS BRICKS DO NÍVEL 1 ---
                bricks = new ArrayList<>();
                int larguraTela = Math.max(getWidth(), 680); // Largura da tela
                int alturaBrick = 25;
                int larguraBrick = 55;
                int espacamento = 60; // Espaço entre os bricks

                // Número de bricks por linha
                int[] bricksPorLinha = {5, 4};
                int[] linhasY = {75, 115}; // Posições Y das linhas

                for (int linha = 0; linha < linhasY.length; linha++) {
                    int y = linhasY[linha];
                    int numBricks = bricksPorLinha[linha];

                    // Calcula a largura total ocupada pelos bricks e espaços na linha
                    int larguraTotalLinha = (larguraBrick * numBricks) + (espacamento * (numBricks - 1));

                    // Centraliza a linha horizontalmente
                    int startX = (larguraTela - larguraTotalLinha) / 2;

                    // Cria cada brick na linha
                    for (int i = 0; i < numBricks; i++) {
                        int x = startX + (i * (larguraBrick + espacamento));
                        Color corBrick = new Color(100, 149, 237); // azul 
                        bricks.add(new EstilosBricks(corBrick, x, y, larguraBrick, alturaBrick));
                    }
                }// fim dos bricks

            } else if (nivel == 2) {
                // --- CÓDIGO DOS BRICKS DO NÍVEL 2 ---
                bricks = new ArrayList<>();
                int larguraTela = Math.max(getWidth(), 680); // Largura da tela
                int alturaBrick = 25;
                int larguraBrick = 55;
                int espacamento = 70; // Espaço entre os bricks

                // Número de bricks por linha
                int[] bricksPorLinha = {5, 4, 3};
                int[] linhasY = {75, 115, 155}; // Posições Y das linhas

                for (int linha = 0; linha < linhasY.length; linha++) {
                    int y = linhasY[linha];
                    int numBricks = bricksPorLinha[linha];

                    // Calcula a largura total ocupada pelos bricks e espaços na linha
                    int larguraTotalLinha = (larguraBrick * numBricks) + (espacamento * (numBricks - 1));

                    // Centraliza a linha horizontalmente
                    int startX = (larguraTela - larguraTotalLinha) / 2;

                    // Cria cada brick na linha
                    for (int i = 0; i < numBricks; i++) {
                        int x = startX + (i * (larguraBrick + espacamento));
                        Color corBrick = new Color(255, 255, 0); // amarelo
                        bricks.add(new EstilosBricks(corBrick, x, y, larguraBrick, alturaBrick));
                    }
                }// fim dos bricks

            } else if (nivel == 3) {
                // --- CÓDIGO DOS BRICKS DO NÍVEL 3 ---
                /// Bricks
    bricks = new ArrayList<>();
                int larguraTela = Math.max(getWidth(), 680); // Largura da tela
                int alturaBrick = 25;
                int larguraBrick = 55;
                int espacamento = 90; // Espaço entre os bricks

                // Número de bricks por linha
                int[] bricksPorLinha = {5, 4, 3, 2, 1};
                int[] linhasY = {75, 115, 155, 195, 235}; // Posições Y das linhas

                for (int linha = 0; linha < linhasY.length; linha++) {
                    int y = linhasY[linha];
                    int numBricks = bricksPorLinha[linha];

                    // Calcula a largura total ocupada pelos bricks e espaços na linha
                    int larguraTotalLinha = (larguraBrick * numBricks) + (espacamento * (numBricks - 1));

                    // Centraliza a linha horizontalmente
                    int startX = (larguraTela - larguraTotalLinha) / 2;

                    // Cria cada brick na linha
                    for (int i = 0; i < numBricks; i++) {
                        int x = startX + (i * (larguraBrick + espacamento));
                        Color corBrick = new Color(255, 0, 0); // vermelho
                        bricks.add(new EstilosBricks(corBrick, x, y, larguraBrick, alturaBrick));
                    }
                }// fim dos bricks

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

    public void saveGame(File file) throws Exception {
        Saves data = new Saves(this.vidas, this.score, this.currentLevel, this.bricks, this.ball, this.timeElapsed);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(data);
            out.flush();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadGame(File file) throws Exception {
        if (!file.exists()) {
            throw new FileNotFoundException("Arquivo não encontrado");
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Saves data = (Saves) in.readObject();
            this.vidas = data.vidas;
            this.timeElapsed = data.timeElapsed;
            this.score = data.score;
            this.currentLevel = data.nivel;

            this.bricks = new ArrayList<>();
            for (BrickData bd : data.bricks) {
                this.bricks.add(bd.toBrick(this)); // <-- passa o ArkanoidGame para criar EstilosBricks
            }
            this.carregarNivel(this.currentLevel, false);
            pad = new Paddle(Color.RED, 200, 480, 50, 10);
            Color corBola = new Color(data.ballR, data.ballG, data.ballB);
            ball = new Ball(corBola, pad.x + pad.width / 2 - 20, pad.y - 40, data.gradCenterFactor, data.gradRadiusFactor);
        }
    }

    public void reloadFundo() {
        try {
            imageFundo = javax.imageio.ImageIO.read(getClass().getResource(caminhoFundo));
        } catch (Exception e) {
            imageFundo = null;
        }
    }

    public void mostrarGameOver() {
        gameTimer.stop();
        timeTimer.stop();

        String mensagem = "Perdeste o jogo!";
        String[] opcoes = {"Reiniciar Nível", "Menu Inicial"};

        int escolha = JOptionPane.showOptionDialog(
                null,
                mensagem,
                "Game Over",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        java.awt.Window janelaAtual = javax.swing.SwingUtilities.getWindowAncestor(this);

        if (escolha == 0) { // Reiniciar Nível
            reiniciarNivelAtual();
        } else if (escolha == 1) { // Menu Inicial
            if (janelaAtual != null) {
                janelaAtual.dispose();
            }
            new arkanoide_exe.Arkanoide().setVisible(true);
        }
    }
    private static final long serialVersionUID = 1L;
}
//teste
