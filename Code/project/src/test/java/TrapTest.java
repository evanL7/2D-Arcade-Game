import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

import Display.Game;
import Display.GameSettings;
import Gamestates.Gamestate;
import Gamestates.Playing;
import Helpers.Position;
import Helpers.TileManager;
import Helpers.CollisionChecker;
import MoveableEntity.Player;
import StaticEntity.Trap;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrapTest {

    private Player player;
    private Game game;
    private Playing playing;
    private TileManager tileManager;
    private CollisionChecker collisionChecker;
    private GameSettings gameSettings;

    @BeforeAll
    public void setUpAll() {
        game = new Game();
        playing = new Playing(game);
        tileManager = new TileManager(playing);
        collisionChecker = new CollisionChecker(tileManager);
        gameSettings = new GameSettings();
        Gamestate.state = Gamestate.PLAYING;
    }

    @Test
    public void testDamage() {
        player = new Player(new Position(2 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize()), collisionChecker, playing);
        Trap trap = new Trap(new Position(2 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize()));

        player.getScoreObj().setScore(2.0);

        double originalScore = player.getScoreObj().getScore();
        assertTrue(originalScore == 2);

        Boolean result = collisionChecker.checkPlayerTrapCollision(player, trap);
        double actualScore = player.getScoreObj().getScore();
        double expectedScore = originalScore - trap.getDamage();

        assertTrue(result);
        assertEquals(expectedScore, actualScore);

    }

    @Test
    public void testMultipleTrapPlayerCollisions() {
        player = new Player(new Position(2 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize()), collisionChecker, playing);
        Trap trap1 = new Trap(new Position(2 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize()));
        Trap trap2 = new Trap(new Position(4 * gameSettings.getTileSize(), 5 * gameSettings.getTileSize()));
        Trap trap3 = new Trap(new Position(6 * gameSettings.getTileSize(), 7 * gameSettings.getTileSize()));
        Trap trap4 = new Trap(new Position(2 * gameSettings.getTileSize(), 5 * gameSettings.getTileSize()));
        Trap trap5 = new Trap(new Position(6 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize()));
        Trap trap6 = new Trap(new Position(4 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize()));

        player.getScoreObj().setScore(2.0);
        double originalScore = player.getScoreObj().getScore();
        assertTrue(originalScore == 2);

        collisionChecker.checkPlayerTrapCollision(player, trap1);

        player.setPosition(4 * gameSettings.getTileSize(), 5 * gameSettings.getTileSize());
        collisionChecker.checkPlayerTrapCollision(player, trap2);

        player.setPosition(6 * gameSettings.getTileSize(), 7 * gameSettings.getTileSize());
        collisionChecker.checkPlayerTrapCollision(player, trap3);

        player.setPosition(2 * gameSettings.getTileSize(), 5 * gameSettings.getTileSize());
        collisionChecker.checkPlayerTrapCollision(player, trap4);

        player.setPosition(6 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize());
        collisionChecker.checkPlayerTrapCollision(player, trap5);

        player.setPosition(4 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize());
        collisionChecker.checkPlayerTrapCollision(player, trap6);

        double actualScore = player.getScoreObj().getScore();
        double expectedScore = originalScore - (trap1.getDamage() * 6);

        assertEquals(expectedScore, actualScore);
    }
}