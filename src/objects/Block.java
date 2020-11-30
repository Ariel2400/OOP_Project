//id:213214026
package objects;

import game.GameLevel;
import game.HitListener;
import game.HitNotifier;
import geometry.Rectangle;
import geometry.Point;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * block class.
 * implements the collidable and sprite interfaces
 * */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle blockShape;
    private Color blockColor;
    private List<HitListener> hitListeners;
    /**
     * @param blockColor the color of the block.
     * @param paddleRectangle the shape of the block.
     * */
    public Block(Rectangle paddleRectangle, Color blockColor) {
        this.blockShape = paddleRectangle;
        this.blockColor = blockColor;
        this.hitListeners = new ArrayList<>();
    }
    /**
     * notifies all the listeners that the block has been hit.
     * @param hitter the ball that hit the ball
     * */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.blockShape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        if (this.blockShape.getLeftLine().inBounds(collisionPoint)
                || this.blockShape.getRightLine().inBounds(collisionPoint)) {
            currentVelocity.invertX();
        }
        if (this.blockShape.getBottomLine().inBounds(collisionPoint)
                || this.blockShape.getTopLine().inBounds(collisionPoint)) {
            currentVelocity.invertY();
        }
        return currentVelocity;
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.blockColor);
        surface.fillRectangle((int) this.blockShape.getUpperLeft().getX(), (int) this.blockShape.getUpperLeft().getY(),
                (int) this.blockShape.getWidth(), (int) this.blockShape.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.blockShape.getUpperLeft().getX(), (int) this.blockShape.getUpperLeft().getY(),
                (int) this.blockShape.getWidth(), (int) this.blockShape.getHeight());
    }

    @Override
    public void timePassed() {

    }
    /**
     * add this block to a game.
     * @param gameLevel the game
     * */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }
    /**
     * removes the block from the game.
     * @param gameLevel the game
     * */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
