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
    public int r, g, b; // cor

    public BrickData(Brick b) {
        this.x = b.x;
        this.y = b.y;
        this.width = b.width;
        this.height = b.height;
        this.r = b.getMyColor().getRed();
        this.g = b.getMyColor().getGreen();
        this.b = b.getMyColor().getBlue();
    }

    public Brick toBrick() {
        return new Brick(new Color(r, g, b), x, y, width, height);
    }
}

