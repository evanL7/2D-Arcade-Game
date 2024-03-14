package Display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JPanel;

import Helpers.KeyboardInputs;

public class GamePanel extends JPanel {

    private Game game;
    private Camera camera;
    
    // new
    private long startTime;

    public GamePanel(Game game) {
        this.game = game;
        this.camera = new Camera(game.getPlaying().getPlayer());

        // new
        this.startTime = System.currentTimeMillis();

        this.setPanelSize();
        this.addKeyListener(new KeyboardInputs(this));
    }

    /*
     * Updates the game state.
     */
    public void updateGame() {
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // new
        displayElapsedTime(g);
        camera.translate(g); //add
        game.render(g);
        camera.reset(g); //add
    }

    private void displayElapsedTime(Graphics g) {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        // Convert elapsed time to minutes and seconds
        long minutes = (elapsedTime / 1000) / 60;
        long seconds = (elapsedTime / 1000) % 60;

        // Display elapsed time in the bottom right corner
        String timeString = String.format("%02d:%02d", minutes, seconds);
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("Elapsed Time: " + timeString, getWidth() - 150, getHeight() - 20);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(Game.screenWidth, Game.screenHeight);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public Game getGame() {
        return game;
    }
}