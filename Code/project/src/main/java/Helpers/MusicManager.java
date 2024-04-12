package Helpers;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import java.io.InputStream;

public class MusicManager {
    private static AdvancedPlayer player;
    private static String filePath = "music/Optimistic-background-music.mp3";
    private static float volume = 0.5f;

    /**
     * Plays background music from the given file path.
     * @param filePath The file path of the background music to be played.
     * @param volume The volume level (0.0f to 1.0f).
     */
    public static void playMusic() {
        try {
            InputStream inputStream = MusicManager.class.getClassLoader().getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new RuntimeException("File not found: " + filePath);
            }
            player = new AdvancedPlayer(inputStream);
            setMusicVolume(volume);
            Thread playerThread = new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
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

    /**
     * Sets the system-wide volume for the background music.
     * @param volume The volume level (0.0f to 1.0f).
     */
    public static void setMusicVolume(float volume) {
        try {
            Mixer.Info[] mixerInfo = javax.sound.sampled.AudioSystem.getMixerInfo();
            for (Mixer.Info info : mixerInfo) {
                Mixer mixer = javax.sound.sampled.AudioSystem.getMixer(info);
                if (mixer.isLineSupported(Port.Info.SPEAKER)) {
                    Port port = (Port) mixer.getLine(Port.Info.SPEAKER);
                    port.open();
                    if (port.isControlSupported(javax.sound.sampled.FloatControl.Type.VOLUME)) {
                        javax.sound.sampled.FloatControl volumeControl = (javax.sound.sampled.FloatControl) port.getControl(javax.sound.sampled.FloatControl.Type.VOLUME);
                        volumeControl.setValue(volume);
                    }
                    port.close();
                }
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
