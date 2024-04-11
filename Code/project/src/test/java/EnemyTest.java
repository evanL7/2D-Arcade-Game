import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import Animation.AnimationConstants.EnemyConstants;

import static org.junit.jupiter.api.Assertions.*;

import Display.Game;
import Display.GameSettings;
import Gamestates.Playing;
import Helpers.Position;
import Helpers.TileManager;
import Helpers.CollisionChecker;
import MoveableEntity.Enemy;
import MoveableEntity.Player;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EnemyTest {

    private Game game;
    private Playing playing;
    private TileManager tileManager;
    private CollisionChecker collisionChecker;
    private GameSettings gameSettings;

    private Enemy enemy;
    private Player player;

    @BeforeAll
    public void setUp() {
        game = new Game();
        playing = new Playing(game);
        tileManager = new TileManager(playing);
        collisionChecker = new CollisionChecker(tileManager);
        gameSettings = new GameSettings();
    }

    @BeforeEach
    public void reset() {
        enemy = new Enemy(new Position(2 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize()), playing);
        player = new Player(new Position(2 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize()), collisionChecker, playing);
    }

    @Test
    public void testCheckPlayerEnemyCollision() {
        Boolean result = collisionChecker.checkPlayerEnemyCollision(player, enemy);

        assertTrue(result);
    }

    @Test
    public void testEnemyMovesDownWhenPlayerBelow() {
        enemy.setPosition(2 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize());
        player.setPosition(2 * gameSettings.getTileSize(), 5 * gameSettings.getTileSize());
        updateEnemy(enemy, player, 5);

        assertEquals(EnemyConstants.DOWN, enemy.getEnemyAction());
    }

    @Test
    public void testEnemyMovesUpWhenPlayerAbove() {
        enemy.setPosition(2 * gameSettings.getTileSize(), 5 * gameSettings.getTileSize());
        player.setPosition(2 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize());
        updateEnemy(enemy, player, 20);

        assertEquals(EnemyConstants.UP, enemy.getEnemyAction());
    }

    @Test
    public void testEnemyMovesLeftWhenPlayerToTheLeft() {
        enemy.setPosition(5 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize());
        player.setPosition(1 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize());
        updateEnemy(enemy, player, 100);

        assertEquals(EnemyConstants.LEFT, enemy.getEnemyAction());
    }

    @Test
    public void testEnemyMovesRightWhenPlayerToTheRight() {
        enemy.setPosition(2 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize());
        player.setPosition(5 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize());
        updateEnemy(enemy, player, 20);

        assertEquals(EnemyConstants.RIGHT, enemy.getEnemyAction());
    }

    @Test
    public void testEnemyMovesAroundWalls() {
        enemy.setPosition(21 * gameSettings.getTileSize(), 4 * gameSettings.getTileSize());
        player.setPosition(21 * gameSettings.getTileSize(), 8 * gameSettings.getTileSize());
        updateEnemy(enemy, player, 750);

        assertEquals(EnemyConstants.RIGHT, enemy.getEnemyAction());
    }

    private void updateEnemy(Enemy enemy, Player player, int maxNumUpdates) {
        for (int updates = 0; updates < maxNumUpdates; updates++) {
            enemy.update(player);
        }
    }
}
