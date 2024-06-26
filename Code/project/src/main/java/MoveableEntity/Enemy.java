package MoveableEntity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Animation.AnimationConstants.EnemyConstants;
import Animation.Animations;

import java.awt.Image;
import java.awt.Rectangle;
import Gamestates.Playing;
import Helpers.Position;

// enemy sprite https://www.reddit.com/r/PixelArt/comments/k40t81/sprite_sheet_for_a_raccoon_dude/
// 181x181

/**
 * The Enemy class represents an enemy entity that can move within the game.
 * It extends the MoveableEntity class.
 */
public class Enemy extends MoveableEntity {
    // ATTRIBUTES

    // private Vector<Position> pathToPlayer;

    private Animations animation;

    private int enemyAction = EnemyConstants.DOWN;

    private BufferedImage enemyImage;

    // CONSTRUCTOR
    /**
     * Constructs an Enemy object with the given position and playing state.
     * 
     * @param position The initial position of the enemy.
     * @param playing  The current playing state of the game.
     */
    public Enemy(Position position, Playing playing) {
        super(position, playing);
        animation = new Animations("/assets/raccoons.png");
        animation.setAnimationAmount(4);
        animation.setAnimationArrayHeight(4);
        animation.setAnimationArrayWidth(4);
        animation.setSpriteHeight(181);
        animation.setSpriteWidth(181);
        animation.setAnimationSpeed(60);

        animation.loadAnimations();
        animation.loadImage();

        animationAmount = 4;

        onPath = true;
        speed = 1;
        solidArea = new Rectangle(8, 16, (int) (gameSettings.getTileSize() * 0.75), gameSettings.getTileSize());
    }

    /**
     * Updates the state of the enemy based on the player's position.
     * 
     * @param player The player object in the game.
     */
    public void update(Player player) {
        animation.updateAnimationTick();
        updateShortestPath(player);

        collisionOn = false;

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
            int goalCol = (player.getPosition().getY() + player.solidArea.y) / gameSettings.getTileSize();
            int goalRow = (player.getPosition().getX() + player.solidArea.x) / gameSettings.getTileSize();

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
        BufferedImage[][] enemyAnimations = animation.getBufferedImages();
        g2.drawImage(enemyAnimations[animation.getAnimationIndex()][enemyAction], position.getX(), position.getY(),
                gameSettings.getTileSize() + 2, 60,
                null);
    }

    @Override
    public Image getSprite() {
        // Return the sprite of the enemy
        return enemyImage;
    }

    /**
     * Searches for the shortest path to the specified goal position.
     * 
     * @param goalCol The column index of the goal position.
     * @param goalRow The row index of the goal position.
     */
    public void searchPath(int goalCol, int goalRow) {
        int startCol = (position.getY() + solidArea.y) / gameSettings.getTileSize();
        int startRow = (position.getX() + solidArea.x) / gameSettings.getTileSize();

        playing.pathFinder.setNode(startCol, startRow, goalCol, goalRow);

        if (playing.pathFinder.search() == true) {
            // Next worldX and worldY
            int nextY = playing.pathFinder.pathList.get(0).col * gameSettings.getTileSize();
            int nextX = playing.pathFinder.pathList.get(0).row * gameSettings.getTileSize();

            // Entity's solidArea position
            int enLeftX = (position.getX() + solidArea.x);
            int enRightX = (position.getX() + solidArea.x + solidArea.width);
            int enTopY = (position.getY() + solidArea.y);
            int enBottomY = (position.getY() + solidArea.y + solidArea.height);

            if (enTopY >= nextY && enLeftX >= nextX && enRightX < nextX + gameSettings.getTileSize()) {
                enemyAction = EnemyConstants.UP;
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX) {
                enemyAction = EnemyConstants.DOWN;
            } else if (enTopY >= nextY && enBottomY <= nextY + gameSettings.getTileSize()) {
                // left or right
                if (collisionOn == true) {
                    enemyAction = EnemyConstants.UP;
                } else {
                    if (enLeftX > nextX) {
                        enemyAction = EnemyConstants.LEFT;
                    } else if (enLeftX < nextX) {
                        enemyAction = EnemyConstants.RIGHT;
                    }
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
            int nextRow = playing.pathFinder.pathList.get(0).row;

            if (nextCol == goalCol && nextRow == goalRow) {
                // playing.getPlayer().decreaseScore(4); // change this
            }
        } else {
            enemyAction = EnemyConstants.LEFT;
        }
    }

    public int getEnemyAction() {
        return enemyAction;
    }
}
