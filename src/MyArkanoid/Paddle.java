package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Classe Paddle: representa o paddle controlado pelo jogador.
 * Inclui lógica de movimento, colisão com a bola e desenho visual.
 */
public class Paddle extends GameObject implements Serializable {

    private final int speed = 18; // Velocidade de movimento com as setas

    // Construtor do paddle
    public Paddle(Color myColor, int x, int y, int width, int height) {
        // O paddle é 30% maior que o valor recebido (efeito visual)
        super(myColor, x, y, (int) (width * 1.3), height);
    }

    // Desenha o paddle com efeito 3D (cor aquamarine, borda escura e highlight)
    public void paint(Graphics gr) {
        // Corpo principal do paddle
        gr.setColor(new Color(127, 255, 212)); // aquamarine
        gr.fillRoundRect(x, y, width, height, 20, 20);

        // Borda escura para profundidade
        gr.setColor(new Color(100, 100, 100));
        gr.drawRoundRect(x, y, width, height, 20, 20);

        // Highlight branco semi-transparente para efeito 3D
        gr.setColor(new Color(255, 255, 255, 100));
        gr.fillRoundRect(x + 2, y + 2, width - 4, height / 2, 20, 20);
    }

    // Move o paddle para a posição do rato, centralizando-o
    public void moveTo(int mouseX, int panelWidth) {
        int newX = mouseX - width / 2;
        if (newX < 0) {
            newX = 0;
        }
        if (newX + width > panelWidth) {
            newX = panelWidth - width;
        }
        this.x = newX;
    }

    // Move o paddle para a esquerda (tecla)
    public void moveLeft() {
        x -= speed;
        if (x < 0) {
            x = 0;
        }
    }

    // Move o paddle para a direita (tecla)
    public void moveRight(int panelWidth) {
        int newX = x + speed;
        if (newX + width > panelWidth) {
            x = panelWidth - width;
        } else {
            x = newX;
        }
    }

    // Lógica de colisão entre bola e paddle
    public void collide(Ball b) {
        // Se a bola intersecta o paddle
        if (b.intersects(this)) {
            b.vy *= -1; // Inverte a direção vertical da bola

            // Calcula o centro da bola e do paddle
            int ballCenter = b.x + b.width / 2;
            int paddleCenter = this.x + this.width / 2;

            // Distância do centro da bola ao centro do paddle
            int distance = ballCenter - paddleCenter;

            // Fator de escala para ajustar a velocidade horizontal da bola
            double scale = (double) distance / (this.width / 2);
            // scale vai de -1 (extremo esquerdo) a 1 (extremo direito)

            // Calcula nova velocidade horizontal proporcional ao local do impacto
            int newVx = (int) (scale * 6); // velocidade máxima horizontal = 6

            // Garante que a bola nunca fica presa a ir só para cima/baixo
            if (newVx == 0) {
                newVx = (b.vx > 0) ? 1 : -1; // força um leve desvio horizontal
            }

            b.vx = newVx;

            // Reposiciona a bola por cima do paddle para evitar ficar presa
            b.y = this.y - b.height;
            b.move(); // Aplica o movimento ajustado
        }
    }

    // Método para recarregar recursos após desserialização (se necessário)
    public void reload() {
        // Reinicializa recursos se necessário
    }

    // Serialização personalizada (caso precises de guardar mais campos)
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // Adicione manualmente campos não-serializáveis se necessário
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.reload(); // Recarrega recursos após desserialização
    }

    private static final long serialVersionUID = 1L;
}
