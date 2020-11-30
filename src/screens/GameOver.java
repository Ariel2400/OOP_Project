//id:213214026
package screens;

import biuoop.DrawSurface;
import game.Animation;
/**
 * the screen that's shown at the end of the game.
 * */
public class GameOver implements Animation {
    private boolean hasWon;
    private int score;
    /**
     * creates the object.
     * @param score the final score.
     * @param hasWon has the player won or not
     * */
    public GameOver(boolean hasWon, int score) {
        this.hasWon = hasWon;
        this.score = score;

    }
    @Override
    public void doOneFrame(DrawSurface d) {
        String msg;
        if (hasWon) {
            msg = "You Won!";
        } else {
            msg = "Game Over!";
        }
        msg = msg + "Your Score is " + String.valueOf(score);
        d.drawText(10, d.getHeight() / 2, msg, 32);
    }
    @Override
    public boolean shouldStop() {
        return true;
    }
}
