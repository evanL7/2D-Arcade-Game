package StaticEntity;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Helpers.ImageUtils;
import Helpers.Position;
import MoveableEntity.MoveableEntity;

public class Door extends StaticEntity {

    private BufferedImage doorImage;

    public Door(Position position) {
        super(position);
    }

    @Override
    public void onCollide(MoveableEntity entity) {
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
