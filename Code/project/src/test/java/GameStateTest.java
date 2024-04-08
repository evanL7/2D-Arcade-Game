import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import Display.Game;
import Gamestates.*;

public class GameStateTest {

    private Game game;
    private Menu menu;

    @BeforeEach
    public void setUp() {
        game = new Game();
        menu = game.getMenu();
        Gamestate.state = Gamestate.MENU; // Reset the game state to MENU before each test
    }

    @Test
    public void testMenuState() {
        assertEquals(Gamestate.MENU, Gamestate.state);
        assertNotNull(menu);
    }

    @Test
    public void testPlayingState() {
        KeyEvent keyEvent = new KeyEvent(game.getGamePanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED);
        menu.keyPressed(keyEvent); // Start the game
        assertEquals(Gamestate.PLAYING, Gamestate.state);
    }

    @Test
    public void testGameOverState() {
        KeyEvent keyEvent1 = new KeyEvent(game.getGamePanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED);
        menu.keyPressed(keyEvent1); // Start the game

        KeyEvent keyEvent2 = new KeyEvent(game.getGamePanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_Q, KeyEvent.CHAR_UNDEFINED);
        game.getPlaying().keyPressed(keyEvent2); // Trigger game over

        assertEquals(Gamestate.GAMEOVER, Gamestate.state);
    }

    @Test
    public void testWinState() {
        KeyEvent keyEvent1 = new KeyEvent(game.getGamePanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED);
        menu.keyPressed(keyEvent1); // Start the game

        KeyEvent keyEvent2 = new KeyEvent(game.getGamePanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_P, KeyEvent.CHAR_UNDEFINED);
        game.getPlaying().keyPressed(keyEvent2); // Trigger win

        assertEquals(Gamestate.WIN, Gamestate.state);
    }
}
