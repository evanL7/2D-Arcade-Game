package StaticEntity;
import java.awt.image.BufferedImage;

import java.awt.*;

import Helpers.Position;
// import MoveableEntity.MoveableEntity;
// import MoveableEntity.Player;
import Helpers.ImageUtils;

//import java.util.Timer;

/**
 * An abstract class representing static entities in the game.
 *
 * <p>A static entity is an object that does not move and has defined behavior when interacting with other entities.
 * This class serves as a base for various static entities, defining common attributes and methods.
 */
public abstract class StaticEntity 
{

    // Attributes

    /** The position of the static entity in the game world. */
    protected Position position;
    
    /** The despawn timer for the entity, measured in milliseconds. A value of -1 indicates despawning only on collision. */
    protected int despawnTimer;

    /** The sprite representing the static entity. */
    protected Image sprite;
    
    //protected Timer despawnTimer;

    /**
     * Constructs a new static entity with a despawn timer.
     *
     * @param position        The position of the static entity.
     * @param despawnTimer    The time, in milliseconds, after which the entity will despawn. Use -1 for despawning only on collision.
     */
    public StaticEntity(Position position, int despawnTimer)
    {
        // Constructor for StaticEntities that rely on time like bonus rewards
        this.position = position;
        this.despawnTimer = despawnTimer;
    }

    /**
     * Constructs a new static entity without a despawn timer.
     *
     * @param position        The position of the static entity.
     * @param sprite          The sprite representing the static entity.
     */
    public StaticEntity(Position position)
    {
        // constructor for StaticEntities that don't despawn on a timer like traps and Regular Rewards
        this.position = position;
        
        // a value of -1 represents how the entity will only despawn if collided with
        despawnTimer = -1;
    }
    

    // Method to get the height of the sprite
    public int getHeight() {
        BufferedImage sprite = ImageUtils.convertToBufferedImage(getSprite());
        if (sprite != null) {
            return sprite.getHeight();
        } else {
            return 0; // or handle null sprite case accordingly
        }
    }

    // Method to get the width of the sprite
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

    // gets and returns the entities position
    public Position getPosition() {
        return this.position;
    }

    /**
     * Gets the bounding box of the static entity.
     * 
     * @return The bounding box.
     */
    public abstract Rectangle getBoundingBox();

}
