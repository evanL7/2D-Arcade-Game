package Gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * The Statemethods interface defines the methods that should be implemented by classes representing game states.
 * These methods are responsible for updating the state, drawing it on the screen, and handling keyboard input events.
 */
public interface Statemethods {

    /**
     * Updates the state of the game.
     */
    public void update();

    /**
     * Draws the state on the screen.
     *
     * @param g the Graphics object used for drawing
     */
    public void draw(Graphics g);

    /**
     * Handles the key press event.
     *
     * @param e the KeyEvent object representing the key press event
     */
    public void keyPressed(KeyEvent e);

    /**
     * Handles the key release event.
     *
     * @param e the KeyEvent object representing the key release event
     */
    public void keyReleased(KeyEvent e);
}
