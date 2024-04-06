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

    private Enemy enemy;
    private Player player;

    @BeforeEach
    public void setUp() {
        game = new Game();
        playing = new Playing(game);
        tileManager = new TileManager(playing);
        collisionChecker = new CollisionChecker(tileManager);
        scoreObject = new Score();

        enemy = new Enemy(new Position(2 * Game.tileSize, 3 * Game.tileSize), playing);
        player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing, scoreObject);
    }

    @Test
    public void testCheckPlayerEnemyCollision() {
        Boolean result = collisionChecker.checkPlayerEnemyCollision(player, enemy);

        assertTrue(result);
    }

    @Test
    public void testEnemyMovesDownWhenPlayerBelow() {
        enemy.setPosition(2 * Game.tileSize, 3 * Game.tileSize);
        player.setPosition(2 * Game.tileSize, 5 * Game.tileSize);
        updateEnemy(enemy, player, 5);

        assertEquals(EnemyConstants.DOWN, enemy.getEnemyAction());
    }

    @Test
    public void testEnemyMovesUpWhenPlayerAbove() {
        enemy.setPosition(2 * Game.tileSize, 5 * Game.tileSize);
        player.setPosition(2 * Game.tileSize, 3 * Game.tileSize);
        updateEnemy(enemy, player, 20);

        assertEquals(EnemyConstants.UP, enemy.getEnemyAction());
    }

    @Test
    public void testEnemyMovesLeftWhenPlayerToTheLeft() {
        enemy.setPosition(5 * Game.tileSize, 3 * Game.tileSize);
        player.setPosition(1 * Game.tileSize, 3 * Game.tileSize);
        updateEnemy(enemy, player, 100);

        assertEquals(EnemyConstants.LEFT, enemy.getEnemyAction());
    }

    @Test
    public void testEnemyMovesRightWhenPlayerToTheRight() {
        enemy.setPosition(2 * Game.tileSize, 3 * Game.tileSize);
        player.setPosition(5 * Game.tileSize, 3 * Game.tileSize);
        updateEnemy(enemy, player, 20);

        assertEquals(EnemyConstants.RIGHT, enemy.getEnemyAction());
    }

    @Test
    public void testEnemyMovesAroundWalls() {
        enemy.setPosition(21 * Game.tileSize, 4 * Game.tileSize);
        player.setPosition(21 * Game.tileSize, 8 * Game.tileSize);
        updateEnemy(enemy, player, 750);

        assertEquals(EnemyConstants.RIGHT, enemy.getEnemyAction());
    }

    private void updateEnemy(Enemy enemy, Player player, int maxNumUpdates) {
        int updates = 0;
        while (updates < maxNumUpdates) {
            enemy.update(player);
            updates++;
        }
    }
}
