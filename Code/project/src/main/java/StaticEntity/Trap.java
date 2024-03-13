package StaticEntity;

import java.awt.*;

import Helpers.Position;

// trap sprite from https://bdragon1727.itch.io/free-trap-platformer
// unsure of dimensions

/**
 * Represents a trap in the game.
 *
 * <p>Traps are static entities that cause damage upon collision.
 * <p>Traps will only despawn if a player collides with them
 */
public class Trap extends StaticEntity {

    /** The amount of damage the trap inflicts. */
    private float damage;

    /**
     * Constructs a new Trap.
     *
     * @param position        The position of the trap.
     * @param damage          The amount of damage the trap inflicts.
     */
    public Trap(Position position, float damage) {
        // doesn't need despawnTimer as it would only despawn if collided with
        super(position);
        this.damage = damage;
    }

    // Methods

    /**
     * Gets the amount of damage the trap inflicts.
     *
     * @return The amount of damage.
     */
    public float getDamage() {
        return damage;
    }

}