//id:213214026
package game;


/**
 * controls the hit listeners.
 * */
public interface HitNotifier {
    /**
     * adds a listener.
     * @param hl the listener
     * */
    void addHitListener(HitListener hl);
    /**
     * removes a listener.
     * @param hl the listener
     * */
    void removeHitListener(HitListener hl);
}