package Helpers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * A class to manage playing short sound effects in the game.
 */
public class SoundManager {
    private static final int BUFFER_SIZE = 4096;

    /**
     * Plays a sound effect from the given file path.
     * @param filePath The file path of the sound effect to be played.
     */
    public static void playSound(String filePath) {
        try {
            // Create a new Clip object to play the sound
            Clip clip = AudioSystem.getClip();

            // Get an AudioInputStream from the file
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Open the clip and start playing the sound
            clip.open(inputStream);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
