package com.mycompany.app.classes.Helpers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.mycompany.app.classes.Display.GamePanel;

import com.mycompany.app.classes.Helpers.AnimationConstants.PlayerConstants;

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
                gamePanel.setAction(PlayerConstants.UP);
                break;
            case KeyEvent.VK_A:
                gamePanel.setAction(PlayerConstants.LEFT);
                break;
            case KeyEvent.VK_S:
                gamePanel.setAction(PlayerConstants.DOWN);
                break;
            case KeyEvent.VK_D:
                gamePanel.setAction(PlayerConstants.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                gamePanel.setMoving(false);
                break;
        }
    }
}
