import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import Display.Game;
import Display.Score;
import Gamestates.Playing;
import Helpers.Position;
import Helpers.CollisionChecker;
import MoveableEntity.Enemy;
import MoveableEntity.Player;
import StaticEntity.TileManager;
import StaticEntity.Reward;


public class PlayerTest {
    
    private Player player;
    private Game game;
    private Score scoreObject;
    private Playing playing;
    private TileManager tileManager;
    private CollisionChecker collisionChecker;
    private Reward reward;

    @BeforeEach
    void setUp() {
        game = new Game();
        playing = new Playing(game);
        tileManager = new TileManager(playing);      
        collisionChecker = new CollisionChecker(tileManager); 
        scoreObject = new Score(); 
    }

    // Tests to see if colliding with a regular reward properly increments 
    // the number of regular rewards collected (variable win)
    @Test
    public void testCheckRewardCollision() {
        
        player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing, scoreObject);
        reward = new Reward(new Position(2 * Game.tileSize, 3 * Game.tileSize), 1, 1);

        Boolean collided = collisionChecker.checkPlayerRewardCollision(player, reward);
        assertTrue(collided);
        int expectedWin = 1;
        int actualWin = player.getWin();

        assertEquals(expectedWin, actualWin);
    }

    @Test
    public void checkRightWallCollision() {
        
        // spawn the player right next to the right wall
        // want to check if going right changes the player's position at all
        player = new Player(new Position(1108, 3 * Game.tileSize), collisionChecker, playing, scoreObject);
        
        // Simulate pressing the "D" key to go right
        player.setRight(true);

        // Check if the right variable is set to true
        assertTrue(player.isRight());
    
        Position oldPos = new Position(player.getPosition().getX(), player.getPosition().getY());
        //System.out.println("OldPos is: " + oldPos.toString());
        
        player.update();
        player.update();
        player.update();

        Position newPos = new Position(player.getPosition().getX(), player.getPosition().getY());
        //System.out.println("NewPos is: " + newPos.toString());
        
        assertTrue(comparePos(oldPos, newPos));
    }

    // helper to check before and after positions
    private boolean comparePos(Position pos1, Position pos2) {
        if (pos1 == null || pos2 == null) {
            return false;
        }

        int x1 = pos1.getX();
        int y1 = pos1.getY();
            
        int x2 = pos2.getX();
        int y2 = pos2.getY();

        return (x1 == x2 && y1 == y2);
    }
}