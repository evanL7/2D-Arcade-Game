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

import com.mycompany.app.classes.Helpers.KeyboardInputs;

public class GamePanel extends JPanel {

    // Screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;

    // Maze size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    // Scaled screen size
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    private int xDelta = 0, yDelta = 0;
    private BufferedImage img;
    private BufferedImage[][] animations; // 2d image array of the images for player movements
    private int animationTick, animationIndex, animationSpeed = 35;

    public GamePanel() {
        this.setBackground(Color.BLACK);

        importImg();
        loadAnimations();

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        // g2.drawImage(img.getSubimage(16 * 4, 24, 16, 24), xDelta, yDelta, tileSize,
        // 72, null);
        updateAnimationTick();
        g2.drawImage(animations[3][animationIndex], xDelta, yDelta, tileSize, 72, null);
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

    private void setPanelSize() {
        Dimension size = new Dimension(screenWidth, screenHeight);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public int getTileSize() {
        return tileSize;
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
            if (animationIndex >= 3) {
                animationIndex = 0;
            }
        }

    }
}
