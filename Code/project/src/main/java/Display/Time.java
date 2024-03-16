package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import Gamestates.Gamestate;

public class Time {

    public long startTime;
    private long pausedTime; // when the timer was paused
    private long totalPausedTime; // how long the timer has been paused

    public Time() {
        startTime = System.currentTimeMillis();
        pausedTime = 0;
        totalPausedTime = 0;
        pauseTimer();
    }

    public void pauseTimer() {
        pausedTime = System.currentTimeMillis();
        System.out.println("The game was paused at this time: " + pausedTime); // test
    }

    public void resumeTimer() {
        totalPausedTime += System.currentTimeMillis() - pausedTime;
        pausedTime = 0;
        System.out.println("Total time paused is: " + totalPausedTime); // test
    }

    public void displayElapsedTime(Graphics g) {
        if (Gamestate.state == Gamestate.PLAYING) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime - totalPausedTime;

            // Convert elapsed time to minutes and seconds
            long minutes = (elapsedTime / 1000) / 60;
            long seconds = (elapsedTime / 1000) % 60;

            // Display elapsed time in the top right corner
            String timeString = String.format("%02d:%02d", minutes, seconds);
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Elapsed Time: " + timeString, Game.screenWidth - 200, 30);
        }
    }
}
