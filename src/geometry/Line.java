//id: 213214026
package geometry;

/**
 * a simple line class.
 * */
public class Line {

    //fields
    private Point start;
    private Point end;
    /**
     * Line constructor from two points.
     * @param start the start point
     * @param end the end point
     * */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Line constructor from four coordinates.
     * the constructor packs the coordinates in two points.
     * @param x1 the x coordinate of the start
     * @param y1 the y coordinate of the start
     * @param x2 the x coordinate of the end
     * @param y2 the y coordinate of the end
     * */
    public Line(double x1, double y1, double x2, double y2) {
        //packs two first coordinates in start point
        Point newStart = new Point(x1, y1);
        //packs two last coordinates in end point
        Point newEnd = new Point(x2, y2);
        this.start = newStart;
        this.end = newEnd;
    }

    /**
     * returns the length of the line.
     * @return the length of the line
     * */
    public double length() {
        return start.distance(end);
    }

    /**
     * calculates the mid-point of the line.
     * @return the mid-point of the line
     * */
    public Point middle() {
        double xMid = (this.start.getX() + this.end.getX()) / 2;
        double yMid = (this.start.getY() + this.end.getY()) / 2;
        return new Point(xMid, yMid);
    }

    /**
     * query that returns start point.
     * @return the start point of the line
     * */
    public Point start() {
        return this.start;
    }

    /**
     * query that return end point.
     * @return the end point of the line
     * */
    public Point end() {
        return this.end;
    }

    /**
     * calculates the slope of the line.
     * @return the slope of the line, null if line is vertical
     * */
    public Double slope() {
        if (this.start.getX() == this.end.getX()) {
            return null;
        }
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * checks if given point is between the max/min coordinates.
     * it helps us determine if the point is in the line
     * @param p given point
     * @return true if the point is out of the line bounds, else false
     * */
    public boolean inBounds(Point p) {
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minX = Math.min(this.start.getX(), this.end.getX());
        double maxY = Math.max(this.start.getY(), this.end.getY());
        double minY = Math.min(this.start.getY(), this.end.getY());
        return p.getX() <= maxX && p.getX() >= minX && p.getY() <= maxY && p.getY() >= minY;
    }
    /**
     * checks if two lines intersect.
     * @param other the other line we check for
     * @return true if lines intersect, else false
     * */
    public boolean isIntersecting(Line other) {
        if (this.slope() == other.slope()) {
            return false;
       }
        Point genIntrsctn = this.generalIntersection(other);
        return this.inBounds(genIntrsctn) && other.inBounds(genIntrsctn);
    }

    /**
     * calculates the intersection of th line segments.
     * @param other the given line
     * @return intersection point, null if they don't intersect
     * */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        } else {
            return this.generalIntersection(other);
        }
    }
    /**
     * calculates the intersection point of two lines.
     * note that here we address the line, not the segments
     * @param other the given line
     * @return the intersection point, null if the lines don't intersect
     * */
    public Point generalIntersection(Line other) {
        if (this.slope() == null) {
            double y = other.slope() * (this.start.getX() - other.start().getX()) + other.start().getY();
            return new Point(this.start.getX(), y);
        }
        if (other.slope() == null) {
            double y = this.slope() * (other.start.getX() - this.start().getX()) + this.start().getY();
            return new Point(other.start.getX(), y);
        }
        double xNumerator = (this.slope() * this.start.getX() - other.slope() * other.start.getX()
                + other.start().getY() - this.start.getY());
        double x = xNumerator / (this.slope() - other.slope());
        double yNumerator = (other.slope() * this.slope() * (this.start.getX() - other.start().getX()) + this.slope()
                * other.start().getY() - other.slope() * this.start.getY());
        double y = yNumerator / (this.slope() - other.slope());
        return new Point(x, y);
    }

    /**
     * checks if given line is equal to the line.
     * @param other the given line
     * @return true if lines are equal, else false
     * */
    public boolean equals(Line other) {
        return this.start == other.start() && this.end == other.end();
    }
    /**
     * checks the closest intersection with a given rectangle.
     * @param rect the given rectangle
     * @return the closest intersection point to the start
     * */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> pointList = rect.intersectionPoints(this);
        Point minP = null;
        double minDistance = -1;
        for (Point point : pointList) {
            if (minDistance == -1) {
                minP = point;
                minDistance = start().distance(point);
            } else if (start().distance(point) < minDistance) {
                minP = point;
                minDistance = start().distance(point);
            }
        }
        return minP;
    }

}