package Display;

import java.awt.Graphics;

import MoveableEntity.Player;
public class Camera {
    private int xOffset, yOffset;
    private Player player;

    public Camera(Player player) {
        this.player = player;
        this.xOffset = 0;
        this.yOffset = 0;
    }

    public void update() {
        // Adjust camera position based on player's position
        xOffset = player.getPosition().getX() - (Game.screenWidth / 2);
        yOffset = player.getPosition().getY() - (Game.screenHeight / 2);

        // Ensure camera doesn't go out of bounds
        if (xOffset < 0) xOffset = 0;
        if (yOffset < 0) yOffset = 0;

        if (xOffset > Game.worldWidth - Game.screenWidth) xOffset = Game.worldWidth - Game.screenWidth;
        if (yOffset > Game.worldHeight - Game.screenHeight) yOffset = Game.worldHeight - Game.screenHeight;
    }

    public void translate(Graphics g) {
        // Translate graphics object to adjust for camera position
        g.translate(-xOffset, -yOffset);
    }

    public void reset(Graphics g) {
        // Reset graphics translation
        g.translate(xOffset, yOffset);
    }

    // Getter methods for xOffset and yOffset
    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }
}
