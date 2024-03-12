package com.mycompany.app.classes.Helpers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import com.mycompany.app.classes.Display.GamePanel;
import com.mycompany.app.classes.Helpers.AnimationConstants.PlayerConstants;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    private HashSet<Integer> keysPressed;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.keysPressed = new HashSet<>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (!keysPressed.contains(keyCode)) {
            keysPressed.add(keyCode);
            handleKeys();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keysPressed.remove(keyCode);
        handleKeys();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void handleKeys() {
        // Handle keys based on the current state of keysPressed
        boolean up = keysPressed.contains(KeyEvent.VK_W);
        boolean left = keysPressed.contains(KeyEvent.VK_A);
        boolean down = keysPressed.contains(KeyEvent.VK_S);
        boolean right = keysPressed.contains(KeyEvent.VK_D);
        
        // Set player actions based on the keys pressed
        gamePanel.getGame().getPlayer().setUp(up);
        gamePanel.getGame().getPlayer().setLeft(left);
        gamePanel.getGame().getPlayer().setDown(down);
        gamePanel.getGame().getPlayer().setRight(right);

        // Determine the primary direction based on the last key pressed
        int action = gamePanel.getGame().getPlayer().getAction(); // Default action
        if (up) {
            action = PlayerConstants.UP;
        } else if (left) {
            action = PlayerConstants.LEFT;
        } else if (down) {
            action = PlayerConstants.DOWN;
        } else if (right) {
            action = PlayerConstants.RIGHT;
        }
        gamePanel.getGame().getPlayer().setAction(action);
    }
}
