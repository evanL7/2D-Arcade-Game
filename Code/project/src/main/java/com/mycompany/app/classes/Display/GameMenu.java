// GameMenu.java
package com.mycompany.app.classes.Display;

public class GameMenu {

    // Function to start the game
    public void start() {
        // Instantiate the game and start it
        Game game = new Game();
        game.startGameLoop();
    }

    public void customize() {
        // TODO: Implement customization logic
    }

    public void settings() {
        // TODO: Implement settings logic
    }

    public void exit() {
        // Exit the application
        System.exit(0);
    }
}
