package com.mycompany.app.classes;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;

    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();

        jframe.setSize(gamePanel.getScreenWidth(), gamePanel.getScreenHeight());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.add(gamePanel);
        jframe.setVisible(true);
        jframe.setTitle("2D Game");
    }
}
