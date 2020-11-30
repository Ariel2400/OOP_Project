//id:213214026.
package game;

import objects.Counter;
import objects.Sprite;
import geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * a GameComponents.ScoreTracker Class.
 * */
public class ScoreTracker implements Sprite {
    private Counter score;
    private Rectangle border;
    /**
     * creates a GameComponents.ScoreTracker object.
     * @param border the shape of the tracker.
     * @param score the score
     * */
    public ScoreTracker(Rectangle border, Counter score) {
        this.border = border;
        this.score = score;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        border.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText((int) border.getWidth() / 2, (int) border.getHeight() / 2 + 5,
                "Score: " + String.valueOf(score.getValue()), 10);
    }

    @Override
    public void timePassed() {

    }
    /**
     * adds the Tracker to a game.
     * @param gameLevel the game we want to track
     * */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
