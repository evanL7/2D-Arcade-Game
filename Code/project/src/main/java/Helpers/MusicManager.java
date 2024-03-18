package Helpers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * A class to manage playing looping background music in the game.
 */
public class MusicManager {
    private static Clip clip;

    private static final int BUFFER_SIZE = 4096;

    /**
     * Plays background music from the given file path.
     * @param filePath The file path of the background music to be played.
     */
    public static void playMusic(String filePath) {
        try {
            // Create a new Clip object to play the music
            clip = AudioSystem.getClip();

            // Get an AudioInputStream from the file
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Open the clip and start playing the music in a loop
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the currently playing background music.
     */
    public static void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
