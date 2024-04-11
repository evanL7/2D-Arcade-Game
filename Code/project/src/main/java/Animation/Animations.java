package Animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

// adds animations for enities
public abstract class Animations {
    // ATTRIBUTES
    // private BufferedImage img;
    private BufferedImage[][] animations; // 2d image array of the images for player movements
    private int animationArrayHeight;
    private int animationArrayWidth;
    private int spriteHeight;
    private int spriteWidth;
    private int animationAmount;

    private int animationTick, animationIndex, animationSpeed = 35;

    // CONSTRUCTOR
    // param:
    // imageName: the image file path of the sprite
    // width: the width of the sprite
    // height: the height of the sprite
    // animationArrayWidth: the number of elements in the width of the 2d array
    // animationArrayHeight: the number of elements in the height of the 2d array
    public Animations(String imageName) {
        loadAnimations(imageName, animationArrayWidth, animationArrayHeight);

    }

    // creates the Image array for the movement animations
    // param:
    // imageName: the image file path name
    // animationArrayWidth: the number of elements in the width of the 2d array
    // animationArrayHeight: the number of elements in the height of the 2d array
    private void loadAnimations(String imageName, int animationArrayWidth, int animationArrayHeight) {
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

    // updates the animation array during the game loop thread
    private void updateAnimationTick(int animationAmount) { // change the variable?

        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= animationAmount) {
                animationIndex = 0;
            }
        }

    }

}
