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
 * Wide easy level.
 * */
public class WideEasy extends Background implements LevelInformation {

    private static final int NUM_OF_BALLS = 10;
    private static final int PADDLE_SPEED = 5;
    private static final int PADDLE_WIDTH = 700;
    private static final double BLOCK_X = 10;
    private static final double BLOCK_WIDTH = 52;
    private static final double BLOCK_Y = 250;
    private static final double BLOCK_HEIGHT = 20;
    private static final Color[] COLORS_LEFT = {Color.RED, Color.orange, Color.YELLOW};
    private static final Color[] COLORS_RIGHT = {Color.blue, Color.PINK, Color.CYAN};
    private static final int CENTER_Y = 100;
    private static final int CENTER_X = 100;


    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int yVelocity = -5;
        for (int i  = 1; i < 6; i++) {
            list.add(new Velocity(0.5 * i, yVelocity));
        }
        for (int  i = 1; i < 6; i++) {
            list.add(new Velocity(-0.5 * i, yVelocity));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return this;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            for (int i = 2 * j; i < 2 * j + 2; i++) {
                list.add(new Block(
                        new Rectangle(new Point(BLOCK_X + i * BLOCK_WIDTH, BLOCK_Y), BLOCK_WIDTH, BLOCK_HEIGHT),
                        COLORS_LEFT[j]));
            }
        }
        for (int i = 6; i < 9; i++) {
            list.add(new Block(
                    new Rectangle(new Point(BLOCK_X + i * BLOCK_WIDTH, BLOCK_Y), BLOCK_WIDTH, BLOCK_HEIGHT),
                    Color.GREEN));
        }
        for (int j = 0; j < 3; j++) {
            for (int i = 2 * j; i < 2 * j + 2; i++) {
                list.add(new Block(
                        new Rectangle(new Point(BLOCK_X + (i + 9) * BLOCK_WIDTH, BLOCK_Y), BLOCK_WIDTH, BLOCK_HEIGHT),
                        COLORS_RIGHT[j]));
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        Color c = new Color(255, 213, 5);
        d.setColor(c.brighter().brighter());
        for (int i = 10; i < 790; i = i + 20) {
            d.drawLine(CENTER_X, CENTER_Y, i, (int) (BLOCK_Y - 1));
        }
        d.setColor(c.brighter().brighter());
        d.fillCircle(CENTER_X, CENTER_Y, 60);
        d.setColor(c.brighter());
        d.fillCircle(CENTER_X, CENTER_Y, 50);
        d.setColor(c);
        d.fillCircle(CENTER_X, CENTER_Y, 40);

    }
}
