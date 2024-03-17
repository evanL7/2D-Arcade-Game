package MoveableEntity;

import java.awt.*;

import Display.Game;
import Gamestates.Playing;
import Helpers.Position;

public abstract class MoveableEntity {
    // ATTRIBUTES
    protected Image sprite;
    public Position position;
    Playing playing;
    public int speed;
    protected boolean moving = false;

    public Rectangle solidArea;
    public boolean collisionOn = false;
    public boolean onPath = false;

    // way to make this public from playing??
    public int worldX = Game.tileSize * 23;
    public int worldY = Game.tileSize * 21;

    /**
     * Constructs a MoveableEntity with the given position.
     * 
     * @param position The position of the entity.
     */
    public MoveableEntity(Position position) {
        this.position = position;
    }

    // gets and returns the entities position
    public Position getPosition() {
        return this.position;
    }

    public void checkCollision() {
        // TO DO
    }

}
