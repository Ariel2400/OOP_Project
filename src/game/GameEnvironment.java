//id:213214026
package game;

import objects.Collidable;
import objects.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;
/**
 * the game enviroment.
 * a collection of collidables
 * */
public class GameEnvironment {
    private List<Collidable> collidables;
    /**
     * create an empty list of objects.
     * */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }
    /**
     * add a collidable to the environment.
     * @param c the collidable
     * */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * return the closest collision to the start of given line.
     * @param trajectory the given line
     * @return a collision info object containing the info about the closest collision
     * */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Double minDist = Double.MAX_VALUE; // a really big number.
        CollisionInfo info = null;
        List<Collidable> collidablesCopy = new ArrayList<>(this.collidables);
        for (Collidable c: collidablesCopy) {
            Point point = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (point != null && trajectory.start().distance(point) < minDist) {
                info = new CollisionInfo(point, c);
                minDist = trajectory.start().distance(point);
            }
        }
        return info;
    }
    /**
     * removes a collidable from the environment.
     * @param c the collidable we want to remove
     * */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}
