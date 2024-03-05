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
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setUp(true);
                gamePanel.getGame().getPlayer().setAction(PlayerConstants.UP);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(true);
                gamePanel.getGame().getPlayer().setAction(PlayerConstants.LEFT);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(true);
                gamePanel.getGame().getPlayer().setAction(PlayerConstants.DOWN);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(true);
                gamePanel.getGame().getPlayer().setAction(PlayerConstants.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
			    gamePanel.getGame().getPlayer().setUp(false);
			    break;
		    case KeyEvent.VK_A:
			    gamePanel.getGame().getPlayer().setLeft(false);
			    break;
		    case KeyEvent.VK_S:
			    gamePanel.getGame().getPlayer().setDown(false);
			    break;
		    case KeyEvent.VK_D:
			    gamePanel.getGame().getPlayer().setRight(false);
			    break;
		}
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
