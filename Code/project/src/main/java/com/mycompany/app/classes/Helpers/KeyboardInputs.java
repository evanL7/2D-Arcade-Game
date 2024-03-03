package com.mycompany.app.classes.Helpers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.mycompany.app.classes.Display.GamePanel;

import com.mycompany.app.classes.Helpers.AnimationConstants.PlayerConstants;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    public static boolean isUpPressed, isDownPressed, isLeftPressed, isRightPressed;

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
                isUpPressed = true;
                gamePanel.setMoving(true);
                gamePanel.setDirection(PlayerConstants.UP);
                break;
            case KeyEvent.VK_A:
                isLeftPressed = true;
                gamePanel.setMoving(true);
                gamePanel.setDirection(PlayerConstants.LEFT);
                break;
            case KeyEvent.VK_S:
                isDownPressed = true;
                gamePanel.setMoving(true);
                gamePanel.setDirection(PlayerConstants.DOWN);
                break;
            case KeyEvent.VK_D:
                isRightPressed = true;
                gamePanel.setMoving(true);
                gamePanel.setDirection(PlayerConstants.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                isUpPressed = false;
                gamePanel.setMoving(false);
                break;
            case KeyEvent.VK_A:
                isLeftPressed = false;
                gamePanel.setMoving(false);
                break;
            case KeyEvent.VK_S:
                isDownPressed = false;
                gamePanel.setMoving(false);
                break;
            case KeyEvent.VK_D:
                isRightPressed = false;
                gamePanel.setMoving(false);
                break;
        }
    }
}
