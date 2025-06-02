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
import javax.swing.ImageIcon;
import javax.swing.UIManager;

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
    private int vidas = 2;
    public static boolean somAtivo = true;
    private static Clip musicaMenu;
    private int score = 0; // Inicializa a pontuação
    private MenuPausa menuPausa;
    private int currentLevel = 1; // Começa no nível 1
    private int totalLevels = 3; // Supondo que tens 3 níveis Número máximo de vidas
    private Timer fallTimer;
    private boolean isGameOver = false;
     private int nivelAtual; // definir este valor no construtor
    private String caminhoFundo = "/resources/fundo.png";
    private transient Image imageFundo;

    private int nivelMaxDesbloqueado = 1; // Começa só com o nível 1 desbloqueado

    public ArkanoidGame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //this(false);
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
    currentLevel = 1;
    // Define o paddle mais embaixo
    pad = new Paddle(Color.RED, 200, 480, 50, 10);
    ball = new Ball(Color.LIGHT_GRAY, pad.x + pad.width / 2 - 20, pad.y - 40);

    /// Bricks
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

    verificarFimDeNivel();
}

    public void start2() {
    currentLevel = 2;
    // Define o paddle mais embaixo
    pad = new Paddle(Color.RED, 200, 480, 50, 10);
    ball = new Ball(Color.YELLOW, pad.x + pad.width / 2 - 20, pad.y - 40);

    /// Bricks
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
     verificarFimDeNivel();
    
}

    public void start3() {
    currentLevel = 3;
    // Define o paddle mais embaixo
    pad = new Paddle(Color.RED, 200, 480, 50, 10);
    ball = new Ball(Color.RED, pad.x + pad.width / 2 - 20, pad.y - 40);

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

    verificarFimDeNivel();
}

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

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

        // Define uma fonte maior, por exemplo Serif, estilo normal, tamanho 20
        Font fonteMaior = new Font("Serif", Font.PLAIN, 20);
        g2d.setFont(fonteMaior);
        // Tempo com opacidade total
        g2d.setColor(Color.BLACK);
        g2d.drawString("Tempo: " + timeElapsed, getWidth() - 650, 30);
        //Número de niveis no qual o jogador se encontra 
        g2d.setColor(Color.BLACK);
        g2d.drawString("Nível " + currentLevel + " / " + totalLevels, getWidth() - 210, 30);
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
                    ArkanoidGame.playSound("/resources/somdepartir.wav");
                    break;
                }
            }

            pad.collide(ball);
            repaint();
        } catch (ExceptionJogo ex) {
            ex.show();
            gameTimer.stop();
        }

        if (vidas <= 0) {
    mostrarGameOver();
    return;
}

        verificarFimDeNivel();
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

    //VERIFICAR SE QUERES ESTA FUNÇÃO, SE QUERES REINICIAR SEMPRE A VOLTAR AO NIVEL 1, 
    //SE NAO QUISERES TENS A FUNÇAO ABAIXO QUE REINICIA NO NIVEL ATUAL, e ta definida assim no menu pausa
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

    public void reiniciarNivelAtual() {
        pararJogo();
        // Limpa os bricks e recria-os como no início do nível
        this.bricks.clear();
        carregarNivel(currentLevel, true); // true para criar bricks do zero
        
        // Guarda os efeitos atuais da bola
    Color corBola = ball.getMyColor();
    float gradCenter = ball.getGradCenterFactor();
    float gradRadius = ball.getGradRadiusFactor();
        
        // Reposiciona paddle e bola
        pad = new Paddle(Color.RED, 200, 480, 50, 10);
        ball = new Ball(corBola, pad.x + pad.width / 2 - 20, pad.y - 40, gradCenter, gradRadius);
        ballReadyToMove = false;
        isGameOver = false;
        this.vidas = 2; // <-- repõe as vidas!
        gameTimer.start();
        timeTimer.start();
        repaint();
    }


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

            // Determinar opções baseadas no nível atual
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

            // Obter referência à janela atual
            java.awt.Window janelaAtual = javax.swing.SwingUtilities.getWindowAncestor(this);

            if (escolha == 0) { // Usuário escolheu a primeira opção
                if (currentLevel < totalLevels) {
                    // Fechar janela atual e abrir próximo nível
                    if (janelaAtual != null) {
                        janelaAtual.dispose();
                    }

                    // Abrir nova janela com próximo nível
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
                    // Último nível concluído - voltar ao menu
                    if (janelaAtual != null) {
                        janelaAtual.dispose();
                    }

                    new arkanoide_exe.Arkanoide().setVisible(true);
                }
            } else if (escolha == 1) { // Usuário escolheu "Sair"
                // Voltar ao menu principal
                if (janelaAtual != null) {
                    janelaAtual.dispose();
                }
                new arkanoide_exe.Arkanoide().setVisible(true);
            }
            // Se usuário fechar a janela (escolha = -1), não faz nada
        }
    }

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
        Saves data = new Saves(this.vidas, this.score, this.currentLevel, this.bricks, this.ball);
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