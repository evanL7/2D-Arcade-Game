package MoveableEntity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.imageio.ImageIO;

import Display.Game;
import Helpers.AnimationConstants;
import Helpers.Position;
import Helpers.AnimationConstants.EnemyConstants;

// enemy sprite https://forums.rpgmakerweb.com/index.php?threads/whtdragons-animals-and-running-horses-now-with-more-dragons.53552/
// 48x48

//181x181

public class Enemy extends MoveableEntity {
    // ATTRIBUTES
    private Vector<Position> pathToPlayer;

    private BufferedImage[][] animations; // 2d image array of the images for player movements
    private int enemyAction = EnemyConstants.DOWN;
    private int animationTick, animationIndex, animationSpeed = 45;

    // CONSTRUCTOR
    public Enemy(Position position) {
        super(position);
        loadAnimations();
        speed = 1;

    }

    public void updateShortestPath() { // THIS WILL CHANGE
        moving = true;

    }

    public void AStar() { // using A* algorithm for enemy to player calculations

    }

    // ANIMATION METHODS
    public void update() {
        updateShortestPath();
        updateAnimationTick();
    }

    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(animations[animationIndex][enemyAction], position.getX(), position.getY(), Game.tileSize + 2, 72,
                null);
    }

    // creates the Image array for the movement animations
    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/assets/raccoons.png");

        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[4][4];
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(j * 181, i * 181, 181, 181);
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
