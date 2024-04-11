package StaticEntity;

import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Helpers.Position;

/**
 * Represents a door in the game.
 * Used to transition between levels.
 * Source of door sprite:
 * https://www.youtube.com/watch?v=xYtXz34IJdY&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq
 * 
 * new doors: 32x16
 */
public class Door extends StaticEntity {

    private BufferedImage doorImage;
    private BufferedImage doorOpenImage;
    private boolean open;

    public Door(Position position) {
        super(position);
        open = false;
        try {
            // Load the image from the file
            InputStream is = getClass().getResourceAsStream("/assets/closeDoor.png");
            doorImage = ImageIO.read(is);
            is.close();

            // Load the open door image from the file
            InputStream isOpen = getClass().getResourceAsStream("/assets/openDoor.png"); // change this
            doorOpenImage = ImageIO.read(isOpen);
            isOpen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        if (open) {
            g.drawImage(doorOpenImage, position.getX(), position.getY(), 48, 96, null);
        } else {
            g.drawImage(doorImage, position.getX(), position.getY(), 48, 96, null);
        }
    }

    /**
     * Sets the Door to open
     */
    public void setOpen() {
        open = true;
    }

    /**
     * Gets the boolean attribute open
     * 
     * @return a boolean
     */
    public boolean getOpen() {
        return open;
    }

    @Override
    public Image getSprite() {
        return doorImage;
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(position.getX(), position.getY(), gameSettings.getTileSize(), gameSettings.getTileSize());
    }
}
