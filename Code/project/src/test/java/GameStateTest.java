import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import Display.Game;
import Display.Score;
import Gamestates.*;
import Helpers.CollisionChecker;
import Helpers.Position;
import MoveableEntity.Player;
import StaticEntity.Door;
import StaticEntity.Reward;
import StaticEntity.TileManager;
import StaticEntity.Trap;

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
    public void testGameOverExtraState() {
        KeyEvent keyEvent1 = new KeyEvent(game.getGamePanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED);
        menu.keyPressed(keyEvent1); // Start the game

        Playing playing = game.getPlaying();
        TileManager tileManager = new TileManager(playing);      
        CollisionChecker collisionChecker = new CollisionChecker(tileManager); 
        Score scoreObject = new Score(); 
        scoreObject.setScore(0);

        Player player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing, scoreObject);
        Trap trap = new Trap(new Position(2 * Game.tileSize, 3 * Game.tileSize));


        if (collisionChecker.checkPlayerTrapCollision(player, trap)) {
            scoreObject.decreaseScore(1);
            if (scoreObject.getScore() < 0) {
                Gamestate.state = Gamestate.GAMEOVER;
            }
        }
        
        assertEquals(Gamestate.GAMEOVER, Gamestate.state);
    }

    @Test
    public void testWinExtraState() {
        KeyEvent keyEvent1 = new KeyEvent(game.getGamePanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED);
        menu.keyPressed(keyEvent1); // Start the game
        Playing playing = game.getPlaying();
        TileManager tileManager = new TileManager(playing);      
        CollisionChecker collisionChecker = new CollisionChecker(tileManager); 
        Score scoreObject = new Score(); 
        scoreObject.setScore(0);

        Player player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing, scoreObject);
        Reward reward = new Reward(new Position(2 * Game.tileSize, 3 * Game.tileSize));
        Door door = new Door(new Position(2 * Game.tileSize, 3 * Game.tileSize));
        
        if(collisionChecker.checkPlayerRewardCollision(player, reward) ){
            door.setOpen();
            if (door.getOpen() && collisionChecker.checkPlayerDoorCollision(player, door)) {
                Gamestate.state = Gamestate.WIN;
               }
        }

        
        //end
        assertEquals(Gamestate.WIN, Gamestate.state);
    }
}
