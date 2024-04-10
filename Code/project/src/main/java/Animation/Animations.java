package Animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Animation.AnimationConstants.*;

// adds animations for enities
public abstract class Animations {
    // ATTRIBUTES
    // private BufferedImage img;
    private BufferedImage[][] animations; // 2d image array of the images for player movements

    // Tile settings
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;

    private int animationTick, animationIndex, animationSpeed = 35;
    private int playerAction = PlayerConstants.UP;

    // CONSTRUCTOR
    // param:
    // imageName: the image file path of the sprite
    // posX and posY: the starting x and y position of the entity
    // width: the width of the sprite
    // height: the height of the sprite
    // animationArrayWidth: the number of elements in the width of the 2d array
    // animationArrayHeight: the number of elements in the height of the 2d array
    public Animations(String imageName, int posX, int posY, int width, int height, int animationArrayWidth,
            int animationArrayHeight) {
        loadAnimations(imageName, animationArrayWidth, animationArrayHeight);

    }

    // params:
    // posX and posY: the starting x and y position of the entity
    // width: the width of the sprite
    // height: the height of the sprite
    public void render(Graphics g, int posX, int posY, int width, int height) {

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
                    animations[j][i] = img.getSubimage(j * 32, 24 + (i * 24), 16, 24);
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
    private void updateAnimationTick() {

        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= AnimationConstants.SpriteAmount(playerAction)) {
                animationIndex = 0;
            }
        }

    }

}
