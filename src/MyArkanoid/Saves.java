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
public class Saves implements Serializable {
    private static final long serialVersionUID = 1L;

    public int vidas;
    public int nivel; // ESTE campo será o currentLevel salvo
    public int score;
    public ArrayList<BrickData> bricks;
   

    public Saves(int vidas, int score, int nivel, ArrayList<Brick> bricksOriginal) {
        this.vidas = vidas;
        this.score = score;
        this.nivel = nivel; // CORRETO: o nivel real do jogo (currentLevel)
        this.bricks = new ArrayList<>();
        for (Brick b : bricksOriginal) {
            this.bricks.add(new BrickData(b));
        }
        
    }
}


