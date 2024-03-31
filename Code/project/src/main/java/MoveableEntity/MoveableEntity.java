package MoveableEntity;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;

import Helpers.ImageUtils;
// import Display.Game;
import Gamestates.Playing;
import Helpers.Position;

public abstract class MoveableEntity {
    // ATTRIBUTES
    protected Image sprite;
    public Position position;

    public Playing playing;
    public int speed;
    protected boolean moving = false;

    public Rectangle solidArea;
    public boolean collisionOn = false;
    public boolean onPath = false;

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

    // Method to get all movable entities
    public static List<MoveableEntity> getAllMoveableEntities() {
        return moveableEntities;
    }

    // Method to get all players
    public static List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        for (MoveableEntity entity : moveableEntities) {
            if (entity instanceof Player) {
                players.add((Player) entity);
            }
        }
        return players;
    }

    // Method to get all enemies
    public static List<Enemy> getAllEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        for (MoveableEntity entity : moveableEntities) {
            if (entity instanceof Enemy) {
                enemies.add((Enemy) entity);
            }
        }
        return enemies;
    }

    // Method to clear all movable entities
    public static void clearAllMoveableEntities() {
        moveableEntities.clear();
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

    // Define the getSprite() method to return the sprite
    public abstract Image getSprite();

    // gets and returns the entities position
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

    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(y);
    }
}
