package Helpers;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.InputStream;

/**
 * A class to manage playing looping background music in the game.
 */
public class MusicManager {
    private static AdvancedPlayer player;

    /**
     * Plays background music from the given file path.
     * @param filePath The file path of the background music to be played.
     */
    public static void playMusic(String filePath) {
        try {
            // Get the input stream for the resource
            InputStream inputStream = MusicManager.class.getClassLoader().getResourceAsStream(filePath);

            // Check if input stream is null
            if (inputStream == null) {
                throw new RuntimeException("File not found: " + filePath);
            }

            // Create an AdvancedPlayer to play the music
            player = new AdvancedPlayer(inputStream);

            // Start playing the music in a loop
            Thread playerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        player.play();
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }
                }
            });
            playerThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the currently playing background music.
     */
    public static void stopMusic() {
        if (player != null) {
            player.close();
        }
    }
}
