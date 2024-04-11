package StaticEntity;

import java.awt.*;
import java.awt.image.BufferedImage;
import Animation.Animations;
import Helpers.Position;

// trap sprite from https://bdragon1727.itch.io/free-trap-platformer
// width:32, height: 22

/**
 * Represents a trap in the game.
 *
 * <p>
 * Traps are static entities that cause damage upon collision.
 * <p>
 * Traps will only despawn if a player collides with them
 */
public class Trap extends StaticEntity {

    /** The amount of damage the trap inflicts. */
    private double damage;

    private BufferedImage[][] trapAnimations; // 2d image array of the images for trap movements
    private Animations animation;

    /**
     * Constructs a new Trap.
     *
     * @param position The position of the trap.
     */
    public Trap(Position position) {
        // doesn't need despawnTimer as it would only despawn if collided with
        super(position);
        damage = 1; // default value of 1

        animation = new Animations("/assets/trap.png");
        animation.setAnimationAmount(2);
        animation.setAnimationArrayHeight(1);
        animation.setAnimationArrayWidth(2);
        animation.setSpriteHeight(22);
        animation.setSpriteWidth(32);
        animation.setAnimationSpeed(150);

        animation.loadAnimations();
        animation.loadImage();
        trapAnimations = animation.getBufferedImages();
    }

    // Methods

    /**
     * Gets the amount of damage the trap inflicts.
     *
     * @return The amount of damage.
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Retrieves the sprite image associated with the trap entity.
     * 
     * @return The sprite image associated with the trap entity.
     */
    @Override
    public Image getSprite() {
        return animation.getImage();
    }

    /**
     * Renders the trap entity on the graphics context.
     * 
     * @param g The graphics context used for rendering.
     */
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(trapAnimations[animation.getAnimationIndex()][0], position.getX(), position.getY(),
                gameSettings.getTileSize() + 2,
                30,
                null);
    }

    /**
     * Updates the animation of the trap entity.
     */
    public void update() {
        animation.updateAnimationTick();
    }

}