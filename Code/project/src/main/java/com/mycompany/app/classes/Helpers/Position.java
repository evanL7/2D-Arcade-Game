package com.mycompany.app.classes.Helpers;

/**
 * The Position class represents a coordinate position in a two-dimensional
 * space.
 */
public class Position {

    private int x; // The x-coordinate of the position
    private int y; // The y-coordinate of the position

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
     * Sets the coordinates of the position.
     * 
     * @param x The new x-coordinate.
     * @param y The new y-coordinate.
     */
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
