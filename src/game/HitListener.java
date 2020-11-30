//id:213214026
package game;


import objects.Block;
import objects.Ball;
/**
 * a listener that acts when the block gets hit.
 * */
public interface HitListener {
    /**
     * acts when a ball hits a block.
     * @param hitter the ball
     * @param beingHit the block
     * */
    void hitEvent(Block beingHit, Ball hitter);
}