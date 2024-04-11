package MoveableEntity;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Animation.ImageUtils;
import Display.GameSettings;
import Gamestates.Playing;
import Helpers.Position;

public abstract class MoveableEntity {
    // ATTRIBUTES
    protected Image sprite;
    protected int animationAmount;
    public Position position;

    public Playing playing;
    public int speed;
    protected boolean moving = false;

    public Rectangle solidArea;
    public boolean collisionOn = false;
    public boolean onPath = false;

    protected GameSettings gameSettings = new GameSettings();
    // Static list to store all movable entities
    private static List<MoveableEntity> moveableEntities = new ArrayList<>();

    /**
     * Constructs a MoveableEntity with the given position.
     * 
     * @param position The position of the entity.
     */
    public MoveableEntity(Position position, Playing playing) {
        this.position = position;
        this.playing = playing;

        moveableEntities.add(this);
    }

    /**
     * Retrieves a list of all movable entities.
     * 
     * @return A list containing all movable entities.
     */
    public static List<MoveableEntity> getAllMoveableEntities() {
        return moveableEntities;
    }

    /**
     * Retrieves a list of all player entities.
     * 
     * @return A list containing all player entities.
     */
    public static List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        for (MoveableEntity entity : moveableEntities) {
            if (entity instanceof Player) {
                players.add((Player) entity);
            }
        }
        return players;
    }

    /**
     * Retrieves a list of all enemy entities.
     * 
     * @return A list containing all enemy entities.
     */
    public static List<Enemy> getAllEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        for (MoveableEntity entity : moveableEntities) {
            if (entity instanceof Enemy) {
                enemies.add((Enemy) entity);
            }
        }
        return enemies;
    }

    /**
     * Clears all movable entities.
     */
    public static void clearAllMoveableEntities() {
        moveableEntities.clear();
    }

    /**
     * Retrieves the height of the sprite.
     * 
     * @return The height of the sprite.
     */
    public int getHeight() {
        BufferedImage sprite = ImageUtils.convertToBufferedImage(getSprite());
        if (sprite != null) {
            return sprite.getHeight();
        } else {
            return 0; // or handle null sprite case accordingly
        }
    }

    /**
     * Retrieves the width of the sprite.
     * 
     * @return The width of the sprite.
     */
    public int getWidth() {
        BufferedImage sprite = ImageUtils.convertToBufferedImage(getSprite());
        if (sprite != null) {
            return sprite.getWidth();
        } else {
            return 0; // or handle null sprite case accordingly
        }
    }

    /**
     * Abstract method to retrieve the sprite image associated with the entity.
     * 
     * @return The sprite image associated with the entity.
     */
    public abstract Image getSprite();

    /**
     * Retrieves the position of the entity.
     * 
     * @return The position of the entity.
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Gets the bounding box of the movable entity.
     * 
     * @return The bounding box.
     */
    public Rectangle getBoundingBox() {
        // Return the bounding box of the player entity
        // Implement this method based on how you define the bounding box for the player
        // entity
        return new Rectangle(position.getX(), position.getY(), getWidth(), getHeight());
    }

    /**
     * Recreates the array so it discards the previous entities.
     * 
     */
    public static void resetMoveableEntities() {
        moveableEntities = new ArrayList<>();
    }

    /**
     * Sets the entities position attribute
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(y);
    }
}
