/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyArkanoid;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jooma
 */
public class Saves implements Serializable{
    private static final long serialVersionUID = 1L;
    public int vidas;
    public int score;
    public ArrayList<Brick> bricks;

    public Saves(int vidas, int score, ArrayList<Brick> bricks) {
        this.vidas = vidas;
        this.score = score;
        this.bricks = new ArrayList<>(bricks);
    }
}
