import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Display.Game;
import Gamestates.*;

public class GameStateTest {

    private Game game;

    @Test
    public void testMenuState() {
        game = new Game();
        State currentState = new Menu(game);
        Gamestate.state = Gamestate.MENU;
        
        assertEquals(Gamestate.MENU, Gamestate.state);
        assertTrue(currentState instanceof Menu);
    }

    @Test
    public void testPlayingState() {
        game = new Game();
        State currentState = new Playing(game);
        Gamestate.state = Gamestate.PLAYING;

        assertEquals(Gamestate.PLAYING, Gamestate.state);
        assertTrue(currentState instanceof Playing);
    }

    @Test
    public void testGameOverState() {
        game = new Game();
        State currentState = new GameOver(game);
        Gamestate.state = Gamestate.GAMEOVER;

        assertEquals(Gamestate.GAMEOVER, Gamestate.state);
        assertTrue(currentState instanceof GameOver);
    }

    @Test
    public void testWinState() {
        game = new Game();
        State currentState = new GameWin(game);
        Gamestate.state = Gamestate.WIN;

        assertEquals(Gamestate.WIN, Gamestate.state);
        assertTrue(currentState instanceof GameWin);
    }

}
