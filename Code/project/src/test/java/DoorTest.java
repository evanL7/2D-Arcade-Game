import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Display.Game;
import Gamestates.Gamestate;
import Gamestates.Playing;
import Helpers.CollisionChecker;
import Helpers.Position;
import MoveableEntity.Player;
import StaticEntity.Door;
import StaticEntity.Reward;
import StaticEntity.TileManager;

public class DoorTest {
    private Player player;
    private static Game game;
    private static Playing playing;
    private static TileManager tileManager;
    private static CollisionChecker collisionChecker;

    @BeforeAll
    static void setUpAll() {
        game = new Game();
        playing = new Playing(game);
        tileManager = new TileManager(playing);
        collisionChecker = new CollisionChecker(tileManager);
        Gamestate.state = Gamestate.PLAYING;
    }

    // test for 0 grad caps collected
    @Test
    public void testDoorNoGrapCaps() {
        Door door = new Door(new Position(23 * Game.tileSize, 22 * Game.tileSize));
        player = new Player(new Position(23 * Game.tileSize, 22 * Game.tileSize), collisionChecker, playing);
        assertEquals(0, player.getWin());
        Boolean collideDoor = collisionChecker.checkPlayerDoorCollision(player);

        assertTrue(collideDoor);
        assertEquals(0, player.getWin());
        assertTrue(!door.getOpen());

    }

    // test for 1 grad cap collected
    @Test
    public void testDoorOneGrapCap() {
        Door door = new Door(new Position(23 * Game.tileSize, 22 * Game.tileSize));
        Reward reward = new Reward(new Position(5 * Game.tileSize, 21 * Game.tileSize));
        player = new Player(new Position(5 * Game.tileSize, 21 * Game.tileSize), collisionChecker, playing);
        assertEquals(0, player.getWin());
        boolean collideReward = collisionChecker.checkPlayerRewardCollision(player, reward);
        assertTrue(collideReward);

        player.setPosition(23 * Game.tileSize, 22 * Game.tileSize);

        Boolean collideDoor = collisionChecker.checkPlayerDoorCollision(player);
        assertTrue(collideDoor);
        assertEquals(1, player.getWin());
        assertTrue(!door.getOpen());
    }

    // test for 2 grad caps collected
    @Test
    public void testDoorTwoGrapCaps() {
        Door door = new Door(new Position(23 * Game.tileSize, 22 * Game.tileSize));
        Reward reward1 = new Reward(new Position(5 * Game.tileSize, 21 * Game.tileSize));
        Reward reward2 = new Reward(new Position(20 * Game.tileSize, 11 * Game.tileSize));
        player = new Player(new Position(5 * Game.tileSize, 21 * Game.tileSize), collisionChecker, playing);
        assertEquals(0, player.getWin());
        boolean collideReward1 = collisionChecker.checkPlayerRewardCollision(player, reward1);
        assertTrue(collideReward1);

        player.setPosition(20 * Game.tileSize, 11 * Game.tileSize);
        boolean collideReward2 = collisionChecker.checkPlayerRewardCollision(player, reward2);
        assertTrue(collideReward2);

        player.setPosition(23 * Game.tileSize, 22 * Game.tileSize);
        Boolean collideDoor = collisionChecker.checkPlayerDoorCollision(player);
        assertTrue(collideDoor);
        assertEquals(2, player.getWin());
        assertTrue(!door.getOpen());
    }

    // test for 3 grad caps collected (door opens)
    @Test
    public void testDoorThreeGrapCaps() {
        Door door = new Door(new Position(23 * Game.tileSize, 22 * Game.tileSize));
        Reward reward1 = new Reward(new Position(5 * Game.tileSize, 21 * Game.tileSize));
        Reward reward2 = new Reward(new Position(20 * Game.tileSize, 11 * Game.tileSize));
        Reward reward3 = new Reward(new Position(21 * Game.tileSize, 4 * Game.tileSize));
        player = new Player(new Position(5 * Game.tileSize, 21 * Game.tileSize), collisionChecker, playing);
        assertEquals(0, player.getWin());

        boolean collideReward1 = collisionChecker.checkPlayerRewardCollision(player, reward1);
        assertTrue(collideReward1);

        player.setPosition(20 * Game.tileSize, 11 * Game.tileSize);
        boolean collideReward2 = collisionChecker.checkPlayerRewardCollision(player, reward2);
        assertTrue(collideReward2);

        player.setPosition(21 * Game.tileSize, 4 * Game.tileSize);
        boolean collideReward3 = collisionChecker.checkPlayerRewardCollision(player, reward3);
        assertTrue(collideReward3);

        player.setPosition(23 * Game.tileSize, 22 * Game.tileSize);
        Boolean collideDoor = collisionChecker.checkPlayerDoorCollision(player);

        assertTrue(collideDoor);
        assertEquals(3, player.getWin());

        assertTrue(door.getOpen());
    }
}
