/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyArkanoid;

import java.io.Serializable;
import java.util.List;

/**
 * Classe que guarda o estado completo do jogo para serialização (save/load).
 */
public class Saves implements Serializable {
    private static final long serialVersionUID = 1L;

    public int vidas, score, nivel; // Estado geral do jogador
    public List<BrickData> bricks;  // Lista de bricks (com estado de cada um)
    public int ballR, ballG, ballB; // Cor da bola
    public float gradCenterFactor, gradRadiusFactor; // Efeito visual da bola
    public int timeElapsed; // Tempo de jogo decorrido

    // Construtor: recebe o estado atual do jogo e guarda tudo
    public Saves(int vidas, int score, int nivel, List<Brick> bricks, Ball ball, int timeElapsed) {
        this.vidas = vidas;
        this.score = score;
        this.nivel = nivel;
        this.bricks = bricks.stream().map(BrickData::new).toList(); // Converte bricks reais em BrickData
        this.ballR = ball.getMyColor().getRed();
        this.ballG = ball.getMyColor().getGreen();
        this.ballB = ball.getMyColor().getBlue();
        this.gradCenterFactor = ball.getGradCenterFactor();
        this.gradRadiusFactor = ball.getGradRadiusFactor();
        this.timeElapsed = timeElapsed;
    }
}


