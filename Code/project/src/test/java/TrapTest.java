import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import Display.Game;
import Display.Score;
import Gamestates.Playing;
import Helpers.Position;
import Helpers.CollisionChecker;
import MoveableEntity.Player;
import StaticEntity.TileManager;
import StaticEntity.Trap;


public class TrapTest {
    
    private Player player;
    private Game game;
    private Playing playing;
    private TileManager tileManager;
    private CollisionChecker collisionChecker;
    private Trap trap;
    private Score scoreObject;

    @BeforeEach
    void setUp() {
        game = new Game();
        playing = new Playing(game);
        tileManager = new TileManager(playing);      
        collisionChecker = new CollisionChecker(tileManager);
        scoreObject = new Score();  
    }

    @Test
    public void testDamage() {
        
        player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing, scoreObject);
        trap = new Trap(new Position(2 * Game.tileSize, 3 * Game.tileSize));

        double originalScore = player.getScoreObj().getScore();
        assertTrue(originalScore == 2);
        
        Boolean result = collisionChecker.checkPlayerTrapCollision(player, trap);
        double actualScore = player.getScoreObj().getScore();        
        double expectedScore = originalScore - trap.getDamage();

        assertTrue(result);
        assertEquals(expectedScore, actualScore);
        
    }
    
}