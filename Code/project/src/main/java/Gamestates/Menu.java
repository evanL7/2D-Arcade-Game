package Gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
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
            g.setColor(Color.GRAY);
            g.drawString(text, x+5, y+5); // Draw a shadow
            g.setColor(Color.WHITE);
            g.drawString(text, x, y);

            // Draw character sprite
            x = (Game.screenWidth - Game.tileSize * 2) / 2;
            y += Game.tileSize * 1.25;
            g.drawImage(game.getPlaying().getPlayer().animations[2][1], x, y, Game.tileSize * 2, Game.tileSize * 2, null);

            // Draw game options
            g.setFont(customFont.deriveFont(60f));
            text = "START";
            x = getXCenteredString(g, text);
            y += Game.tileSize * 3.5;
            g.drawString(text, x, y);
            if (commandNum == 0) {
                g.drawString(">", x - Game.tileSize / 2, y);
            }

            // text = "SETTINGS";
            // x = getXCenteredString(g, text);
            // y += Game.tileSize;
            // g.drawString(text, x, y);
            // if (commandNum == 1) {
            //     g.drawString(">", x - Game.tileSize / 2, y);
            // }

            text = "QUIT";
            x = getXCenteredString(g, text);
            y += Game.tileSize;
            g.drawString(text, x, y);
            if (commandNum == 1) {
                g.drawString(">", x - Game.tileSize / 2, y);
            }
        }
    }

    public int getXCenteredString(Graphics g, String text) {
        int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        return (Game.screenWidth - length) / 2;
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
                // case 1:
                //     Gamestate.state = Gamestate.OPTIONS;
                //     break;
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
