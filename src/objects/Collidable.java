//id:213214026
package objects;

import geometry.Point;
import geometry.Rectangle;
/**
 * a collidable class.
 * */
public interface Collidable {
    /**
     * @return the shape of the collidable.
     * */
    Rectangle getCollisionRectangle();

    /**
     * notifies the object a hit has occurred.
     * @return updated velocity
     * @param collisionPoint the point where the hit occurred
     * @param currentVelocity original velocity
     * @param hitter the ball with which the collidable was hit.
     * */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
