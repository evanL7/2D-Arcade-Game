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
    private int animationTick, animationIndex, animationSpeed = 60;

    // CONSTRUCTOR
    public Enemy(Position position) {
        super(position);
        loadAnimations();
        onPath = true;
        speed = 1;

    }

    public void update(Player player) {
        // updateShortestPath(player);
        updateAnimationTick();
    }

    public void updateShortestPath(Player player) {
        moving = true;
        if (onPath == true) {
            int goalCol = (player.getPosition().getX() + playing.getPlayer().solidArea.x) / Game.tileSize;
            int goalRow = (player.getPosition().getY() + playing.getPlayer().solidArea.y) / Game.tileSize;

            searchPath(goalCol, goalRow);
        }

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

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (position.getX() + solidArea.x) / Game.tileSize;
        int startRow = (position.getY() + solidArea.y) / Game.tileSize;

        playing.pathFinder.setNode(startCol, startRow, goalCol, goalRow);

        if (playing.pathFinder.search() == true) {
            // Next worldX and worldY
            int nextX = playing.pathFinder.pathList.get(0).col * Game.tileSize;
            int nextY = playing.pathFinder.pathList.get(0).row * Game.tileSize;

            // Entity's solidArea position
            int enLeftX = position.getX() + solidArea.x;
            int enRightX = position.getX() + solidArea.x + solidArea.width;
            int enTopY = position.getY() + solidArea.y;
            int enBottomY = position.getY() + solidArea.y + solidArea.height;

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

        }
    }

}
