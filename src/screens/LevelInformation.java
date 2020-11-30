//id:213214026
package screens;

import objects.Block;
import objects.Sprite;
import objects.Velocity;

import java.util.List;
/**
 * LevelInformation Interface.
 * holds all the relevant info about each level
 * */
public interface LevelInformation {
    /**
     * @return the number os balls.
     * */
    int numberOfBalls();
    /**
     * @return a list of the initial velocities.
     * */
    List<Velocity> initialBallVelocities();
    /**
     * @return the paddle speed.
     * */
    int paddleSpeed();
    /**
     * @return the paddle width
     * */
    int paddleWidth();
    /**
     * @return the level name
     * */
    String levelName();
    /**
     * @return a background
     * */
    Sprite getBackground();
    /**
     * @return the list of blocks to remove.
     * */
    List<Block> blocks();
    /**
     * @return number of blocks to remove.
     * */
    int numberOfBlocksToRemove();
}
