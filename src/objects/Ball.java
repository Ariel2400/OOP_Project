//id:213214026
package objects;
import game.GameEnvironment;
import geometry.Point;
import geometry.Line;
import biuoop.DrawSurface;
import java.awt.Color;
import game.GameLevel;
/**
 * basic ball class.
 * */
public class Ball implements Sprite {
    //fields
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;
    private GameEnvironment environment;
    /**
     * constructor with all the parameters.
     * @param color the color
     * @param r the radius
     * @param center the center
     * @param velocity the velocity
     * @param environment the game environment
     * */
    public Ball(Point center, int r, Color color, Velocity velocity, GameEnvironment environment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = velocity;
        this.environment = environment;
    }
    /**
     * Query that returns the x of the center.
     * @return the x coordinate of the center
     * */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * Query that returns the y of the center.
     * @return the y coordinate of the center
     * */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * Query that returns the radius.
     * @return the radius
     * */
    public int getSize() {
        return this.r;
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }
    /**
     * updates the velocity of the ball.
     * @param v the new velocity
     * */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * query that returns the velocity of the ball.
     * @return the velociity
     * */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * returns the game environment.
     * @return the game environment
     * */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }
    /**
     * returns the center point.
     * @return the center
     * */
    public Point getCenter() {
        return center;
    }
    /**
     * move one step with respect to collisions.
     * */
    public void moveOneStep() {
       Line trajectory = new Line(getCenter(), getVelocity().applyToPoint(getCenter()));
       if (this.getEnvironment().getClosestCollision(trajectory) == null) {
           this.center = getVelocity().applyToPoint(this.center);
       } else {
           CollisionInfo colInfo = this.getEnvironment().getClosestCollision(trajectory);
           this.center = new Line(this.center, colInfo.getCollisionPoint()).middle();
           this.setVelocity(colInfo.getCollisionObject().hit(this, colInfo.getCollisionPoint(), this.velocity));
       }
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }
    /**
     * adds this to a game.
     * @param gameLevel the game
     * */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
    /**
     * removes the ball from a game.
     * @param g the game
     * */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
