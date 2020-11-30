//id:213214026
package objects;

/**
 * a simple counter class.
 * */
public class Counter {
    private int count;
    /**
     * creates a counter that starts with 0.
     * */
    public Counter() {
        this.count = 0;
    }
    /**
     * creates a counter that starts with a given number.
     * @param count the starting point
     * */
    public Counter(int count) {
        this.count = count;
    }
    /**
     * add number to the count.
     * @param number the number we add to the count
     * */
    public void increase(int number) {
        count += number;
    }
    /**
     * subtracts a number from the counter.
     * @param number the number we subtract from the count
     * */
    public void decrease(int number) {
        count -= number;
    }
    /**
     * @return the current count.
     * */
    public int getValue() {
        return this.count;
    }
}