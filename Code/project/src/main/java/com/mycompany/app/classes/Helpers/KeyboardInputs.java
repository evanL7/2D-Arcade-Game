package com.mycompany.app.classes.Helpers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.mycompany.app.classes.Display.GamePanel;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    // public static final int UP = 0;
    // public static final int LEFT = 1;
    // public static final int DOWN = 2;
    // public static final int RIGHT = 3;

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
                break;
            case KeyEvent.VK_A:
                isLeftPressed = true;
                gamePanel.setMoving(true);
                break;
            case KeyEvent.VK_S:
                isDownPressed = true;
                gamePanel.setMoving(true);
                break;
            case KeyEvent.VK_D:
                isRightPressed = true;
                gamePanel.setMoving(true);
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
