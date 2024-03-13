// package com.mycompany.app.classes;

// import com.mycompany.app.classes.Display.Game;

// public class Main {
//     public static void main(String[] args) {
//         // Displays game window
//         new Game();
//     }

// }

// Main.java


import javax.swing.*;

import Display.GameMenu;
import Display.GameWindow;

public class Main {
    public static void main(String[] args) {
        // Create an instance of the game menu
        GameMenu gameMenu = new GameMenu();

        // Display menu screen
        int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                new String[]{"Start", "Customize", "Settings", "Exit"}, "Start");

        // Perform action based on user choice
        switch (choice) {
            case 0: // Start
                gameMenu.start();
                break;
            case 1: // Customize
                gameMenu.customize();
                break;
            case 2: // Settings
                gameMenu.settings();
                break;
            case 3: // Exit
                gameMenu.exit();
                break;
            default:
                // Invalid choice, do nothing or handle as needed
                break;
        }
    }
}

