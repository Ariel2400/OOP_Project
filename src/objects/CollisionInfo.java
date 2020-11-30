//id:213214026
package objects;

import geometry.Point;

/**
 * class that holds the info about a collision.
 * */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * creates a info object.
     * @param collisionPoint where the collision occurred
     * @param collisionObject the object where the collision occurred.
     * */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * @return the object where the collision occurred.
     * */
    public Collidable getCollisionObject() {
        return collisionObject;
    }
    /**
     * @return where the collision occurred.
     * */
    public Point getCollisionPoint() {
        return collisionPoint;
    }
}
