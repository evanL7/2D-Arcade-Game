package MoveableEntity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Rectangle;
import Display.Game;
import Helpers.AnimationConstants;
import Helpers.Position;
import Helpers.AnimationConstants.EnemyConstants;
import Helpers.ImageUtils;

// enemy sprite https://forums.rpgmakerweb.com/index.php?threads/whtdragons-animals-and-running-horses-now-with-more-dragons.53552/
// 48x48

//181x181

public class Enemy extends MoveableEntity {
    // ATTRIBUTES
    private Vector<Position> pathToPlayer;

    private BufferedImage[][] animations; // 2d image array of the images for player movements
    private int enemyAction = EnemyConstants.DOWN;
    private int animationTick, animationIndex, animationSpeed = 60;

    // CONSTRUCTOR
    public Enemy(Position position) {
        super(position);
        loadAnimations();
        speed = 1;

    }

    public void update(Position playerPosition) {
        updateShortestPath(playerPosition);
        updateAnimationTick();
    }

    public void updateShortestPath(Position playerPosition) { // THIS WILL CHANGE
        moving = true;
        pathToPlayer = AStar(position, playerPosition);
    }

    public Vector<Position> AStar(Position start, Position goal) { // using A* algorithm for enemy to player
                                                                   // calculations

        return null;
    }

    // ANIMATION METHODS
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(animations[animationIndex][enemyAction], position.getX(), position.getY(), Game.tileSize + 2, 60,
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

    @Override
    public Image getSprite() {
        // Return the sprite of the enemy
        return sprite; // Assuming sprite is the attribute storing the sprite
    }

    @Override
    public Rectangle getBoundingBox() {
        // Return the bounding box of the player entity
        // Implement this method based on how you define the bounding box for the player entity
        return new Rectangle(position.getX(), position.getY(), getWidth(), getHeight());
    }

    @Override
    public int getWidth() {
        if (sprite != null) {
            BufferedImage bufferedImage = ImageUtils.convertToBufferedImage(sprite);
            return bufferedImage.getWidth();
        } else {
            return 0; // Or any default width value you prefer
        }
    }

    @Override
    public int getHeight() {
        if (sprite != null) {
            BufferedImage bufferedImage = ImageUtils.convertToBufferedImage(sprite);
            return bufferedImage.getHeight();
        } else {
            return 0; // Or any default height value you prefer
        }
    }
}
