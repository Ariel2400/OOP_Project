//id:213214026
package game;


import objects.Ball;
import objects.Block;
import objects.Counter;


/**
 * listener that's in charge of removing the blocks when hit.
 * */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    /**
     * creates a block remover Object.
     * @param gameLevel the game in which the blocks exist
     * @param remainingBlocks a counter of the remaining blocks
     * */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}