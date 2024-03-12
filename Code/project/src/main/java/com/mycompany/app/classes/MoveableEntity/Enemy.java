package com.mycompany.app.classes.MoveableEntity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.mycompany.app.classes.Display.Game;
import com.mycompany.app.classes.Helpers.AnimationConstants;
import com.mycompany.app.classes.Helpers.AnimationConstants.EnemyConstants;
import com.mycompany.app.classes.Helpers.Position;

// enemy sprite https://forums.rpgmakerweb.com/index.php?threads/whtdragons-animals-and-running-horses-now-with-more-dragons.53552/
// 64x57

public class Enemy extends MoveableEntity {
    // ATTRIBUTES
    private Vector<Position> pathToPlayer;

    private BufferedImage[][] animations; // 2d image array of the images for player movements
    private int enemyAction = EnemyConstants.DOWN;
    private int animationTick, animationIndex, animationSpeed = 35;

    // CONSTRUCTOR
    public Enemy(Position position) {
        super(position);
        loadAnimations();
        speed = 1;

    }

    public void update() {
        updateShortestPath();
        updateAnimationTick();
    }

    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(animations[animationIndex][enemyAction], position.getX(), position.getY(), Game.tileSize, 72, null);
    }

    // METHODS
    public void updateShortestPath() { // THIS WILL CHANGE
        moving = true;

    }

    // creates the Image array for the movement animations
    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("racoons.png");

        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[3][4];
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(j * 48, i * 48, 48, 48);
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
        if (moving && animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= AnimationConstants.SpriteEnemyAmount(enemyAction)) {
                animationIndex = 0;
            }
        }
    }

}
