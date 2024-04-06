import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import Display.Game;
import Display.Score;
import Gamestates.Gamestate;
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
    private Score scoreObject;

    @BeforeEach
    void setUp() {
        game = new Game();
        playing = new Playing(game);
        tileManager = new TileManager(playing);
        collisionChecker = new CollisionChecker(tileManager);
        scoreObject = new Score();
        Gamestate.state = Gamestate.PLAYING;
    }

    @Test
    public void testDamage() {

        player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing, scoreObject);
        Trap trap = new Trap(new Position(2 * Game.tileSize, 3 * Game.tileSize));

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
        player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing, scoreObject);
        Trap trap1 = new Trap(new Position(2 * Game.tileSize, 3 * Game.tileSize));
        Trap trap2 = new Trap(new Position(4 * Game.tileSize, 5 * Game.tileSize));
        Trap trap3 = new Trap(new Position(6 * Game.tileSize, 7 * Game.tileSize));
        Trap trap4 = new Trap(new Position(2 * Game.tileSize, 5 * Game.tileSize));
        Trap trap5 = new Trap(new Position(6 * Game.tileSize, 3 * Game.tileSize));
        Trap trap6 = new Trap(new Position(4 * Game.tileSize, 3 * Game.tileSize));

        double originalScore = player.getScoreObj().getScore();
        assertTrue(originalScore == 2);

        collisionChecker.checkPlayerTrapCollision(player, trap1);
        player.update();
        playing.update();

        player.setPosition(4 * Game.tileSize, 5 * Game.tileSize);
        collisionChecker.checkPlayerTrapCollision(player, trap2);
        player.update();
        playing.update();

        player.setPosition(6 * Game.tileSize, 7 * Game.tileSize);
        collisionChecker.checkPlayerTrapCollision(player, trap3);
        player.update();
        playing.update();

        player.setPosition(2 * Game.tileSize, 5 * Game.tileSize);
        collisionChecker.checkPlayerTrapCollision(player, trap4);
        player.update();
        playing.update();

        player.setPosition(6 * Game.tileSize, 3 * Game.tileSize);
        collisionChecker.checkPlayerTrapCollision(player, trap5);
        player.update();
        playing.update();

        player.setPosition(4 * Game.tileSize, 3 * Game.tileSize);
        collisionChecker.checkPlayerTrapCollision(player, trap6);
        player.update();
        playing.update();

        double actualScore = player.getScoreObj().getScore();
        double expectedScore = originalScore - (trap1.getDamage() * 6);

        assertEquals(expectedScore, actualScore);
    }

}