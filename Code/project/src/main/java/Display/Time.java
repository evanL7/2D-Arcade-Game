package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import Gamestates.Gamestate;

public class Time {

    public long startTime;
    private Game game;

    public Time()
    {
        startTime = System.currentTimeMillis();
    }

    public void displayElapsedTime(Graphics g) {
        if (Gamestate.state == Gamestate.PLAYING)
        {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;

            // Convert elapsed time to minutes and seconds
            long minutes = (elapsedTime / 1000) / 60;
            long seconds = (elapsedTime / 1000) % 60;

            // Display elapsed time in the bottom right corner
            String timeString = String.format("%02d:%02d", minutes, seconds);
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Elapsed Time: " + timeString, game.screenWidth - 200, 30);
        }
    }
}
