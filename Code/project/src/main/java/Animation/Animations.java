package Animation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Manages animations for entities.
 */
public class Animations {
    // ATTRIBUTES
    private BufferedImage spriteImage;
    private BufferedImage[][] animations; // 2d image array of the images for player movements
    private int animationArrayHeight;
    private int animationArrayWidth;
    private int spriteHeight;
    private int spriteWidth;
    private int animationAmount;
    private String imageName;

    private int animationTick, animationIndex, animationSpeed = 35;

    // CONSTRUCTOR

    /**
     * Constructs an Animations object.
     * 
     * @param imageName The image file path of the sprite.
     */
    public Animations(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Renders the player on the screen.
     * 
     * @param g          The graphics context used for rendering.
     * @param action     The action representing the player's current movement.
     * @param x          The x-coordinate to render the player.
     * @param y          The y-coordinate to render the player.
     * @param tileSize   The size of a tile.
     * @param spriteSize The size of the sprite.
     */
    public void render(Graphics g, int action, int x, int y, int tileSize, int spriteSize) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(animations[action][animationIndex], x, y, tileSize, spriteSize, null);
    }

    /**
     * Loads the animations from the image file.
     */
    public void loadAnimations() {
        InputStream is = getClass().getResourceAsStream(imageName);

        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[animationArrayWidth][animationArrayHeight];
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(j * spriteWidth, i * spriteHeight, spriteWidth, spriteHeight);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads the sprite image.
     */
    public void loadImage() {
        try {
            InputStream is = getClass().getResourceAsStream(imageName);
            spriteImage = ImageIO.read(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the animation tick during the game loop thread.
     */
    public void updateAnimationTick() {

        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= animationAmount) {
                animationIndex = 0;
            }
        }

    }

    // SETTERS AND GETTERS
    /**
     * Sets the height of the animation array.
     * 
     * @param height The height of the animation array.
     */
    public void setAnimationArrayHeight(int height) {
        animationArrayHeight = height;
    }

    /**
     * Sets the width of the animation array.
     * 
     * @param width The width of the animation array.
     */
    public void setAnimationArrayWidth(int width) {
        animationArrayWidth = width;
    }

    /**
     * Sets the height of the sprite.
     * 
     * @param height The height of the sprite.
     */
    public void setSpriteHeight(int height) {
        spriteHeight = height;
    }

    /**
     * Sets the width of the sprite.
     * 
     * @param width The width of the sprite.
     */
    public void setSpriteWidth(int width) {
        spriteWidth = width;
    }

    /**
     * Sets the amount of animation frames.
     * 
     * @param amount The amount of animation frames.
     */
    public void setAnimationAmount(int amount) {
        animationAmount = amount;
    }

    /**
     * Sets the speed of the animation.
     * 
     * @param speed The speed of the animation.
     */
    public void setAnimationSpeed(int speed) {
        animationSpeed = speed;
    }

    /**
     * Gets the buffered images for the animations.
     * 
     * @return The buffered images for the animations.
     */
    public BufferedImage[][] getBufferedImages() {
        return animations;
    }

    /**
     * Gets the current animation index.
     * 
     * @return The current animation index.
     */
    public int getAnimationIndex() {
        return animationIndex;
    }

    /**
     * Gets the sprite image.
     * 
     * @return The sprite image.
     */
    public BufferedImage getImage() {
        return spriteImage;
    }

}
