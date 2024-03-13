package MoveableEntity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Display.Game;
import Helpers.AnimationConstants;
import Helpers.CollisionChecker;
import Helpers.Position;
import Helpers.AnimationConstants.PlayerConstants;

public class Player extends MoveableEntity {

    // ATTRIBUTES
    private BufferedImage[][] animations; // 2d image array of the images for player movements
    private int playerAction = PlayerConstants.DOWN;
    private boolean up, left, down, right;
    private int animationTick, animationIndex, animationSpeed = 35;

    private int regularRewardsCollected;

    private CollisionChecker collisionChecker;

    // CONSTRUCTOR
    public Player(Position position, CollisionChecker collisionChecker) {
        // need to determine the players start position and specific sprite
        super(position);
        this.collisionChecker = collisionChecker;
        loadAnimations();
        speed = 1;

        solidArea = new Rectangle(8, 16, (int) (Game.tileSize * 0.75), Game.tileSize);
    }

    public void update() {
        updatePos();
        updateAnimationTick();
    }

    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(animations[playerAction][animationIndex], position.getX(), position.getY(), Game.tileSize, 72, null);        
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

    // creates the Image array for the movement animations
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

    // updates the animation array during the game loop thread
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

    // GETTERS AND SETTERS
    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setAction(int action) {
        this.playerAction = action;
        this.moving = true;
    }

    public int getAction() {
        return playerAction;
    }

    public int getRegularRewardsCollected() {
        return regularRewardsCollected;
    }

    public void setRegularRewardsCollected(int regularRewardsCollected) {
        this.regularRewardsCollected = regularRewardsCollected;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}