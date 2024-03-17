package Display;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Helpers.KeyboardInputs;

public class GamePanel extends JPanel {

    private Game game;
    private Camera camera;
    //private Score score;

    public GamePanel(Game game) {
        this.game = game;
        this.camera = new Camera(game.getPlaying().getPlayer());
        //this.score = new Score();

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
        camera.translate(g); 
        game.render(g);
        camera.reset(g); 
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