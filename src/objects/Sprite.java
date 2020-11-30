//id:213214026
package objects;

import biuoop.DrawSurface;
/**
 * BasicObjects.Sprite interface.
 * */
public interface Sprite {
    /**
     * draws the sprite on the surface.
     * @param d the surface
     * */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     * */
    void timePassed();
}