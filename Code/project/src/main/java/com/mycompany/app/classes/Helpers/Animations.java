package com.mycompany.app.classes.Helpers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.mycompany.app.classes.Helpers.AnimationConstants.*;

// adds animations for enities
public class Animations extends JPanel {
    // ATTRIBUTES
    private BufferedImage img;
    private BufferedImage[][] animations; // 2d image array of the images for player movements

    // Tile settings
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;

    private int animationTick, animationIndex, animationSpeed = 35;
    private int playerAction = PlayerConstants.UP;

    private int xDelta = 0, yDelta = 0;
    private int playerSpeed = 2;
    private boolean moving = false;

    // CONSTRUCTOR
    // param: takes the image of the sprites
    public Animations(BufferedImage img) {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        updateAnimationTick();

        updatePos();

        g2.drawImage(animations[playerAction][animationIndex], xDelta, yDelta, tileSize, 72, null);
        g2.dispose();
    }

    private void importImg(String imageName) {
        // Source of player sprites:
        // https://axulart.itch.io/small-8-direction-characters
        InputStream is = getClass().getResourceAsStream(imageName);

        try {
            img = ImageIO.read(is);
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

    // creates the Image array for the movement animations
    private void loadAnimations() {
        animations = new BufferedImage[4][3];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(j * 32, 24 + (i * 24), 16, 24);
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

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setAction(int action) {
        this.playerAction = action;
        moving = true;

    }

    /**
     * Updates the player's position based on the currently pressed keys.
     * If the player is moving, it adjusts the position according to the keys being
     * pressed.
     * If diagonal movement is detected, it normalizes the speed to maintain
     * consistent movement.
     */
    private void updatePos() {
        if (moving) {
            if (KeyboardInputs.isUpPressed && KeyboardInputs.isRightPressed) {
                xDelta += playerSpeed / Math.sqrt(2); // Move diagonally up-right
                yDelta -= playerSpeed / Math.sqrt(2);
            } else if (KeyboardInputs.isUpPressed && KeyboardInputs.isLeftPressed) {
                xDelta -= playerSpeed / Math.sqrt(2); // Move diagonally up-left
                yDelta -= playerSpeed / Math.sqrt(2);
            } else if (KeyboardInputs.isDownPressed && KeyboardInputs.isRightPressed) {
                xDelta += playerSpeed / Math.sqrt(2); // Move diagonally down-right
                yDelta += playerSpeed / Math.sqrt(2);
            } else if (KeyboardInputs.isDownPressed && KeyboardInputs.isLeftPressed) {
                xDelta -= playerSpeed / Math.sqrt(2); // Move diagonally down-left
                yDelta += playerSpeed / Math.sqrt(2);
            } else {
                if (KeyboardInputs.isUpPressed) {
                    yDelta -= playerSpeed; // Move up
                }
                if (KeyboardInputs.isDownPressed) {
                    yDelta += playerSpeed; // Move down
                }
                if (KeyboardInputs.isLeftPressed) {
                    xDelta -= playerSpeed; // Move left
                }
                if (KeyboardInputs.isRightPressed) {
                    xDelta += playerSpeed; // Move right
                }
            }
        }
    }

}
