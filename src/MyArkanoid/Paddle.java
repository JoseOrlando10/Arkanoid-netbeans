package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */
public class Paddle extends GameObject implements Serializable {

    private final int speed = 18; // Velocidade de movimento com as setas

    // Construtor do paddle
    public Paddle(Color myColor, int x, int y, int width, int height) {
        super(myColor, x, y, (int)(width * 1.3), height); // Aumenta o tamanho do paddle em 30% (*1.3)
    }

    public void paint(Graphics gr) {
        // Pintura ao estilo clássico Arkanoid (cantos arredondados e highlight para 3D)

        // Corpo principal do paddle: cor base
        gr.setColor(new Color(200, 200, 255)); // azul clarinho
        gr.fillRoundRect(x, y, width, height, 20, 20);

        // Borda mais escura para dar profundidade
        gr.setColor(new Color(100, 100, 255));
        gr.drawRoundRect(x, y, width, height, 20, 20);

        // Highlight para efeito 3D
        gr.setColor(new Color(255, 255, 255, 100)); // branco semi-transparente
        gr.fillRoundRect(x + 2, y + 2, width - 4, height / 2, 20, 20);
    }

    // Movimento para onde está o rato (centraliza o paddle em relação ao rato)
    public void moveTo(int mouseX, int panelWidth) {
        int newX = mouseX - width / 2;
        if (newX < 0) newX = 0;
        if (newX + width > panelWidth) newX = panelWidth - width;
        this.x = newX;
    }

    // Movimento para a esquerda
    public void moveLeft() {
        x -= speed;
        if (x < 0) x = 0;
    }

    // Movimento para a direita
    public void moveRight(int panelWidth) {
    // Calcula a nova posição proposta
    int newX = x + speed;

    // Se a nova posição proposta + largura do paddle passa do painel
    if (newX + width > panelWidth) {
        // Encosta o paddle ao limite direito
        x = panelWidth - width;
    } else {
        // Caso contrário, move normalmente
        x = newX;
    }
}

    // Colisão entre bola e paddle
    public void collide(Ball b) {

        // Problema encontrado: Quando o paddle está quieto, a colisão funciona mas a bola está a ficar presa dentro do paddle,
        // tudo indica que o paddle está a ser pintado em cima da bola
        // Solução: Verificar se o paddle não está a ser pintado em cima da bola; se sim, o jogo deve entender isso como um remate
        if (b.intersects(this)) {
            b.vy *= -1;

            // Calcula o ponto central da bola e do paddle
            int ballCenter = b.x + b.width / 2;
            int paddleCenter = this.x + this.width / 2;

            // Distância do centro da bola para o centro do paddle
            int distance = ballCenter - paddleCenter;

            // Define um fator de escala para a velocidade horizontal, não obrigatório, mas faz com que o ricochete seja mais suave e proporcional à zona de impacto
            double scale = (double) distance / (this.width / 2);
            /* Vai dar sempre um resultado entre 1 e -1 conforme o sítio onde a bola colide com o paddle,
             ou seja, se bater no extremo esquerdo, vai dar -1, se bater no centro vai dar 0 e se bater no extremo direito vai dar 1 */

            // Multiplica por uma velocidade máxima
            int newVx = (int) (scale * 6); // velocidade máxima horizontal pode chegar até 6

            // Garante que a bola tenha sempre um movimento leve horizontal, mesmo quando bate exatamente no meio do paddle
            if (newVx == 0) { // Se newVx == 0, então a bola bateu exatamente no meio do paddle, o que faria que acontecesse apenas um movimento vertical
                // Isso pode fazer com que a bola fique presa dentro do paddle, então forçamos um leve desvio horizontal
                newVx = (b.vx > 0) ? 1 : -1; // vai manter a direção horizontal da bola, mas com intensidade mínima
            }

            b.vx = newVx;

            // Reposiciona a bola fora do paddle para não ficar presa
            b.y = this.y - b.height; // Coloca a bola por cima do paddle
            b.move(); // Aplica o movimento ajustado
        }
    }

}