package com.mycompany.app.classes.Display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.mycompany.app.classes.Helpers.KeyboardInputs;

public class GamePanel extends JPanel {

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
        Dimension size = new Dimension(Game.screenWidth, Game.screenHeight);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public Game getGame() {
        return game;
    }
}