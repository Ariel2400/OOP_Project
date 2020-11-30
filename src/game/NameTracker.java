//id:213214026
package game;

import biuoop.DrawSurface;
import geometry.Rectangle;
import objects.Sprite;

import java.awt.Color;
/**
 * keeps track of the name.
 * */
public class NameTracker implements Sprite {
    private String name;
    private Rectangle border;
    /**
     * creates a name tracker.
     * @param border the border of the name tracker
     * @param name the name
     * */
    public NameTracker(String name, Rectangle border) {
        this.name = name;
        this.border = border;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((int) (3 * border.getWidth() / 4), (int) border.getHeight() / 2 + 5,
                "Level Name:  " + this.name, 10);
    }

    @Override
    public void timePassed() {

    }
    /**
     * adds the tracker to the level.
     * @param level the level
     * */
    public void addToGame(GameLevel level) {
        level.addSprite(this);
    }
}
