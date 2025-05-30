package MyArkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Pedro Coelho - 25026
 * @author Jose Martins - 24269
 * 
 */

public class ImageBrick extends Brick implements Serializable{
    private String caminhoImagem;
    private transient BufferedImage imagem;

    public ImageBrick(String caminhoImagem, Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
        this.caminhoImagem = caminhoImagem;
        reloadImage();
    }

    public void reloadImage() {
        try {
            imagem = javax.imageio.ImageIO.read(getClass().getResource(caminhoImagem));
        } catch (Exception e) {
            imagem = null;
        }
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("A pintar ImageBrick em " + x + "," + y);
        if (imagem != null) {
            g.drawImage(imagem, x, y, width, height, null);
        } else {
            g.setColor(Color.GRAY);
            g.fillRect(x, y, width, height);
        }
    }
    
    public static void main(String[] args) {
        // Exemplo de como criar um objeto ImageBrick
        int x = 50;
        int y = 100;
        int larguraBrick = 75;
        int alturaBrick = 20;
        ImageBrick brick = new ImageBrick("/resources/pedras.png", Color.GRAY, x, y, larguraBrick, alturaBrick);
    }
}

