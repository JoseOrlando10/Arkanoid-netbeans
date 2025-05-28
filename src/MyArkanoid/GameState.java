
package MyArkanoid;

/**
 *
 * @author pedro e josé
 */
import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    public int ballX, ballY, ballVx, ballVy;
    public int padX, padY;
    public int score;
    public int timeElapsed;
    public boolean ballReadyToMove;
    public ArrayList<Boolean> bricksVisible;

    // Construtor para preencher ao salvar
    public GameState(int ballX, int ballY, int ballVx, int ballVy,
                      int padX, int padY, int score, int timeElapsed,
                      boolean ballReadyToMove, ArrayList<Boolean> bricksVisible) {
        this.ballX = ballX;
        this.ballY = ballY;
        this.ballVx = ballVx;
        this.ballVy = ballVy;
        this.padX = padX;
        this.padY = padY;
        this.score = score;
        this.timeElapsed = timeElapsed;
        this.ballReadyToMove = ballReadyToMove;
        this.bricksVisible = bricksVisible;
    }
}
