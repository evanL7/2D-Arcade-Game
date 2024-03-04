package com.mycompany.app.classes.Display;

import javax.swing.JOptionPane;

public class GameMenu {

    // Reference to the game instance
    private Game game;

    // Constructor to initialize the game instance
    public GameMenu(Game game) {
        this.game = game;
    }

    // Function to start the game
    public void start() {
        // Start the game loop directly from Game class
        game.startGameLoop();
    }

    // Function to customize game settings
    public void customize() {
        // Provide customization options
        JOptionPane.showMessageDialog(null, "Customization options will be available in the future.");
    }

    // Function to access game settings
    public void settings() {
        // Provide access to game settings
        JOptionPane.showMessageDialog(null, "Settings menu will be available in the future.");
    }

    // Function to exit the game
    public void exit() {
        // Confirm if the user wants to exit
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Exit the game
            System.exit(0);
        }
    }
}
