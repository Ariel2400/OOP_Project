//id:213214026
package screens;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import objects.Block;
import objects.Sprite;
import objects.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Final Four Level.
 * */
public class FinalFour extends Background implements LevelInformation {

    private static final Color[] COLORS = {Color.gray, Color.red, Color.YELLOW, Color.GREEN, Color.white, Color.pink,
            Color.magenta};
    private static final double BLOCK_WIDTH = 52;
    private static final int BLOCK_HEIGHT = 25;


    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(new Velocity(-3, -5));
        list.add(new Velocity(0, -5));
        list.add(new Velocity(3, -5));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 60;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return this;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        int y = 100;
        for (Color value : COLORS) {
            for (int i = 0; i < 15; i++) {
                list.add(new Block(new Rectangle(new Point(10 + i * BLOCK_WIDTH, y), BLOCK_WIDTH, BLOCK_HEIGHT),
                        value));
            }
            y = y + BLOCK_HEIGHT;
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        drawClouds(d, 0);
        drawClouds(d, 500);
    }
    /**
     * draws the clouds.
     * @param d the draw surface.
     * @param start the starting x of the clouds
     * */
    private void drawClouds(DrawSurface d, int start) {
        for (int i = 0; i < 10; i++) {
            d.drawLine(start + 105 + i * 10, 400, start + 80 + i * 10, 600);
        }
        d.setColor(Color.lightGray);
        d.fillCircle(start + 100, 400, 23);
        d.fillCircle(start + 120, 420, 27);
        d.setColor(Color.gray);
        d.fillCircle(start + 140, 390, 29);
        d.setColor(Color.darkGray.brighter());
        d.fillCircle(start + 160, 420, 22);
        d.fillCircle(start + 180, 400, 32);
    }
}
