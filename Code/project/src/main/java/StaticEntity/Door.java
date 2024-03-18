package StaticEntity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Display.Game;
import Helpers.ImageUtils;
import Helpers.Position;

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
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Image getSprite() {
        return doorImage;
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(position.getX(), position.getY(), getWidth(), getHeight());
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
}
