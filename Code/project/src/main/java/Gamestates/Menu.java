package Gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;

import Display.Game;

/**
 * The Menu class represents the menu state of the game.
 * It displays the game title and allows the player to navigate through the menu.
 * The menu uses a custom font and handles keyboard input to select options.
 * Font source: https://tinyworlds.itch.io/free-pixel-font-thaleah
 */
public class Menu extends State implements Statemethods {

    private Font customFont;
    private int commandNum = 0;

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
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(120f);

            // Register the custom font with the graphics environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace(); // Handle font loading errors            
        }
    }

    @Override
    public void draw(Graphics g) {
        if (customFont != null) {
            // Set the background color to black
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, gameSettings.getScreenWidth(), gameSettings.getScreenHeight());

            // Set the font
            g.setFont(customFont);

            String text = gameSettings.getGameTitle();

            int x = getXCenteredString(g, text);
            int y = gameSettings.getTileSize() * 3;

            // Draw the game title
            drawShadowedString(g, text, x, y, Color.GRAY, Color.WHITE);

            // Draw character sprite
            x = (gameSettings.getScreenWidth() - gameSettings.getTileSize() * 2) / 2;
            y += gameSettings.getTileSize() * 1.25;
            g.drawImage(game.getPlaying().getPlayer().animations[2][1], x, y, gameSettings.getTileSize() * 2, gameSettings.getTileSize() * 2, null);

            // Draw game options
            g.setFont(customFont.deriveFont(60f));
            drawOption(g, "START", getXCenteredString(g, "START"), y += gameSettings.getTileSize() * 3.5, commandNum == 0);
            drawOption(g, "QUIT", getXCenteredString(g, "QUIT"), y += gameSettings.getTileSize(), commandNum == 1);
        }
    }

    public int getXCenteredString(Graphics g, String text) {
        int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        return (gameSettings.getScreenWidth() - length) / 2;
    }

    public void drawShadowedString(Graphics g, String text, int x, int y, Color shadowColor, Color textColor) {
        g.setColor(shadowColor);
        g.drawString(text, x + 5, y + 5); // Draw a shadow
        g.setColor(textColor);
        g.drawString(text, x, y);
    }

    public void drawOption(Graphics g, String text, int x, int y, boolean isSelected) {
        g.drawString(text, x, y);
        if (isSelected) {
            g.drawString(">", x - gameSettings.getTileSize() / 2, y);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            commandNum++;
            if (commandNum > 1) {
                commandNum = 0;
            }
        } else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            commandNum--;
            if (commandNum < 0) {
                commandNum = 1;
            }
        } else if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_SPACE) {
            switch (commandNum) {
                case 0:
                    Gamestate.state = Gamestate.PLAYING;
                    // allows this class to get the time object from Playing and resume the timer
                    Playing playingState = game.getPlaying(); 
                    playingState.windowFocusLost();
                    playingState.getTime().resumeTimer();
                    break;
                case 1:
                    System.exit(0);
                    break;
            }
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
