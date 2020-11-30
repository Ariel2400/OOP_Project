//id:213214026
package game;

import objects.Ball;
import objects.Block;
import objects.Collidable;
import objects.Counter;
import objects.Paddle;
import objects.Sprite;
import objects.Velocity;
import geometry.Point;
import geometry.Rectangle;
import screens.KeyPressStoppableAnimation;
import screens.LevelInformation;
import screens.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;
/**
 * the game class.
 * packs all the other objects and interacts t
 * */
public class GameLevel implements Animation {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int BORDER_WIDTH = 10;
    private static final int BORDER_HEIGHT = GUI_HEIGHT - 2 * BORDER_WIDTH;
    private static final int FPS = 60;
    private static final double PADDLE_HEIGHT = 20;
    private static final Color PADDLE_COLOR = Color.ORANGE;
    private static final Color BORDER_COLOR = Color.GRAY;
    private static final int BALL_SIZE = 5;
    private static final Color BALL_COLOR = Color.white;


    private GameEnvironment environment;
    private SpriteCollection sprites;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter currentScore;
    private boolean running;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private LevelInformation information;
    private Paddle paddle;
    /**
     * constructs a level with given information.
     * @param information the level information
     * @param counter the current Score
     * @param gui the gui
     * */
    public GameLevel(LevelInformation information, Counter counter, GUI gui) {
        this.information = information;
        this.currentScore = counter;
        this.gui = gui;
    }
    /**
     * adds a collidable to the game environment.
     * @param c the item we want to add
     * */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * adds a sprite to the sprite collection.
     * @param s the item we want to add
     * */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * intializes the game.
     * creates the gui, the balls, the blocks and the paddle
     * */
    public void initialize() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(information.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(information.numberOfBalls());
        this.runner = new AnimationRunner(this.gui, FPS);
        this.keyboard = gui.getKeyboardSensor();
        addBackground();
        addBorders();
        addBlocks();
        addPaddle();
        addBalls();
        addScoreTracker();
    }
    /**
     * adds the background to the level.
     * */
    private void addBackground() {
        this.sprites.addSprite(information.getBackground());
    }

    /**
     * creates a scoreTracker and adds it to the game.
     * */
    private void addScoreTracker() {
        Rectangle topBoard = new Rectangle(new Point(0, 0), this.gui.getDrawSurface().getWidth(), 15);
        ScoreTracker tracker = new ScoreTracker(topBoard, currentScore);
        NameTracker nameTracker = new NameTracker(this.information.levelName(), topBoard);
        tracker.addToGame(this);
        nameTracker.addToGame(this);
    }


    /**
     * creates the blocks in an inverted right triangle pattern.
     * */
    private void addBlocks() {
        BlockRemover remover = new BlockRemover(this, remainingBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(currentScore);
        for (Block block: information.blocks()) {
            block.addHitListener(scoreTrackingListener);
            block.addHitListener(remover);
            block.addToGame(this);
        }
    }
    /**
     * adds a ball to the game.
     * */
    private void addBalls() {
        for (Velocity velocity: information.initialBallVelocities()) {
            Point ballStart = new Point(paddle.getCollisionRectangle().getWidth() / 2
                    + paddle.getCollisionRectangle().getUpperLeft().getX(),
                    paddle.getCollisionRectangle().getUpperLeft().getY() - 30);
            Ball ball = new Ball(ballStart, BALL_SIZE, BALL_COLOR, velocity, this.environment);
            ball.addToGame(this);
        }
    }
    /**
     * adds the paddle to the game.
     * */
    private void addPaddle() {
        double paddleStartX = (double) gui.getDrawSurface().getWidth() / 2 - (double) information.paddleWidth() / 2;
        Point paddleStart = new Point(paddleStartX, gui.getDrawSurface().getHeight() - PADDLE_HEIGHT - 10);
        Rectangle paddleRectangle = new Rectangle(paddleStart, information.paddleWidth(), PADDLE_HEIGHT);
        this.paddle = new Paddle(paddleRectangle, PADDLE_COLOR, gui.getKeyboardSensor(), information.paddleSpeed());
        paddle.addToGame(this);
    }
    /**
     * adds the borders of the gui.
     * */
    private void addBorders() {
        addLeftBorder();
        addRightBorder();
        addTopBorder();
        addBottomBorder();
    }
    /**
     * adds the bottom border.
     * */
    private void addBottomBorder() {
        BallRemover remover = new BallRemover(this, remainingBalls);
        Point bottomBorderStart  = new Point(0, gui.getDrawSurface().getHeight() + BALL_SIZE);
        Block bottomBorder = new Block(new Rectangle(bottomBorderStart, gui.getDrawSurface().getWidth(), BORDER_WIDTH),
                BORDER_COLOR);
        bottomBorder.addToGame(this);
        bottomBorder.addHitListener(remover);
    }
    /**
     * adds the top border.
     * */
    private void addTopBorder() {
        Block topBorder = new Block(new Rectangle(new Point(0, 0), gui.getDrawSurface().getWidth(),
                2 * BORDER_WIDTH), BORDER_COLOR);
        topBorder.addToGame(this);
    }
    /**
     * adds the right border.
     * */
    private void addRightBorder() {
        Point rightBorderStart = new Point(gui.getDrawSurface().getWidth() - BORDER_WIDTH, BORDER_WIDTH);
        Rectangle rightBorderShape = new Rectangle(rightBorderStart, BORDER_WIDTH, BORDER_HEIGHT + BORDER_WIDTH);
        Block rightBorder = new Block(rightBorderShape, BORDER_COLOR);
        rightBorder.addToGame(this);
    }
    /**
     * adds the left border.
     * */
    private void addLeftBorder() {
        Point leftBorderStart = new Point(0, BORDER_WIDTH);
        Rectangle leftBorderShape = new Rectangle(leftBorderStart, BORDER_WIDTH, BORDER_HEIGHT + BORDER_WIDTH);
        Block leftBorder = new Block(leftBorderShape, BORDER_COLOR);
        leftBorder.addToGame(this);
    }

    /**
     * runs the game-starts the animation.
     * */
    public void run() {
        this.running = true;
        this.runner.run(this);
    }
    /**
     * removes a collidable from the game environment.
     * @param c the collidable we want to remove
     * */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * removes a sprite from the sprite collection.
     * @param s the sprite we want to remove
     * */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    @Override
    public boolean shouldStop() {
       return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (remainingBlocks.getValue() == 0) {
            currentScore.increase(100);
            this.running = false;
        }
        if (remainingBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("P") || this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }
    /**
     * @return the number of remaining blocks.
     * */
    public int getRemainingBlocks() {
        return remainingBlocks.getValue();
    }
    /**
     * @return the number of remaining balls.
     * */
    public int getRemainingBalls() {
        return remainingBalls.getValue();
    }
}

