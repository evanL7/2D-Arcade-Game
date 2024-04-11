import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
import StaticEntity.Reward;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RewardTest {
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
        gameSettings = playing.getGame().getGameSettings();
        Gamestate.state = Gamestate.PLAYING;
    }

    @BeforeEach
    public void setUp() {
        player = new Player(new Position(2 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize()), collisionChecker, playing);
        player.getScoreObj().setScore(0);
    }

    @Test
    public void testCheckPlayerRewardCollision() {
        Reward reward = new Reward(new Position(2 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize()));

        double originalScore = player.getScoreObj().getScore();
        Boolean result = collisionChecker.checkPlayerRewardCollision(player, reward);
        double actualScore = player.getScoreObj().getScore();
        double expectedScore = originalScore + reward.getRewardAmount();

        assertTrue(result);
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testCollectMultipleRewards() {
        Reward reward1 = new Reward(new Position(2 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize()));
        Reward reward2 = new Reward(new Position(4 * gameSettings.getTileSize(), 5 * gameSettings.getTileSize()));
        Reward reward3 = new Reward(new Position(6 * gameSettings.getTileSize(), 7 * gameSettings.getTileSize()));

        double originalScore = player.getScoreObj().getScore();
        collisionChecker.checkPlayerRewardCollision(player, reward1);

        player.setPosition(4 * gameSettings.getTileSize(), 5 * gameSettings.getTileSize());
        collisionChecker.checkPlayerRewardCollision(player, reward2);

        player.setPosition(6 * gameSettings.getTileSize(), 7 * gameSettings.getTileSize());
        collisionChecker.checkPlayerRewardCollision(player, reward3);

        double actualScore = player.getScoreObj().getScore();
        double expectedScore = originalScore + (3 * reward1.getRewardAmount());

        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testBonusReward() {
        Reward bonusReward = new Reward(new Position(4 * gameSettings.getTileSize(), 5 * gameSettings.getTileSize()), 4000);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // Handle interrupted exception
            e.printStackTrace();
        }

        player.setPosition(4 * gameSettings.getTileSize(), 5 * gameSettings.getTileSize());

        Boolean collide = collisionChecker.checkPlayerRewardCollision(player, bonusReward);
        // assertTrue(scoreObject.getScore() == 2);
        assertTrue(collide);
    }

    @Test
    public void testBonusRewardCollide() {
        player = new Player(new Position(4 * gameSettings.getTileSize(), 5 * gameSettings.getTileSize()), collisionChecker, playing);
        Reward bonusReward = new Reward(new Position(4 * gameSettings.getTileSize(), 5 * gameSettings.getTileSize()), 4000);
        double originalScore = player.getScoreObj().getScore();

        Boolean collide = collisionChecker.checkPlayerRewardCollision(player, bonusReward);

        double actualScore = player.getScoreObj().getScore();
        double expectedScore = originalScore + bonusReward.getRewardAmount();

        assertEquals(expectedScore, actualScore);
        assertTrue(collide);
    }

}
