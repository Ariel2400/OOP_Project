//id: 213214026
package geometry;

import biuoop.DrawSurface;

/**
 * rectangle class, used for the block and poddle classes.
 * */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * creates a new rectangle.
     * @param upperLeft the upper left corner of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * creates a list of the intersection points of the rectangle with a given line.
     * @param line the line we check for intersections
     * @return a (possibly empty) list of intersection points
     * */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> pointList = new java.util.ArrayList<Point>();
        if (line.isIntersecting(getTopLine())) {
            pointList.add(line.intersectionWith(this.getTopLine()));
        }
        if (line.isIntersecting(getBottomLine())) {
            pointList.add(line.intersectionWith(this.getBottomLine()));
        }
        if (line.isIntersecting(getLeftLine())) {
            pointList.add(line.intersectionWith(this.getLeftLine()));
        }
        if (line.isIntersecting(getRightLine())) {
            pointList.add(line.intersectionWith(this.getRightLine()));
        }
        return pointList;
    }
    /**
     * @return the top edge.
     * */
    public Line getTopLine() {
        return new Line(this.getUpperLeft(), this.getUpperRight());
    }
    /**
     * @return the bottom edge.
     * */
    public Line getBottomLine() {
        return new Line(this.getLowerLeft(), this.getLowerRight());
    }
    /**
     * @return the right edge.
     * */
    public Line getRightLine() {
        return new Line(this.getUpperRight(), this.getLowerRight());
    }
    /**
     * @return the left edge.
     * */
    public Line getLeftLine() {
        return new Line(this.getUpperLeft(), this.getLowerLeft());
    }
    /**
     * @return the upper right vertex.
     * */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + getWidth(), this.upperLeft.getY());
    }
    /**
     * @return the lower left vertex.
     * */
    private Point getLowerLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + getHeight());
    }
    /**
     * @return the lower right vertex.
     * */
    private Point getLowerRight() {
        return new Point(this.upperLeft.getX() + getWidth(), this.upperLeft.getY() + getHeight());
    }
    /**
     * @return the width.
     * */
    public double getWidth() {
        return this.width;
    }
    /**
     * @return the height.
     * */
    public double getHeight() {
        return this.height;
    }
    /**
     * @return the upper left vertex.
     * */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * draws a rectangle on a drawSurface.
     * @param d the drawSurface
     * */
    public void drawOn(DrawSurface d) {
        d.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(), (int) getWidth(), (int) getHeight());
    }
}
