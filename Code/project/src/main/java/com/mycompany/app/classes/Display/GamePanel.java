package com.mycompany.app.classes.Display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.mycompany.app.classes.Helpers.KeyboardInputs;

public class GamePanel extends JPanel {

    // Tile settings
    final static int originalTileSize = 16;
    final static int scale = 3;
    public final static int tileSize = originalTileSize * scale;

    // Grid size
    public final static int maxScreenCol = 16;
    public final static int maxScreenRow = 12;

    // Scaled screen size
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    private Game game;

    public GamePanel(Game game) {
        this.game = game;

        this.setBackground(Color.BLACK);
        this.setPanelSize();
        this.addKeyListener(new KeyboardInputs(this));
    }

    /*
     * Updates the game state.
     */
    public void updateGame() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(screenWidth, screenHeight);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public Game getGame() {
        return game;
    }
}