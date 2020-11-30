//id:213214026
package screens;

import objects.Block;
import objects.Sprite;
import objects.Velocity;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Direct-hit level.
 * */
public class DirectHit extends Background implements LevelInformation {
    private static final int NUM_OF_BALLS = 1;
    private static final int PADDLE_WIDTH = 25;
    private static final int PADDLE_SPEED = 5;
    private static final double BLOCK_WIDTH = 50.0;
    private static final double BLOCK_HEIGHT = 50.0;
    private static final double BLOCK_X = 375;
    private static final double BLOCK_Y = 75;
    private static final int CENTER_X = 400;
    private static final int CENTER_Y = 100;
    private static final int INIT_RADIUS = 40;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(0, -5));
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return this;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        list.add(new Block(new Rectangle(new Point(BLOCK_X, BLOCK_Y), BLOCK_WIDTH, BLOCK_HEIGHT), Color.RED));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        d.drawCircle(CENTER_X, CENTER_Y, INIT_RADIUS);
        d.drawCircle(CENTER_X, CENTER_Y, INIT_RADIUS + 10);
        d.drawCircle(CENTER_X, CENTER_Y, INIT_RADIUS + 2 * 10);
        d.drawLine(CENTER_X, CENTER_Y, CENTER_X, CENTER_Y + 80);
        d.drawLine(CENTER_X, CENTER_Y, CENTER_X, CENTER_Y - 80);
        d.drawLine(CENTER_X, CENTER_Y, CENTER_X - 80, CENTER_Y);
        d.drawLine(CENTER_X, CENTER_Y, CENTER_X + 80, CENTER_Y);
    }
}
