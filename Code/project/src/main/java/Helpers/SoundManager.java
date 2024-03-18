package Helpers;

import javax.sound.sampled.*;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * A class to manage playing short sound effects in the game.
 */
public class SoundManager {
    //private static final int BUFFER_SIZE = 4096;

    /**
     * Plays a sound effect from the given file path.
     * @param filePath The file path of the sound effect to be played.
     */
    public static void playSound(String filePath) {
        try {
            InputStream inputStream = SoundManager.class.getClassLoader().getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + filePath);
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(inputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}






    // public static void playSound(String filePath) {
    //     try {
            
    //         // Create a new Clip object to play the sound
    //         Clip clip = AudioSystem.getClip();

    //         // Get an AudioInputStream from the file
    //         AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(filePath));

    //         // Open the clip and start playing the sound
    //         clip.open(inputStream);
    //         clip.start();
    //     } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
    //         e.printStackTrace();
    //     }
    // }
