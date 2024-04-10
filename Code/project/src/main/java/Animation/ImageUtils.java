package Animation;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageUtils {
    public static BufferedImage convertToBufferedImage(Image image) {
        // Create a new BufferedImage with the same width and height as the Image
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        // Get the graphics context of the BufferedImage and draw the Image onto it
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose(); // Dispose the graphics context to release resources

        return bufferedImage;
    }
}
