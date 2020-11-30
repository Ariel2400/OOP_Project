//id:213214026
package objects;

import geometry.Point;

/**
 * basic velocity class.
 * */
public class Velocity {
    //fields
    private double dx;
    private double dy;
    /**
     * constructor with two parameters.
     * @param dx the horizontal velocity
     * @param dy the vertical velocity
     * */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * applies the velocity to a given point.
     * @return the point after one step with this velocity
     * @param p the point we want to move
     * */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
    /**
     * query that returns the horizontal velocity.
     * @return the horizontal velocity
     * */
    public double getDx() {
        return this.dx;
    }
    /**
     * query that returns the vertical velocity.
     * @return the vertical velocity
     * */
    public double getDy() {
        return dy;
    }
    /**
     * changes the direction of the horizontal velocity.
     * */
    public void invertX() {
        this.dx = -this.dx;
    }
    /**
     * changes the direction of the vertical velocity.
     * */
    public void invertY() {
        this.dy = -this.dy;
    }
    /**
     * packs the angle and the speed into a velocity.
     * @param angle the angle of the vector
     * @param speed the size of the vector
     * @return the velocity which has this angle and speed
     * */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * @return  the speed(size of vector).
     * */
    public double getSpeed() {
        return Math.sqrt(getDx() * getDx() + getDy() * getDy());
    }
    /**
     * sets the vector to a desires angle.
     * @param angle the angle
     * */
    public void setAngle(double angle) {
        this.dx = Velocity.fromAngleAndSpeed(angle, this.getSpeed()).getDx();
        this.dy = Velocity.fromAngleAndSpeed(angle, this.getSpeed()).getDy();
    }
}
