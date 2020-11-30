//id:213214026.
package game;


import objects.Ball;
import objects.Block;
import objects.Counter;


/**
 * a listener that keeps track of the score.
 * */
 public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * creates a score tracking listener.
     * @param scoreCounter the counter
     * */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }
}
