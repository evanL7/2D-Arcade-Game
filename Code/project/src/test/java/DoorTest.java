import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import Display.Game;
import Display.GameSettings;
import Gamestates.Gamestate;
import Gamestates.Playing;
import Helpers.CollisionChecker;
import Helpers.Position;
import Helpers.TileManager;
import MoveableEntity.Player;
import StaticEntity.Door;
import StaticEntity.Reward;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DoorTest {
    
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

    // test for 0 grad caps collected
    @Test
    public void testDoorNoGrapCaps() {
        Door door = new Door(new Position(23 * gameSettings.getTileSize(), 22 * gameSettings.getTileSize()));
        player = new Player(new Position(23 * gameSettings.getTileSize(), 22 * gameSettings.getTileSize()), collisionChecker, playing);
        assertEquals(0, player.getWin());
        Boolean collideDoor = collisionChecker.checkPlayerDoorCollision(player);

        assertTrue(collideDoor);
        assertEquals(0, player.getWin());
        assertTrue(!door.getOpen());

    }

    // test for 1 grad cap collected
    @Test
    public void testDoorOneGrapCap() {
        Door door = new Door(new Position(23 * gameSettings.getTileSize(), 22 * gameSettings.getTileSize()));
        Reward reward = new Reward(new Position(5 * gameSettings.getTileSize(), 21 * gameSettings.getTileSize()));
        player = new Player(new Position(5 * gameSettings.getTileSize(), 21 * gameSettings.getTileSize()), collisionChecker, playing);
        assertEquals(0, player.getWin());
        boolean collideReward = collisionChecker.checkPlayerRewardCollision(player, reward);
        assertTrue(collideReward);

        player.setPosition(23 * gameSettings.getTileSize(), 22 * gameSettings.getTileSize());

        Boolean collideDoor = collisionChecker.checkPlayerDoorCollision(player);
        assertTrue(collideDoor);
        assertEquals(1, player.getWin());
        assertTrue(!door.getOpen());
    }

    // test for 2 grad caps collected
    @Test
    public void testDoorTwoGrapCaps() {
        Door door = new Door(new Position(23 * gameSettings.getTileSize(), 22 * gameSettings.getTileSize()));
        Reward reward1 = new Reward(new Position(5 * gameSettings.getTileSize(), 21 * gameSettings.getTileSize()));
        Reward reward2 = new Reward(new Position(20 * gameSettings.getTileSize(), 11 * gameSettings.getTileSize()));
        player = new Player(new Position(5 * gameSettings.getTileSize(), 21 * gameSettings.getTileSize()), collisionChecker, playing);
        assertEquals(0, player.getWin());
        boolean collideReward1 = collisionChecker.checkPlayerRewardCollision(player, reward1);
        assertTrue(collideReward1);

        player.setPosition(20 * gameSettings.getTileSize(), 11 * gameSettings.getTileSize());
        boolean collideReward2 = collisionChecker.checkPlayerRewardCollision(player, reward2);
        assertTrue(collideReward2);

        player.setPosition(23 * gameSettings.getTileSize(), 22 * gameSettings.getTileSize());
        Boolean collideDoor = collisionChecker.checkPlayerDoorCollision(player);
        assertTrue(collideDoor);
        assertEquals(2, player.getWin());
        assertTrue(!door.getOpen());
    }

    // test for 3 grad caps collected (door opens)
    @Test
    public void testDoorThreeGrapCaps() {
        Door door = new Door(new Position(23 * gameSettings.getTileSize(), 22 * gameSettings.getTileSize()));
        Reward reward1 = new Reward(new Position(5 * gameSettings.getTileSize(), 21 * gameSettings.getTileSize()));
        Reward reward2 = new Reward(new Position(20 * gameSettings.getTileSize(), 11 * gameSettings.getTileSize()));
        Reward reward3 = new Reward(new Position(21 * gameSettings.getTileSize(), 4 * gameSettings.getTileSize()));
        player = new Player(new Position(5 * gameSettings.getTileSize(), 21 * gameSettings.getTileSize()), collisionChecker, playing);
        assertEquals(0, player.getWin());

        boolean collideReward1 = collisionChecker.checkPlayerRewardCollision(player, reward1);
        assertTrue(collideReward1);

        player.setPosition(20 * gameSettings.getTileSize(), 11 * gameSettings.getTileSize());
        boolean collideReward2 = collisionChecker.checkPlayerRewardCollision(player, reward2);
        assertTrue(collideReward2);

        player.setPosition(21 * gameSettings.getTileSize(), 4 * gameSettings.getTileSize());
        boolean collideReward3 = collisionChecker.checkPlayerRewardCollision(player, reward3);
        assertTrue(collideReward3);

        player.setPosition(23 * gameSettings.getTileSize(), 22 * gameSettings.getTileSize());
        Boolean collideDoor = collisionChecker.checkPlayerDoorCollision(player);

        assertTrue(collideDoor);
        assertEquals(3, player.getWin());

        assertTrue(door.getOpen());
    }
}
