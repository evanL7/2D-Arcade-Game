package Helpers;

/**
 * The Position class represents a coordinate position in a two-dimensional
 * space.
 */
public class Position {

    private int x; // The x-coordinate of the position
    private int y; // The y-coordinate of the position

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
    /**
     * Gets the x-coordinate of the position.
     * 
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the position.
     * 
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the position.
     * 
     * @param x The new x-coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the position.
     * 
     * @param y The new y-coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

}
