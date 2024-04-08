import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import Display.Game;
import Gamestates.*;

public class GameStateTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testMenuState() {
        State currentState = new Menu(game);
        Gamestate.state = Gamestate.MENU;
        
        assertEquals(Gamestate.MENU, Gamestate.state);
        assertTrue(currentState instanceof Menu);
    }

    @Test
    public void testPlayingState() {
        State currentState = new Playing(game);
        Gamestate.state = Gamestate.PLAYING;

        assertEquals(Gamestate.PLAYING, Gamestate.state);
        assertTrue(currentState instanceof Playing);
    }

    @Test
    public void testGameOverState() {
        State currentState = new GameOver(game);
        Gamestate.state = Gamestate.GAMEOVER;

        assertEquals(Gamestate.GAMEOVER, Gamestate.state);
        assertTrue(currentState instanceof GameOver);
    }

    @Test
    public void testWinState() {
        State currentState = new GameWin(game);
        Gamestate.state = Gamestate.WIN;

        assertEquals(Gamestate.WIN, Gamestate.state);
        assertTrue(currentState instanceof GameWin);
    }
    

}
