package Animation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

// adds animations for enities
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
    // param:
    // imageName: the image file path of the sprite
    // width: the width of the sprite
    // height: the height of the sprite
    // animationArrayWidth: the number of elements in the width of the 2d array
    // animationArrayHeight: the number of elements in the height of the 2d array
    public Animations(String imageName) {
        this.imageName = imageName;

    }

    /**
     * Renders the player on the screen.
     * 
     * @param g The graphics context used for rendering.
     */
    public void render(Graphics g, int action, int x, int y, int tileSize, int spriteSize) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(animations[action][animationIndex], x, y, tileSize, spriteSize, null);
    }

    // creates the Image array for the movement animations
    // param:
    // imageName: the image file path name
    // animationArrayWidth: the number of elements in the width of the 2d array
    // animationArrayHeight: the number of elements in the height of the 2d array
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

    public void loadImage() {
        try {
            InputStream is = getClass().getResourceAsStream(imageName);
            spriteImage = ImageIO.read(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // updates the animation array during the game loop thread
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
    public void setAnimationArrayHeight(int height) {
        animationArrayHeight = height;
    }

    public void setAnimationArrayWidth(int width) {
        animationArrayWidth = width;
    }

    public void setSpriteHeight(int height) {
        spriteHeight = height;
    }

    public void setSpriteWidth(int width) {
        spriteWidth = width;
    }

    public void setAnimationAmount(int amount) {
        animationAmount = amount;
    }

    public void setAnimationSpeed(int speed) {
        animationSpeed = speed;
    }

    public BufferedImage[][] getBufferedImages() {
        return animations;
    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public BufferedImage getImage() {
        return spriteImage;
    }
}
