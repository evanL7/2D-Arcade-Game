package com.mycompany.app.classes.MoveableEntity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.mycompany.app.classes.Helpers.Position;

public abstract class MoveableEntity {
    // ATTRIBUTES
    protected Image sprite;
    private BufferedImage[][] animations;
    protected int speed;
    protected Position position;
    protected int aniTick, aniIndex, aniSpeed = 25;
    // protected int playerAction = IDLE;
    protected int playerDir = -1;
    protected boolean moving = false;

    /**
     * Constructs a MoveableEntity with the given position.
     * 
     * @param position The position of the entity.
     */
    public MoveableEntity(Position position) {
        this.position = position;
    }

    /**
     * Draws the entity on the graphics context.
     * 
     * @param g The graphics context.
     */
    public void draw(Graphics2D g) {

    }

    /**
     * Updates the state of the entity.
     */
    public void update() {

    }

    /**
     * Renders the entity.
     */
    public void render() {

    }

    /*
     * private void updatePos() {
     * if (moving) {
     * switch (playerDir) {
     * case LEFT:
     * position.setX(position.getX() - 5);
     * break;
     * case RIGHT:
     * position.setX(position.getX() + 5);
     * break;
     * case UP:
     * position.setY(position.getY() - 5);
     * break;
     * case DOWN:
     * position.setY(position.getY() + 5);
     * break;
     * }
     * }
     * }
     */

    /**
     * Loads the animations for the entity from a sprite sheet.
     */
    private void loadAnimations() {

        InputStream is = getClass().getResourceAsStream("/sprites.png");
        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[9][6];
            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
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
}
