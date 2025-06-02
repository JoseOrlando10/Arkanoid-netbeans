/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyArkanoid;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jooma
 */
public class Saves implements Serializable {
    private static final long serialVersionUID = 1L;

    public int vidas, score, nivel;
    public List<BrickData> bricks;
    public int ballR, ballG, ballB;
    public float gradCenterFactor, gradRadiusFactor;

    public Saves(int vidas, int score, int nivel, List<Brick> bricks, Ball ball) {
        this.vidas = vidas;
        this.score = score;
        this.nivel = nivel;
        this.bricks = bricks.stream().map(BrickData::new).toList();
        this.ballR = ball.getMyColor().getRed();
        this.ballG = ball.getMyColor().getGreen();
        this.ballB = ball.getMyColor().getBlue();
        this.gradCenterFactor = ball.getGradCenterFactor();
        this.gradRadiusFactor = ball.getGradRadiusFactor();
    }
}


