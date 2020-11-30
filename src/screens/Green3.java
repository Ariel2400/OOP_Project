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
 * Green3 Level.
 * */
public class Green3 extends Background implements LevelInformation {
    private static final Color[] COLORS = {Color.gray, Color.RED, Color.YELLOW, Color.blue, Color.white};
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(new Velocity(4, -5));
        list.add(new Velocity(-4, -5));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return this;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        int y = 100;
        for (int i = 0; i < COLORS.length; i++) {
            for (int j = i + 5; j < 15; j++) {
                list.add(new Block(new Rectangle(new Point((j * 50 + 35), y), BLOCK_WIDTH, BLOCK_HEIGHT),
                        COLORS[i]));
            }
            y += BLOCK_HEIGHT;
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.green);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.darkGray);
        d.fillRectangle(110, 200, 10, 200);
        d.setColor(Color.orange.brighter());
        d.fillCircle(115, 200, 12);
        d.setColor(Color.red);
        d.fillCircle(115, 200, 8);
        d.setColor(Color.WHITE);
        d.fillCircle(115, 200, 3);
        d.setColor(Color.darkGray);
        d.fillRectangle(100, 400, 30, 200);
        d.setColor(Color.darkGray.darker());
        d.fillRectangle(65, 450, 100, 200);
        int xStart = 75;
        int yStart = 460;
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(xStart + j * 18, yStart + i * 32, 10, 25);
            }
        }
    }
}
