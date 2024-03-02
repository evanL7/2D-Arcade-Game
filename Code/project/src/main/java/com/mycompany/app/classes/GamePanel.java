package com.mycompany.app.classes;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    // Screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;

    // Maze size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    
    // Scaled screen size
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public GamePanel() {
        this.setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
