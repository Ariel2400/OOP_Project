//id:213214026
package screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Animation;
/**
 * Decorator class for the stoppable animations.
 * */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed;
    private boolean stop;
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    /**
     * Animation Constructor.
     * @param animation the desired animation
     * @param key the key that stops the animation
     * @param sensor the keyboard
     * */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (!sensor.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
        if (isAlreadyPressed) {
            this.stop = false;
        } else {
            animation.doOneFrame(d);
            if (sensor.isPressed(key)) {
                this.stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
