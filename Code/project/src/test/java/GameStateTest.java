import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Display.Game;
import Gamestates.Gamestate;
import Gamestates.Menu;
import Gamestates.Playing;
import Gamestates.State;

public class GameStateTest {

    private Game game;
    private State currentState;

    @Test
    public void testGameStates() {
        game = new Game();
        currentState = new Menu(game); // Start with the Menu state

        // Test Menu state
        assertEquals(Gamestate.MENU, Gamestate.state);

        // Simulate transitioning to the Playing state
        simulateTransitionToPlayingState();
        assertEquals(Gamestate.PLAYING, Gamestate.state);

        // Simulate transitioning to the Menu state
        simulateTransitionToMenuState();
        assertEquals(Gamestate.MENU, Gamestate.state);
    }

    private void simulateTransitionToPlayingState() {
        currentState = new Playing(game);
        Gamestate.state = Gamestate.PLAYING;
    }

    private void simulateTransitionToMenuState() {
        currentState = new Menu(game);
        Gamestate.state = Gamestate.MENU;
    }
}
