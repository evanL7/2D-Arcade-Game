package Helpers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Display.GamePanel;
import Gamestates.Gamestate;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

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
                gamePanel.getGame().getGameOver().keyPressed(e);
            default:
                break;
            }
    }

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

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
