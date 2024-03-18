package Gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
//import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

import Display.Game;

/**
 * The Game Win class represents the win screen displayed when the game ends.
 * It shows the final score and elapsed time, and provides options to restart the game or quit.
 * 
 * <p>Font source: https://tinyworlds.itch.io/free-pixel-font-thaleah
 */
public class GameWin extends State implements Statemethods {
    
    private static Font customFont;
    private Playing playingState;

    /**
     * Constructs a GameWin object.
     * 
     * @param game the Game object
     */
    public GameWin(Game game) {
        super(game);
        playingState = game.getPlaying();

        try {
            // Load the external font file
            InputStream fontStream = getClass().getClassLoader().getResourceAsStream("fonts/ThaleahFat.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(50f);

            // Register the custom font with the graphics environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace(); // Handle font loading errors            
        }
    }
    
    @Override
    public void update() {

    }

    /**
     * Draws the win screen.
     * 
     * @param g the Graphics object
     */
    @Override
	public void draw(Graphics g) {
        if (customFont != null) {
            g.setFont(customFont);
            g.setColor(Color.BLACK);

            FontMetrics fm = g.getFontMetrics();
            int x = (Game.screenWidth - fm.stringWidth("YOU WIN!")) / 2;
            int y = (Game.screenHeight - fm.getHeight()) / 2 + fm.getAscent() - 40;

            g.drawString("YOU WIN!", x, y);
            
            // Draws score
            y += fm.getHeight();
            x = (Game.screenWidth - fm.stringWidth("SCORE: " + playingState.getScoreObj().getScore())) / 2;
            g.drawString("SCORE: " + playingState.getScoreObj().getScore(), x, y);

            y += fm.getHeight();
            x = (Game.screenWidth - fm.stringWidth("TIME: " + playingState.getTime().getElapsedTime())) / 2;
            g.drawString("TIME: " + playingState.getTime().getElapsedTime(), x, y);


            y += fm.getHeight();  // Move down for the second line
            x = (Game.screenWidth - fm.stringWidth("PRESS R TO RESTART THE GAME")) / 2;
            g.drawString("PRESS R TO RESTART THE GAME", x, y);

            y += fm.getHeight(); // Move down for the third line
            x = (Game.screenWidth - fm.stringWidth("PRESS Q TO QUIT THE GAME")) / 2;
            g.drawString("PRESS Q TO QUIT THE GAME", x, y);
        }
    }

    /**
     * Handles key press event.
     * 
     * @param e the KeyEvent object
     */
    @Override
	public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_R:
                //System.out.println("Restarting the game"); // test
                playingState.restartGame();
                Gamestate.state = Gamestate.PLAYING;
                break;
            case KeyEvent.VK_Q:
                //System.out.println("Exiting the program"); // test
                System.exit(0);
                break;
            default:
                break;
        }
    }

    @Override
	public void keyReleased(KeyEvent e) {

    }
}
