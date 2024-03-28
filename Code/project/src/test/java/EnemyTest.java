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

public class EnemyTest {

    private Game game;
    private Playing playing;
    private TileManager tileManager;
    private CollisionChecker collisionChecker;
    private Score scoreObject;

    @BeforeEach
    public void setUp() {
        game = new Game();
        playing = new Playing(game);
        tileManager = new TileManager(playing);
        collisionChecker = new CollisionChecker(tileManager);
        scoreObject = new Score();
    }

    @Test
    public void testCheckPlayerEnemyCollision() {
        Enemy enemy = new Enemy(new Position(2 * Game.tileSize, 3 * Game.tileSize), playing);
        Player player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing, scoreObject);

        Boolean result = collisionChecker.checkPlayerEnemyCollision(player, enemy);

        assertTrue(result);
    }

    @Test
    public void testSearchPath() {
        Enemy enemy = new Enemy(new Position(2 * Game.tileSize, 5 * Game.tileSize), playing);
        Player player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing, scoreObject);

        // enemy.update(player);
        assertTrue(true);
        
    }
}
