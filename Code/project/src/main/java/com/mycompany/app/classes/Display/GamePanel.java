package com.mycompany.app.classes.Display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.mycompany.app.classes.Helpers.AnimationConstants;
import com.mycompany.app.classes.Helpers.KeyboardInputs;
import com.mycompany.app.classes.Helpers.AnimationConstants.*;

public class GamePanel extends JPanel {

    // Tile settings
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;

    // Grid size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    // Scaled screen size
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    private BufferedImage img;
    private BufferedImage[][] animations; // 2d image array of the images for player movements
    private int animationTick, animationIndex, animationSpeed = 35;
    private int playerAction = PlayerConstants.UP;

    private int xDelta = 0, yDelta = 0;
    private int playerSpeed = 2;
    private boolean moving = false;

    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.importImg();
        loadAnimations();
        this.setPanelSize();
        this.addKeyListener(new KeyboardInputs(this));
    }

    public void updateGame() {
        updateAnimationTick();
        updatePos();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(animations[playerAction][animationIndex], xDelta, yDelta, tileSize, 72, null);
        g2.dispose();
    }

    private void importImg() {
        // Source of player sprites:
        // https://axulart.itch.io/small-8-direction-characters
        InputStream is = getClass().getResourceAsStream("player_sprites.png");

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

    private void setPanelSize() {
        Dimension size = new Dimension(screenWidth, screenHeight);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setAction(int action) {
        this.playerAction = action;
        this.moving = true;

    }

    /**
     * Updates the player's position based on the currently pressed keys.
     * If the player is moving, it adjusts the position according to the keys being
     * pressed.
     */
    private void updatePos() {
        if (moving) {
            switch (playerAction) {
                case PlayerConstants.UP:
                    yDelta -= playerSpeed; // Move up
                    break;
                case PlayerConstants.LEFT:
                    xDelta -= playerSpeed; // Move left
                    break;
                case PlayerConstants.DOWN:
                    yDelta += playerSpeed; // Move down
                    break;
                case PlayerConstants.RIGHT:
                    xDelta += playerSpeed; // Move right
                    break;
            }
        }
    }
}