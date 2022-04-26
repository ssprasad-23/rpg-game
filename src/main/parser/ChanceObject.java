package main.parser;

public class ChanceObject {

    private int low;
    private int high;
    private String name;

    /**
     * Constructor for a main.parser.ChanceObject.
     * @param l The lower bound of the roll
     * @param h The upper bound of the roll
     * @param n The name of the object
     */
    public ChanceObject(int l, int h, String n) {
        low = l;
        high = h;
        name = n;
    }

    /**
     * Returns whether a specified roll is within the range of the bounds of the object.
     * @return low <= roll <= high
     */
    public boolean getInRange(int roll) {
        if (roll+1 >= low && roll+1 <= high)
            return true;
        return false;
    }

    /**
     * Returns the name of the object.
     * @return name
     */
    public String getName() { return name; }

    /**
     * Returns a printout of the main.parser.ChanceObject. Used purely for testing.
     * @return this as a String
     */
    public String toString() { return name + " (" + low + "-" + high + ")"; }

}
