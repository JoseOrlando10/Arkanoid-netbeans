package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */

public class Paddle extends GameObject {

    private Image image;
    private final int speed = 17; // Velocidade de movimento com as setas

    public Paddle(Color myColor, int x, int y, int width, int height) {
        super(myColor, x, y, width, height);

        try {
            image = ImageIO.read(getClass().getResource("/resources/tronco.jpg")); // Caminho relativo ao src
        } catch (IOException | IllegalArgumentException ex) {
            System.err.println("Erro ao carregar imagem do paddle: " + ex.getMessage());
        }
    }
    
    public void paint(Graphics gr) {
        if (image != null) {
            gr.drawImage(image, x, y, width, height, null);
        } else {
            // Fallback caso a imagem não carregue
            gr.setColor(myColor);
            gr.fillRect(x, y, width, height);
            gr.setColor(Color.DARK_GRAY);
            gr.drawRect(x, y, width, height);
        }
    }

    public void moveTo(int mouseX, int panelWidth) {
        int newX = mouseX - width / 2;
        if (newX < 0) newX = 0;
        if (newX + width > panelWidth) newX = panelWidth - width;
        this.x = newX;
    }

    public void moveLeft() {
        x -= speed;
        if (x < 0) x = 0;
    }

    public void moveRight(int panelWidth) {
        x += speed;
        if (x + width > panelWidth) {
            x = panelWidth - width;
        }
    }

    public void collide(Ball b) {

        //Problema encontrado: Quando o paddle esta quieto, a colisao funciona mas a bola esta a ficar presa dentro do padle,
        //tudo indica que o paddle está se a teletransportar para cima da bola
        //Solução:Verificar se o paddle nao esta a ser pintado em cima da bola, se sim, o jogo deve entender isso como um remate 
        if (b.intersects(this)) {
            b.vy *= -1;

            //Calcula o ponto central da bola e do paddle
            int ballCenter = b.x + b.width / 2;
            int paddleCenter = this.x + this.width / 2;

            //Distancia do centro da bola para o centro do paddle
            int distance = ballCenter - paddleCenter;

            //Define um fator de escala para a velocidade horizontal, não obrigatório, mas faz com que o ricochete seja mais suave e propicional á zona de impacto
            double scale = (double) distance / (this.width / 2);
            /*vai dar sempre um resultado entre 1 e -1 conforme o sitío onde a bola colide com o paddle, 
                                        ou seja, se bater no extremo esquerdo, vai dar -1, se bater no centro vai dar 0 e se bater no extremo direito vai dar 1 */

            //Multiplica por uma velocidade máxima
            int newVx = (int) (scale * 6); //velocidade maxima horizontal pode chegar até 6

            //Garante que a bola tenha sempre um movimento leve horizontal, mesmo quando bate exatamente no meio do paddle
            if (newVx == 0) { //Se newVx == 0, então a bola bateu exatamente no meio do paddle, o que faria que acontece-se apenas um movimento veritical
                //Isso pode fazer com que a bola fique presa dentro do paddle, então forçamos um leve desvio horizontal
                newVx = (b.vx > 0) ? 1 : -1; //vai manter a direção horizontal da bola, mas com intensidade minima
            }

            b.vx = newVx;

            //Reposiciona a bola fora do paddle para não ficar presa
            b.y = this.y - b.height; //Coloca a bola por cima do paddle
            b.move(); //Aplica o movimento ajustado 
        }
    }
    
}