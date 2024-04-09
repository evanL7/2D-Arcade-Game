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

    private static Font customFont;
    int commandNum = 0;

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
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        if (customFont != null) {
            // Set the background color to black
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Game.screenWidth, Game.screenHeight);

            // Set the font
            g.setFont(customFont);

            String text = Game.gameTitle;

            int x = getXCenteredString(g, text);
            int y = Game.tileSize * 3;

            // Draw the game title
            drawShadowedString(g, text, x, y, Color.GRAY, Color.WHITE);

            // Draw character sprite
            x = (Game.screenWidth - Game.tileSize * 2) / 2;
            y += Game.tileSize * 1.25;
            g.drawImage(game.getPlaying().getPlayer().animations[2][1], x, y, Game.tileSize * 2, Game.tileSize * 2, null);

            // Draw game options
            g.setFont(customFont.deriveFont(60f));
            drawOption(g, "START", getXCenteredString(g, "START"), y += Game.tileSize * 3.5, commandNum == 0);
            drawOption(g, "QUIT", getXCenteredString(g, "QUIT"), y += Game.tileSize, commandNum == 1);
        }
    }

    public int getXCenteredString(Graphics g, String text) {
        int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        return (Game.screenWidth - length) / 2;
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
            g.drawString(">", x - Game.tileSize / 2, y);
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
    public void keyReleased(KeyEvent e) {
    }
}
