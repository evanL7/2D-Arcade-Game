package StaticEntity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Display.Game;
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
    private float damage;

    private BufferedImage[][] animations; // 2d image array of the images for trap movements
    private int animationTick, animationIndex, animationSpeed = 100;

    /**
     * Constructs a new Trap.
     *
     * @param position The position of the trap.
     * @param damage   The amount of damage the trap inflicts.
     */
    public Trap(Position position, float damage) {
        // doesn't need despawnTimer as it would only despawn if collided with
        super(position);
        loadAnimations();
        this.damage = damage;
    }

    // Methods

    /**
     * Gets the amount of damage the trap inflicts.
     *
     * @return The amount of damage.
     */
    public float getDamage() {
        return damage;
    }

    // ANIMATION METHODS FOR TRAP
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(animations[animationIndex][0], position.getX(), position.getY(), Game.tileSize, 72,
                null);
    }

    public void update() {
        updateAnimationTick();
    }

    // creates the Image array for the movement animations
    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/assets/trap.png");

        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[2][1];
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(j * 32, i * 22, 32, 22);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Enemy did not successfully grab the sprite");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // updates the animation array during the game loop thread
    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= 2) {
                animationIndex = 0;
            }
        }
    }

}