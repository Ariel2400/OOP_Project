//id: 213214026
package geometry;

/**
 * simple point class.
 * */
public class Point {
    private double x;
    private double y;
    /**
     * constructor with two coordinates.
     * @param x x coordinate
     * @param y y coordinate
     * */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculates the distance between two points.
     * @param other the other given point
     * @return the distance
     * */
    public Double distance(Point other) {
        if (other == null || this == null) {
            return null;
        }
        double d;
        //we use the formula based on pythagoras's theorem
        double xDelta = Math.pow(this.x - other.getX(), 2);
        double yDelta = Math.pow(this.y - other.getY(), 2);
        d = Math.sqrt(xDelta + yDelta);
        return d;
    }

    /**
     * comparison check between this and another point.
     * @param other the other point
     * @return true if they have the same x and y, else fasle
     * */
    public boolean equals(Point other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * query that returns x.
     * @return x coordinate
     * */
    public double getX() {
        return this.x;
    }
    /**
     * query that returns y.
     * @return y coordinate
     * */
    public double getY() {
        return this.y;
    }
}
