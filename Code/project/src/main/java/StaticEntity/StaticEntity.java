package StaticEntity;

import java.awt.image.BufferedImage;
import java.awt.*;

import Helpers.Position;

import java.util.ArrayList;
import java.util.List;

import Animation.ImageUtils;
import Display.GameSettings;

/**
 * An abstract class representing static entities in the game.
 *
 * <p>
 * A static entity is an object that does not move and has defined behavior when
 * interacting with other entities.
 * This class serves as a base for various static entities, defining common
 * attributes and methods.
 */
public abstract class StaticEntity {

    /** The position of the static entity in the game world. */
    protected Position position;

    /**
     * The despawn timer for the entity, measured in milliseconds. A value of -1
     * indicates despawning only on collision.
     */
    protected int despawnTimer;

    /** The sprite representing the static entity. */
    protected Image sprite;

    protected GameSettings gameSettings = new GameSettings();
    // Static list to store all static entities
    private static List<StaticEntity> staticEntities = new ArrayList<>();

    /**
     * Constructs a new static entity with a despawn timer.
     *
     * @param position     The position of the static entity.
     * @param despawnTimer The time, in milliseconds, after which the entity will
     *                     despawn. Use -1 for despawning only on collision.
     */
    public StaticEntity(Position position, int despawnTimer) {
        this.position = position;
        this.despawnTimer = despawnTimer;

        staticEntities.add(this);
    }

    /**
     * Constructs a new static entity without a despawn timer.
     *
     * @param position The position of the static entity.
     * @param sprite   The sprite representing the static entity.
     */
    public StaticEntity(Position position) {
        this.position = position;
        // a value of -1 represents how the entity will only despawn if collided with
        despawnTimer = -1;
        staticEntities.add(this);
    }

    /**
     * Method to get all static entities.
     * 
     * @return The list containing all static entities.
     */
    public static List<StaticEntity> getAllStaticEntities() {
        return staticEntities;
    }

    /**
     * Method to get all rewards.
     * 
     * @return The list containing all rewards.
     */
    public static List<Reward> getAllRewards() {
        List<Reward> rewards = new ArrayList<>();
        for (StaticEntity entity : staticEntities) {
            if (entity instanceof Reward) {
                rewards.add((Reward) entity);
            }
        }
        return rewards;
    }

    /**
     * Method to get all traps.
     * 
     * @return The list containing all traps.
     */
    public static List<Trap> getAllTraps() {
        List<Trap> traps = new ArrayList<>();
        for (StaticEntity entity : staticEntities) {
            if (entity instanceof Trap) {
                traps.add((Trap) entity);
            }
        }
        return traps;
    }

    /**
     * Method to get the door.
     * 
     * @return The door object.
     */
    public static Door getDoor() {
        Door door = null;
        for (StaticEntity entity : staticEntities) {
            if (entity instanceof Door) {
                door = (Door) entity;
            }
        }
        return door;
    }

    /**
     * Discards all elements in the array so it is now empty.
     */
    public static void clearAllStaticEntities() {
        staticEntities.clear();
    }

    /**
     * Gets the bounding box of the static entity.
     * 
     * @return The bounding box.
     */
    public Rectangle getBoundingBox() {
        return new Rectangle(position.getX(), position.getY(), (int) (gameSettings.getTileSize() * 0.6), 15);
    }

    /**
     * Gets the height of the static entity.
     * 
     * @return The height of the static entity.
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
     * Gets the width of the static entity.
     * 
     * @return The width of the static entity.
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
     * Gets the sprite associated with the static entity.
     * 
     * @return The sprite image.
     */
    public abstract Image getSprite();

    /**
     * Gets the position of the static entity.
     * 
     * @return The position of the static entity.
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Renders the static entity on the graphics context.
     * 
     * @param g The graphics context used for rendering.
     */
    public void render(Graphics g) {
    }

    /**
     * Updates the state of the static entity.
     */
    public void update() {
    }

    /**
     * Removes a static entity from the list of all static entities.
     */
    public void remove() {
        staticEntities.remove(this);
    }

    /**
     * Recreates the array so it discards the previous entities.
     */
    public static void resetStaticEntities() {
        staticEntities = new ArrayList<>();
    }
}
