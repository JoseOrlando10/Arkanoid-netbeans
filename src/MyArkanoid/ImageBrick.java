package MyArkanoid;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ImageBrick extends Brick {
    private Image image;

    public ImageBrick(String imagePath, int x, int y, int width, int height) {
        super(null, x, y, width, height); // ignora a cor
        try {
            image = ImageIO.read(getClass().getResource("/resources/pedras.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Erro ao carregar imagem: " + imagePath);
        }
    }

    public void paint(Graphics g) {
        if (isVisible && image != null) {
            g.drawImage(image, x, y, width, height, null);
        }
    }
}
