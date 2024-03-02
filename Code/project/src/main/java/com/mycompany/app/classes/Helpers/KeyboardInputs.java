package com.mycompany.app.classes.Helpers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.mycompany.app.classes.Display.GamePanel;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.changeYDelta(-gamePanel.getTileSize());
                break;
            case KeyEvent.VK_A:
                gamePanel.changeXDelta(-gamePanel.getTileSize());
                break;
            case KeyEvent.VK_S:
                gamePanel.changeYDelta(gamePanel.getTileSize());
                break;
            case KeyEvent.VK_D:
                gamePanel.changeXDelta(gamePanel.getTileSize());
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
