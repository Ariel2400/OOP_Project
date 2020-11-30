//id:213214026
package objects;

import game.GameLevel;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import geometry.Point;

/**
 * a player controlled paddle.
 * implements the collidable and sprite interfaces.
 * */
public class Paddle implements Collidable, Sprite {
    //constants that define the borders, the hit angles and velocity
    private static final int LEFT_BORDER = 10;
    private static final int RIGHT_BORDER = 790;
    private static final double BIG_LEFT = 300;
    private static final double SMALL_LEFT = 330;
    private static final double BIG_RIGHT = 60;
    private static final double SMALL_RIGHT = 30;
    //fields
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRectangle;
    private Color paddleColor;
    private int paddleSpeed;
    /**
     * creates a paddle.
     * @param rectangle the shape of paddle
     * @param keyboard the keyboard input
     * @param color the color of the rectangle
     * @param paddleSpeed the speed of the paddle
     * */
    public Paddle(Rectangle rectangle, Color color, biuoop.KeyboardSensor keyboard, int paddleSpeed) {
        this.paddleColor = color;
        this.paddleRectangle = rectangle;
        this.keyboard = keyboard;
        this.paddleSpeed = paddleSpeed;
    }
    /**
     * moves the paddle left.
     * */
    public void moveLeft() {
        if (this.getCollisionRectangle().getUpperLeft().getX() > LEFT_BORDER) {
            Point upperLeft = new Point(this.paddleRectangle.getUpperLeft().getX() - paddleSpeed,
                    this.paddleRectangle.getUpperLeft().getY());
            this.paddleRectangle = new Rectangle(upperLeft, getCollisionRectangle().getWidth(),
                    getCollisionRectangle().getHeight());
        }
    }
    /**
     * moves the paddle right.
     * */
    public void moveRight() {
        if (this.getCollisionRectangle().getUpperRight().getX() < RIGHT_BORDER) {
            Point upperLeft = new Point(this.paddleRectangle.getUpperLeft().getX() + paddleSpeed,
                    this.paddleRectangle.getUpperLeft().getY());
            this.paddleRectangle = new Rectangle(upperLeft, getCollisionRectangle().getWidth(),
                    getCollisionRectangle().getHeight());
        }
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        boolean isHit = this.paddleRectangle.getBottomLine().inBounds(collisionPoint)
                || this.paddleRectangle.getTopLine().inBounds(collisionPoint);
        double boundX = this.getCollisionRectangle().getUpperLeft().getX();
        double speed = currentVelocity.getSpeed();
        if (this.paddleRectangle.getLeftLine().inBounds(collisionPoint)
                || this.paddleRectangle.getRightLine().inBounds(collisionPoint)) {
            currentVelocity.invertX();
            return currentVelocity;
        }
        if (isHit) {
            double section = this.paddleRectangle.getWidth() / 5;
            if (collisionPoint.getX() < boundX + section) {
                currentVelocity.setAngle(BIG_LEFT);
            } else if (collisionPoint.getX() < boundX + 2 * section) {
                currentVelocity.setAngle(SMALL_LEFT);
            } else if (collisionPoint.getX() < boundX + 3 * section) {
                currentVelocity.invertY();
            } else if (collisionPoint.getX() < boundX + 4 * section) {
                currentVelocity.setAngle(SMALL_RIGHT);
            } else {
                currentVelocity.setAngle(BIG_RIGHT);
            }
        }
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.paddleColor);
        Point upperLeft = this.paddleRectangle.getUpperLeft();
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) this.paddleRectangle.getWidth(),
                (int) this.paddleRectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) this.paddleRectangle.getWidth(),
                (int) this.paddleRectangle.getHeight());
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * add the paddle to the game.
     * @param gameLevel the game
     * */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }
}
