package Display;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Helpers.KeyboardInputs;

/**
 * The GamePanel class represents the panel where the game is displayed.
 * It extends the JPanel class and provides methods for updating and rendering the game.
 */
public class GamePanel extends JPanel {

    private Game game;
    private Camera camera;
    private GameSettings gameSettings;

    /**
     * Constructs a GamePanel object with the specified game.
     * Initializes the camera and sets the panel size.
     * Adds a key listener for keyboard inputs.
     * 
     * @param game the game object
     */
    public GamePanel(Game game) {
        this.game = game;
        this.camera = new Camera(game.getPlaying().getPlayer());
        this.gameSettings = new GameSettings();

        this.setPanelSize();
        this.addKeyListener(new KeyboardInputs(this));
    }

    /**
     * Renders the game on the panel.
     * This method is called by the paintComponent method to draw the game graphics.
     * 
     * @param g the graphics object
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        camera.translate(g); 
        game.render(g);
        camera.reset(g); 
    }

    /**
     * Sets the size of the panel to the screen size of the game.
     */
    private void setPanelSize() {
        Dimension size = new Dimension(gameSettings.getScreenWidth(), gameSettings.getScreenHeight());
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    /**
     * Returns the game object associated with the panel.
     * 
     * @return the game object
     */
    public Game getGame() {
        return game;
    }
}