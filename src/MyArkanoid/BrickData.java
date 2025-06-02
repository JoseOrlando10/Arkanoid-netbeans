/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyArkanoid;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author jooma
 */
public class BrickData implements Serializable {
    public int x, y, width, height;
    public int r, g, b;
    public String tipo;
    public boolean isVisible; // <-- Adiciona isto

    public BrickData(Brick b) {
        this.x = b.x;
        this.y = b.y;
        this.width = b.width;
        this.height = b.height;
        this.r = b.getMyColor().getRed();
        this.g = b.getMyColor().getGreen();
        this.b = b.getMyColor().getBlue();
        this.tipo = b.getClass().getSimpleName();
        this.isVisible = b.isVisible; // <-- Guarda o estado
    }

    public Brick toBrick(ArkanoidGame game) {
        Color cor = new Color(r, g, b);
        Brick brick;
        switch (tipo) {
            case "EstilosBricks":
                brick = game.new EstilosBricks(cor, x, y, width, height);
                break;
            default:
                brick = new Brick(cor, x, y, width, height);
        }
        brick.isVisible = this.isVisible; // <-- Restaura o estado
        return brick;
    }
}


