package Helpers;

import javax.sound.sampled.*;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SoundManager {
    
    private static String soundCollectFilePath = "sounds/bloop_x.wav";
    private static String soundTrapFilePath = "sounds/mixkit-boxer-getting-hit-2055.wav";
    private static String soundWinFilePath = "sounds/mixkit-game-bonus-reached-2065.wav";
    private static float volume = 0.5f;

    /**
     * Plays the sound effect for collecting items.
     */
    public static void playCollectSound() {
        playSound(soundCollectFilePath);
    }

    /**
     * Plays the sound effect for encountering traps.
     */
    public static void playTrapSound() {
        playSound(soundTrapFilePath);
    }

    /**
     * Plays the sound effect for winning the game.
     */
    public static void playWinSound() {
        playSound(soundWinFilePath);
    }

    /**
     * Plays a sound effect from the given file path with adjustable volume.
     * @param filePath The file path of the sound effect to be played.
     * @param volume The volume level (0.0f to 1.0f).
     */
    private static void playSound(String filePath) {
        try {
            InputStream inputStream = SoundManager.class.getClassLoader().getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + filePath);
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(inputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            // Adjust volume
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
