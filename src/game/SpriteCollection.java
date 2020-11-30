//id:213214026.
package game;

import objects.Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * a collection of sprite objects.
 * */
public class SpriteCollection {
    private List<Sprite> spriteList;
    /**
     * creates an empty list of sprites.
     * */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }
    /**
     * adds a sprite to the collection.
     * @param s the sprite
     * */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }
    /**
     * notifies all sprites that time has passed.
     * */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<>(getSpriteList());
        for (Sprite sprite: sprites) {
            sprite.timePassed();
        }
    }
    /**
     * calls drawOn on all sprites.
     * @param d the surface upon we draw
     * */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new ArrayList<>(getSpriteList());
        for (Sprite sprite: sprites) {
            sprite.drawOn(d);
        }
    }
    /**
     * @return the sprite list.
     * */
    public List<Sprite> getSpriteList() {
        return spriteList;
    }
    /**
     * removes a sprite from the list.
     * @param s the sprite we want to remove
     * */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
}
