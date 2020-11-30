//id:213214026
package screens;

import game.Animation;
import biuoop.DrawSurface;
/**
 * pause screen.
 * */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
