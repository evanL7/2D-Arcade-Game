package Display;

import java.awt.Graphics;
import MoveableEntity.Player;

public class Camera {

    private int xOffset, yOffset;
    private Player player;
    private GameSettings gameSettings;

    /**
     * Constructs a Camera object with the specified player.
     *
     * @param player the player object to follow
     */
    public Camera(Player player) {
        this.player = player;
        this.xOffset = 0;
        this.yOffset = 0;
        this.gameSettings = new GameSettings();
    }
    
    /**
     * Updates the camera position based on the player's position.
     * Ensures that the camera stays within the bounds of the game world.
     */
    public void update() {
        // Adjust camera position based on player's position
        xOffset = player.getPosition().getX() - (gameSettings.getScreenWidth() / 2);
        yOffset = player.getPosition().getY() - (gameSettings.getScreenHeight() / 2);

        // Ensure camera doesn't go out of bounds
        if (xOffset < 0) xOffset = 0;
        if (yOffset < 0) yOffset = 0;

        if (xOffset > gameSettings.getWorldWidth() - gameSettings.getScreenWidth())
            xOffset = gameSettings.getWorldWidth() - gameSettings.getScreenWidth();
        if (yOffset > gameSettings.getWorldHeight() - gameSettings.getScreenHeight())
            yOffset = gameSettings.getWorldHeight() - gameSettings.getScreenHeight();
    }

    /**
     * Translates the graphics object to adjust for the camera position.
     *
     * @param g the graphics object to translate
     */
    public void translate(Graphics g) {
        // Translate graphics object to adjust for camera position
        g.translate(-xOffset, -yOffset);
    }

    /**
     * Resets the graphics translation to the original position.
     *
     * @param g the graphics object to reset
     */
    public void reset(Graphics g) {
        // Reset graphics translation
        g.translate(xOffset, yOffset);
    }
    
    /**
     * Gets the horizontal offset of the camera.
     *
     * @return the horizontal offset
     */
    // Getter methods for xOffset and yOffset
    public int getXOffset() {
        return xOffset;
    }

    /**
     * Gets the vertical offset of the camera.
     *
     * @return the vertical offset
     */
    public int getYOffset() {
        return yOffset;
    }
}
