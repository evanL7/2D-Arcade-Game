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
        reward = new Reward(new Position(2 * Game.tileSize, 3 * Game.tileSize));

        Boolean collided = collisionChecker.checkPlayerRewardCollision(player, reward);
        assertTrue(collided);
        int expectedWin = 1;
        int actualWin = player.getWin();

        assertEquals(expectedWin, actualWin);

        // check if resetWin properly sets win variable to 0
        player.resetWin();
        assertEquals(0, player.getWin());
    }

    @Test
    public void checkRightMovement() {
        
        // spawn the player right next to the right wall
        // want to check if going right changes the player's position at all
        player = new Player(new Position(1106, 3 * Game.tileSize), collisionChecker, playing, scoreObject);
        
        // Simulate pressing the "D" key to go right
        // player should be able to move 1 to the right
        player.setRight(true);
        player.setAction(1);
        assertEquals(1, player.getAction());
        assertTrue(player.isRight());

        // check if player moved 1 right
        player.update();
        assertEquals(1107, player.getPosition().getX());
        
    
        // now player should be against the right wall so movement shouldn't be possible
        Position oldPos = new Position(player.getPosition().getX(), player.getPosition().getY());

        player.setRight(true);
        player.setAction(1);
        
        player.update();
        player.update();
        player.update();

        Position newPos = new Position(player.getPosition().getX(), player.getPosition().getY());
        
        // checks if the position changed
        // it shouldn't change since there is a wall in the way
        assertTrue(comparePos(oldPos, newPos));
        player.resetDirBooleans();
    }

    @Test
    public void checkLeftMovement() {
        
        // spawn the player right next to the left wall
        // want to check if going left changes the player's position at all
        player = new Player(new Position(41, 3 * Game.tileSize), collisionChecker, playing, scoreObject);
        
        // Simulate pressing the "A" key to go left
        // player should be able to move 1 to the left
        player.setLeft(true);
        player.setAction(3);
        assertTrue(player.isLeft());
        assertEquals(3, player.getAction());
        
        // check if player moved 1 left
        player.update();
        assertEquals(40, player.getPosition().getX());
        
        player.setLeft(true);
        player.setAction(3);
        // now player should be against the left wall so movement shouldn't be possible
        Position oldPos = new Position(player.getPosition().getX(), player.getPosition().getY());
        
        player.update();
        player.update();
        player.update();

        Position newPos = new Position(player.getPosition().getX(), player.getPosition().getY());
        
        // checks if the position changed
        // it shouldn't change since there is a wall in the way
        assertTrue(comparePos(oldPos, newPos));
        player.resetDirBooleans();
    }

    @Test
    public void checkUpMovement() {
        
        // spawn the player right under the top wall
        // want to check if going up changes the player's position at all
        player = new Player(new Position(2 * Game.tileSize, 129), collisionChecker, playing, scoreObject);
        
        // Simulate pressing the "W" key to go up
        // player should be able to move 1 up
        player.setUp(true);
        player.setAction(0);
        assertTrue(player.isUp());
        assertEquals(0, player.getAction());

        // check if player moved 1 up
        player.update();
        assertEquals(128, player.getPosition().getY());
    
        player.setUp(true);
        player.setAction(0);
        // now player should be against the top wall so movement shouldn't be possible
        Position oldPos = new Position(player.getPosition().getX(), player.getPosition().getY());
        
        player.update();
        player.update();
        player.update();

        Position newPos = new Position(player.getPosition().getX(), player.getPosition().getY());
        
        // checks if the position changed
        // it shouldn't change since there is a wall in the way
        assertTrue(comparePos(oldPos, newPos));
        player.resetDirBooleans();
    }

    @Test
    public void checkDownMovement() {
        
        // spawn the player right above the bottom wall
        // want to check if going down changes the player's position at all
        player = new Player(new Position(2 * Game.tileSize, 1086), collisionChecker, playing, scoreObject);
        
        // Simulate pressing the "S" key to go down
        // player should be able to move 1 down
        player.setDown(true);
        player.setAction(2);
        assertTrue(player.isDown());
        assertEquals(2, player.getAction());

        // check if player moved 1 down
        player.update();
        assertEquals(1087, player.getPosition().getY());

        player.setDown(true);
        player.setAction(2);
        // now player should be against the bottom wall so movement shouldn't be possible
        Position oldPos = new Position(player.getPosition().getX(), player.getPosition().getY());
        
        player.update();
        player.update();
        player.update();

        Position newPos = new Position(player.getPosition().getX(), player.getPosition().getY());
        
        // checks if the position changed
        // it shouldn't change since there is a wall in the way
        assertTrue(comparePos(oldPos, newPos));
        player.resetDirBooleans();
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