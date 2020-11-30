//id:213214026.
package game;

import biuoop.DrawSurface;
/**
 * Animation interface.
 * */
public interface Animation {
    /**
     * draw one frame.
     * @param d the draw surface
     * */
    void doOneFrame(DrawSurface d);
    /**
     * @return if the animation should stop.
     * */
    boolean shouldStop();
}
