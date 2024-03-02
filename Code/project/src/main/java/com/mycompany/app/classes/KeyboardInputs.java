package com.mycompany.app.classes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener{

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
                System.out.println("pressed W");
                break;
            case KeyEvent.VK_A:
                System.out.println("pressed A");
                break;
            case KeyEvent.VK_S:
                System.out.println("pressed S");
                break;
            case KeyEvent.VK_D:
                System.out.println("pressed D");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
