package com.mycompany.app.classes;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame jframe;

    public GameWindow() {
        jframe = new JFrame();

        jframe.setSize(400, 400);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.setTitle("2D Game");
    }
}
