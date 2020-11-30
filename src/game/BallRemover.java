//id:213214026
package game;


import objects.Ball;
import objects.Block;
import objects.Counter;

/**
 * removes the blocks from the game in case of being hit.
 * */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;
    /**
     * creates a ball remover object.
     * @param gameLevel the game that contains the balls
     * @param counter counts the balls
     * */
    public BallRemover(GameLevel gameLevel, Counter counter) {
        this.gameLevel = gameLevel;
        this.remainingBalls = counter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(1);
    }
}
