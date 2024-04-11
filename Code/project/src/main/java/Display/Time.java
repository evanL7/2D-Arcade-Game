package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * The Time class represents a timer used in the game.
 * It tracks the elapsed time and provides methods to pause, resume, and display the elapsed time.
 */
public class Time {

    private long startTime; // when the timer initially started
    private long pausedTime; // when the timer was paused
    private long totalPausedTime; // how long the timer has been paused
    private String timeElapsedStr; // the string representation of time elapsed

    private GameSettings gameSettings;

    /**
     * Constructs a Time object and initializes the timer.
     * The timer is initially paused.
     */
    public Time() {
        startTime = System.currentTimeMillis();
        pausedTime = 0;
        totalPausedTime = 0;
        timeElapsedStr = null;
        gameSettings = new GameSettings();

        // since the user sees the main menu first so timer should be paused
        pauseTimer(); 
    }

    /**
     * Pauses the timer and records the time at which it was paused.
     */
    public void pauseTimer() {
        // takes the time when it was paused
        pausedTime = System.currentTimeMillis();
        //System.out.println("The game was paused"); // test
    }

    /**
     * Resumes the timer and updates the total paused time.
     */
    public void resumeTimer() {
        // calculates total amount of time paused
        totalPausedTime += System.currentTimeMillis() - pausedTime;
        
        // resets the pausedTime
        pausedTime = 0;
        //System.out.println("Game unpaused and the total time paused is: " + totalPausedTime); // test
    }

    /**
     * Calculates the elapsed time since the timer started, excluding paused time.
     * Updates the string representation of elapsed time.
     */
    private void calculateElapsedTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime - totalPausedTime;

        // Convert elapsed time to minutes and seconds
        long minutes = (elapsedTime / 1000) / 60;
        long seconds = (elapsedTime / 1000) % 60;

        // converts the time to a string
        timeElapsedStr = String.format("%02d:%02d", minutes, seconds);
    }
    
    /**
     * Gets the string representation of the elapsed time.
     * 
     * @return The elapsed time as a formatted string (mm:ss).
     */
    public String getElapsedTime() {
        return timeElapsedStr;
    }

    /**
     * Displays the elapsed time on the screen.
     * 
     * @param g The Graphics object used for rendering.
     */
    public void displayElapsedTime(Graphics g) { 
        calculateElapsedTime();
        String elapsedTime = getElapsedTime();

        // Display elapsed time in the top right corner
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Elapsed Time: " + elapsedTime, gameSettings.getScreenWidth() - 200, 30);
    }
}
