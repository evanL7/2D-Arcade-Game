package MoveableEntity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
//import java.util.Vector;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Rectangle;
import Display.Game;
//import Gamestates.Gamestate;
import Gamestates.Playing;
import Helpers.AnimationConstants;
import Helpers.Position;
import Helpers.AnimationConstants.EnemyConstants;
import Helpers.ImageUtils;

// enemy sprite https://forums.rpgmakerweb.com/index.php?threads/whtdragons-animals-and-running-horses-now-with-more-dragons.53552/
// 48x48

//181x181

/**
 * The Enemy class represents an enemy entity that can move within the game.
 * It extends the MoveableEntity class.
 */
public class Enemy extends MoveableEntity {
    // ATTRIBUTES

    //private Vector<Position> pathToPlayer;

    private BufferedImage[][] animations; // 2d image array of the images for player movements
    private int enemyAction = EnemyConstants.DOWN;
    private int animationTick, animationIndex, animationSpeed = 60;

    // CONSTRUCTOR
    /**
     * Constructs an Enemy object with the given position and playing state.
     * 
     * @param position The initial position of the enemy.
     * @param playing  The current playing state of the game.
     */
    public Enemy(Position position, Playing playing) {
        super(position, playing);
        loadAnimations();
        onPath = true;
        speed = 1;

        solidArea = new Rectangle(8, 16, (int) (Game.tileSize * 0.75), Game.tileSize);

    }

    /**
     * Updates the state of the enemy based on the player's position.
     * 
     * @param player The player object in the game.
     */
    public void update(Player player) {
        updateAnimationTick();
        updateShortestPath(player);

        // moving the enemy
        playing.collisionChecker.checkTileEnemy(this, enemyAction);
        if (collisionOn == false) {
            switch (enemyAction) {
                case EnemyConstants.UP:
                    position.setY(position.getY() - speed);
                    break;
                case EnemyConstants.DOWN:
                    position.setY(position.getY() + speed);
                    break;
                case EnemyConstants.LEFT:
                    position.setX(position.getX() - speed);
                    break;
                case EnemyConstants.RIGHT:
                    position.setX(position.getX() + speed);
                    break;
            }
        }
    }

    /**
     * Checks for collisions with other entities or objects.
     */
    public void checkCollision() {
        playing.collisionChecker.checkTileEnemy(this, enemyAction);
        // add objects and entity checks here
    }

    /**
     * Updates the shortest path to the player's position.
     * 
     * @param player The player object in the game.
     */
    public void updateShortestPath(Player player) {
        moving = true;
        if (onPath == true) {
            int goalCol = (player.getPosition().getY() + player.solidArea.y) / Game.tileSize;
            int goalRow = (player.getPosition().getX() + player.solidArea.x) / Game.tileSize;

            searchPath(goalCol, goalRow);
        }

    }

    // ANIMATION METHODS

    /**
     * Renders the enemy on the screen.
     * 
     * @param g The graphics object used for rendering.
     */
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(animations[animationIndex][enemyAction], position.getX(), position.getY(), Game.tileSize + 2, 60,
                null);
    }

    /**
     * Loads the animations for the enemy movement and creates the Image array for
     * the movement animations
     */
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

    /**
     * Updates the animation tick during the game loop thread.
     */
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
        // Implement this method based on how you define the bounding box for the player
        // entity
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

    /**
     * Searches for the shortest path to the specified goal position.
     * 
     * @param goalCol The column index of the goal position.
     * @param goalRow The row index of the goal position.
     */
    public void searchPath(int goalCol, int goalRow) {
        int startCol = (position.getY() + solidArea.y) / Game.tileSize;
        int startRow = (position.getX() + solidArea.x) / Game.tileSize;

        playing.pathFinder.setNode(startCol, startRow, goalCol, goalRow);

        if (playing.pathFinder.search() == true) {
            // Next worldX and worldY
            int nextY = playing.pathFinder.pathList.get(0).col;
            int nextX = playing.pathFinder.pathList.get(0).row;

            // Entity's solidArea position
            int enLeftX = (position.getX() + solidArea.x) / Game.tileSize;
            int enRightX = (position.getX() + solidArea.x + solidArea.width) / Game.tileSize;
            int enTopY = (position.getY() + solidArea.y) / Game.tileSize;
            int enBottomY = (position.getY() + solidArea.y + solidArea.height) / Game.tileSize;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + Game.tileSize) {
                enemyAction = EnemyConstants.UP;
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + Game.tileSize) {
                enemyAction = EnemyConstants.DOWN;
            } else if (enTopY >= nextY && enBottomY < nextY + Game.tileSize) {
                // left or right
                if (enLeftX > nextX) {
                    enemyAction = EnemyConstants.LEFT;
                }
                if (enLeftX < nextX) {
                    enemyAction = EnemyConstants.RIGHT;
                }
            } else if (enTopY > nextY && enLeftX > nextX) {
                // up or left
                enemyAction = EnemyConstants.UP;
                checkCollision();
                if (collisionOn == true) {
                    enemyAction = EnemyConstants.LEFT;
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                // up or right
                enemyAction = EnemyConstants.UP;
                checkCollision();
                if (collisionOn == true) {
                    enemyAction = EnemyConstants.RIGHT;
                }
            } else if (enTopY < nextY && enLeftX > nextX) {
                // down or left
                enemyAction = EnemyConstants.DOWN;
                checkCollision();
                if (collisionOn == true) {
                    enemyAction = EnemyConstants.LEFT;
                }
            } else if (enTopY < nextY && enLeftX < nextX) {
                // down or right
                enemyAction = EnemyConstants.DOWN;
                checkCollision();
                if (collisionOn == true) {
                    enemyAction = EnemyConstants.RIGHT;
                }
            }

            // enemy reaches player
            int nextCol = playing.pathFinder.pathList.get(0).col;
            int nextRow = playing.pathFinder.pathList.get(0).col;

            if (nextCol == goalCol && nextRow == goalRow) {
                playing.getPlayer().decreaseScore(4); // change this
            }
        }
    }

}
