package Helpers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Display.GamePanel;
import Gamestates.Gamestate;

/**
 * The KeyboardInputs class implements the KeyListener interface and handles keyboard inputs for the game.
 */
public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    /**
     * Constructs a KeyboardInputs object with the specified GamePanel.
     * @param gamePanel the GamePanel object to associate with the KeyboardInputs
     */
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Invoked when a key is pressed.
     * @param e the KeyEvent object representing the key press event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().keyPressed(e);
                break;
            case WIN:
                gamePanel.getGame().getGameWin().keyPressed(e);
            default:
                break;
        }
    }

    /**
     * Invoked when a key is released.
     * @param e the KeyEvent object representing the key release event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            case GAMEOVER:
                gamePanel.getGame().getGameOver().keyReleased(e);
                break;
            case WIN:
                gamePanel.getGame().getGameWin().keyReleased(e);
                break;
            default:
                break;
        }
    }

    /**
     * Invoked when a key is typed (pressed and released).
     * @param e the KeyEvent object representing the key typed event
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }
}
