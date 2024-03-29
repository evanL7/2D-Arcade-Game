import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import Display.Game;
import Display.Score;
import Gamestates.Playing;
import Helpers.Position;
import Helpers.AnimationConstants.EnemyConstants;
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
        tileManager = new TileManager(playing, Game.mapFilePath);
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
    public void testEnemyFollowsPlayer() {

        // Enemy should move down
        Enemy enemy = new Enemy(new Position(2 * Game.tileSize, 3 * Game.tileSize), playing);
        Player player = new Player(new Position(2 * Game.tileSize, 5 * Game.tileSize), collisionChecker, playing, scoreObject);

        enemy.update(player);
        
        assertEquals(EnemyConstants.DOWN, enemy.getEnemyAction());

        // Enemy should move up
        enemy.getPosition().setX(2 * Game.tileSize);
        enemy.getPosition().setY(5 * Game.tileSize);
        player.getPosition().setX(2 * Game.tileSize);
        player.getPosition().setY(3 * Game.tileSize);

        enemy.update(player);

        assertEquals(EnemyConstants.UP, enemy.getEnemyAction());

        // Enemy should move left
        enemy.getPosition().setX(5 * Game.tileSize);
        enemy.getPosition().setY(3 * Game.tileSize);
        player.getPosition().setX(2 * Game.tileSize);
        player.getPosition().setY(3 * Game.tileSize);

        enemy.update(player);
        enemy.update(player);
        enemy.update(player);

        assertEquals(EnemyConstants.LEFT, enemy.getEnemyAction());

        // Enemy should move right
        enemy.getPosition().setX(2 * Game.tileSize);
        enemy.getPosition().setY(3 * Game.tileSize);
        player.getPosition().setX(5 * Game.tileSize);
        player.getPosition().setY(3 * Game.tileSize);

        enemy.update(player);

        assertEquals(EnemyConstants.RIGHT, enemy.getEnemyAction());
    }
}
