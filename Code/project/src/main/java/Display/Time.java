package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Time {

    private long startTime; // when the timer initially started
    private long pausedTime; // when the timer was paused
    private long totalPausedTime; // how long the timer has been paused
    private String timeElapsedStr; // the string representation of time elapsed

    public Time() {
        startTime = System.currentTimeMillis();
        pausedTime = 0;
        totalPausedTime = 0;
        timeElapsedStr = null;
        
        // since the user sees the main menu first so timer should be paused
        pauseTimer(); 
    }

    public void pauseTimer() {
        // takes the time when it was paused
        pausedTime = System.currentTimeMillis();
        System.out.println("The game was paused"); // test
    }

    public void resumeTimer() {
        // calculates total amount of time paused
        totalPausedTime += System.currentTimeMillis() - pausedTime;
        
        // resets the pausedTime
        pausedTime = 0;
        System.out.println("Game unpaused and the total time paused is: " + totalPausedTime); // test
    }

    private void calculateElapsedTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime - totalPausedTime;

        // Convert elapsed time to minutes and seconds
        long minutes = (elapsedTime / 1000) / 60;
        long seconds = (elapsedTime / 1000) % 60;

        // converts the time to a string
        timeElapsedStr = String.format("%02d:%02d", minutes, seconds);
    }
    
    // Getter
    public String getElapsedTime() {
        return timeElapsedStr;
    }

    public void displayElapsedTime(Graphics g) { 
        calculateElapsedTime();
        String elapsedTime = getElapsedTime();

        // Display elapsed time in the top right corner
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Elapsed Time: " + elapsedTime, Game.screenWidth - 200, 30);
    }
}
