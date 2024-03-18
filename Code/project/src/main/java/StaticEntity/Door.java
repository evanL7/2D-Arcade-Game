package StaticEntity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Display.Game;
import Helpers.Position;


/**
 * Represents a door in the game.
 * Used to transition between levels.
 * Source of door sprite: https://www.youtube.com/watch?v=xYtXz34IJdY&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq
 */
public class Door extends StaticEntity {

    private BufferedImage doorImage;

    public Door(Position position) {
        super(position);
        try {
            // Load the image from the file
            InputStream is = getClass().getResourceAsStream("/assets/door.png");
            doorImage = ImageIO.read(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        g.drawImage(doorImage, position.getX(), position.getY(), Game.tileSize, Game.tileSize, null);
    }

    @Override
    public Image getSprite() {
        return doorImage;
    }
}
