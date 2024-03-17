package MoveableEntity;

import java.awt.*;

import Display.Game;
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

    /**
     * Constructs a MoveableEntity with the given position.
     * 
     * @param position The position of the entity.
     */
    public MoveableEntity(Position position, Playing playing) {
        this.position = position;
        this.playing = playing;
    }

    // gets and returns the entities position
    public Position getPosition() {
        return this.position;
    }

}
