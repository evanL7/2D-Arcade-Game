package MoveableEntity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import java.awt.Image;

import Display.Game;
import Display.Score;
import Gamestates.Playing;
import Helpers.AnimationConstants;
import Helpers.CollisionChecker;
// import Helpers.ImageUtils;
import Helpers.Position;
// import StaticEntity.Reward;
// import StaticEntity.StaticEntity;
import StaticEntity.TileManager;
import Helpers.AnimationConstants.PlayerConstants;

/**
 * The Player class represents the main character controlled by the player.
 * It extends the MoveableEntity class and includes methods for player movement,
 * rendering, and interaction with the game environment.
 */
public class Player extends MoveableEntity {

    // ATTRIBUTES
    public BufferedImage[][] animations; // 2d image array of the images for player movements
    private int playerAction = PlayerConstants.DOWN;
    private boolean up, left, down, right;
    private int animationTick, animationIndex, animationSpeed = 35;

    private int regularRewardsCollected;

    private TileManager tileManager;

    private CollisionChecker collisionChecker;

    private BufferedImage playerImage;
    // private float score;

    private Score scoreObject;

    // CONSTRUCTOR
    /**
     * Constructs a Player object with the specified position, collision checker,
     * playing state, score object, and tile manager.
     * 
     * @param position         The initial position of the player.
     * @param collisionChecker The collision checker used to detect collisions with
     *                         the environment.
     * @param playing          The current playing state of the game.
     * @param scoreObject      The score object associated with the player.
     * @param tileManager      The tile manager containing information about the
     *                         game map.
     */
    public Player(Position position, CollisionChecker collisionChecker, Playing playing, Score scoreObject,
            TileManager tileManager) {
        // need to determine the players start position and specific sprite
        super(position, playing);
        this.collisionChecker = collisionChecker;
        this.scoreObject = scoreObject; // Assign the score object
        this.tileManager = tileManager;

        loadAnimations();
        loadPlayerImage();
        speed = 1;

        solidArea = new Rectangle(8, 16, (int) (Game.tileSize * 0.75), Game.tileSize);
    }

    /**
     * Updates the player's state during each game loop iteration.
     * This includes updating the player's position and animation.
     */
    public void update() {
        updatePos();

        // // Check collision with rewards
        // for (StaticEntity entity : tileManager.getStaticEntities()) {
        // if (entity instanceof Reward) {
        // if (entity.getBoundingBox().intersects(this.getBoundingBox())) {
        // // Collision with reward detected
        // ((Reward) entity).onCollide(this);
        // // Remove the reward from the game world
        // tileManager.getStaticEntities().remove(entity);
        // break; // Exit loop after detecting collision with one reward
        // }
        // }
        // }

        // // Check collision with enemies
        // for (MoveableEntity enemy : tileManager.getEnemies()) {
        // if (enemy.getBoundingBox().intersects(this.getBoundingBox())) {
        // // Collision with enemy detected
        // // Handle collision with enemy
        // break; // Exit loop after detecting collision with one enemy
        // }
        // }

        // // Check collision with traps
        // for (StaticEntity trap : tileManager.getTraps()) {
        //     if (trap.getBoundingBox().intersects(this.getBoundingBox())) {
        //         // Collision with trap detected
        //         // Handle collision with trap
        //         break; // Exit loop after detecting collision with one trap
        //     }
        // }

        updateAnimationTick();
    }

    /**
     * Renders the player on the screen.
     * 
     * @param g The graphics context used for rendering.
     */
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(animations[playerAction][animationIndex], position.getX(), position.getY(), Game.tileSize, 72,
                null);
    }

    /**
     * Updates the player's position based on the currently pressed keys.
     * If the player is moving, it adjusts the position according to the keys being
     * pressed.
     */
    private void updatePos() {
        moving = false;

        collisionOn = false;

        collisionChecker.checkTile(this, playerAction);

        if (collisionOn == false) {
            if (left && !right && !up && !down) {
                position.setX(position.getX() - speed); // Move left
                moving = true;
            } else if (right && !left && !up && !down) {
                position.setX(position.getX() + speed); // Move right
                moving = true;
            }

            if (up && !down && !left && !right) {
                position.setY(position.getY() - speed); // Move up
                moving = true;
            } else if (down && !up && !left && !right) {
                position.setY(position.getY() + speed); // Move down
                moving = true;
            }
        }
    }

    /**
     * Loads the player's animation sprites from the resource file.
     */
    private void loadAnimations() {
        // Source of player sprites:
        // https://axulart.itch.io/small-8-direction-characters
        InputStream is = getClass().getResourceAsStream("/assets/player_sprites.png");

        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[4][3];
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

    /**
     * Loads the player's image from the resource file.
     */
    private void loadPlayerImage() {
        try {
            InputStream is = getClass().getResourceAsStream("/assets/player_sprites.png");
            playerImage = ImageIO.read(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the animation tick during the game loop.
     */
    private void updateAnimationTick() {
        animationTick++;
        if (moving && animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= AnimationConstants.SpriteAmount(playerAction)) {
                animationIndex = 0;
            }
        }
    }

    /**
     * Increases the player's score by the specified amount.
     * 
     * @param amount The amount by which to increase the player's score.
     */
    public void increaseScore(float amount) {
        scoreObject.incrementScore(amount); // Call the increment method in Score class
    }

    /**
     * Decreases the player's score by the specified amount.
     * 
     * @param amount The amount by which to decrease the player's score.
     */
    public void decreaseScore(float amount) {
        scoreObject.incrementScore(-amount); // Call the increment method with negative amount to decrease score
    }

    // GETTERS AND SETTERS
    /**
     * Resets the direction booleans indicating the player's movement direction.
     */
    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    @Override
    public Image getSprite() {
        return playerImage;
    }

    @Override
    public Rectangle getBoundingBox() {
        // Return the bounding box of the player entity
        // Implement this method based on how you define the bounding box for the player
        // entity
        return new Rectangle(position.getX(), position.getY(), getWidth(), getHeight());
    }

    // @Override
    // public int getWidth() {
    // if (sprite != null) {
    // BufferedImage bufferedImage = ImageUtils.convertToBufferedImage(sprite);
    // return bufferedImage.getWidth();
    // } else {
    // return 0; // Or any default width value you prefer
    // }
    // }

    // @Override
    // public int getHeight() {
    // if (sprite != null) {
    // BufferedImage bufferedImage = ImageUtils.convertToBufferedImage(sprite);
    // return bufferedImage.getHeight();
    // } else {
    // return 0; // Or any default height value you prefer
    // }
    // }

    /**
     * Sets whether the player is moving.
     * 
     * @param moving true if the player is moving; otherwise, false.
     */
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     * Sets the current action of the player.
     * 
     * @param action The action code representing the player's current movement
     *               direction.
     */
    public void setAction(int action) {
        this.playerAction = action;
        this.moving = true;
    }

    /**
     * Retrieves the current action of the player.
     * 
     * @return The action code representing the player's current movement direction.
     */
    public int getAction() {
        return playerAction;
    }

    /**
     * Retrieves the number of regular rewards collected by the player.
     * 
     * @return The number of regular rewards collected by the player.
     */
    public int getRegularRewardsCollected() {
        return regularRewardsCollected;
    }

    /**
     * Sets the number of regular rewards collected by the player.
     * 
     * @param regularRewardsCollected The number of regular rewards collected by the
     *                                player.
     */
    public void setRegularRewardsCollected(int regularRewardsCollected) {
        this.regularRewardsCollected = regularRewardsCollected;
    }

    /**
     * Retrieves the position of the player.
     * 
     * @return The position of the player.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Checks if the player is moving left.
     * 
     * @return true if the player is moving left; otherwise, false.
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * Sets whether the player is moving left.
     * 
     * @param left true if the player is moving left; otherwise, false.
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Checks if the player is moving up.
     * 
     * @return true if the player is moving up; otherwise, false.
     */
    public boolean isUp() {
        return up;
    }

    /**
     * Sets whether the player is moving up.
     * 
     * @param up true if the player is moving up; otherwise, false.
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * Checks if the player is moving right.
     * 
     * @return true if the player is moving right; otherwise, false.
     */
    public boolean isRight() {
        return right;
    }

    /**
     * Sets whether the player is moving right.
     * 
     * @param right true if the player is moving right; otherwise, false.
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * Checks if the player is moving down.
     * 
     * @return true if the player is moving down; otherwise, false.
     */
    public boolean isDown() {
        return down;
    }

    /**
     * Sets whether the player is moving down.
     * 
     * @param down true if the player is moving down; otherwise, false.
     */
    public void setDown(boolean down) {
        this.down = down;
    }
}
