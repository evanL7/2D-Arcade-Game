package Gamestates;

import Display.Game;

/**
 * The State class represents a game state in the application.
 * It provides access to the game object and serves as a base class for specific game states.
 */
public class State {

    protected Game game;

    /**
     * Constructs a State object with the specified game.
     *
     * @param game the game object associated with this state
     */
    public State(Game game) {
        this.game = game;
    }

    /**
     * Returns the game object associated with this state.
     *
     * @return the game object
     */
    public Game getGame() {
        return game;
    }
}
