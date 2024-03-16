package Gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

import Display.Game;

/**
 * The Menu class represents the main menu of the game.
 * It currently displays options for starting the game or returning back.
 * Font source: https://tinyworlds.itch.io/free-pixel-font-thaleah
 */
public class Menu extends State implements Statemethods {

    private static Font customFont;
    private boolean firstTime = true;

    /**
     * Constructs a Menu object.
     * 
     * @param game the Game object
     */
    public Menu(Game game) {
        super(game);

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

    @Override
    public void draw(Graphics g) {
        if (customFont != null) {
            g.setFont(customFont);
            g.setColor(Color.BLACK);

            FontMetrics fm = g.getFontMetrics();
            int x = (Game.screenWidth - fm.stringWidth("PRESS ENTER TO START")) / 2;
            int y = (Game.screenHeight - fm.getHeight()) / 2 + fm.getAscent() - 40;

            g.drawString("PRESS ENTER TO START", x, y);

            y += fm.getHeight();  // Move down for the second line
            x = (Game.screenWidth - fm.stringWidth("PRESS ESC TO RETURN BACK")) / 2;
            g.drawString("PRESS ESC TO RETURN BACK", x, y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.state = Gamestate.PLAYING;

            // allows this class to get the time object from Playing and resume the timer
            Playing playingState = game.getPlaying(); 
            playingState.getTime().resumeTimer();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
