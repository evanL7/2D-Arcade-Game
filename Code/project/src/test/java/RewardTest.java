import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Display.Game;
import Display.Score;
import Gamestates.Gamestate;
import Gamestates.Playing;
import Helpers.CollisionChecker;
import Helpers.Position;
import MoveableEntity.Player;
import StaticEntity.Reward;
import StaticEntity.TileManager;

public class RewardTest {
    private Player player;
    private static Game game;
    private static Score scoreObject;
    private static Playing playing;
    private static TileManager tileManager;
    private static CollisionChecker collisionChecker;

    @BeforeAll
    static void setUpAll() {
        game = new Game();
        playing = new Playing(game);
        tileManager = new TileManager(playing);
        collisionChecker = new CollisionChecker(tileManager);
        scoreObject = new Score();
        Gamestate.state = Gamestate.PLAYING;
    }

    @Test
    public void testCheckPlayerRewardCollision() {

        assertTrue(scoreObject.getScore() == 2);
        player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing,
                scoreObject);

        player.getScoreObj().setScore(2.0);

        Reward reward = new Reward(new Position(2 * Game.tileSize, 3 * Game.tileSize));

        Boolean result = collisionChecker.checkPlayerRewardCollision(player, reward);
        double score = player.getScoreObj().getScore();

        assertTrue(result);
        assertEquals(2.5, score);
    }

    @Test
    public void testCollectMultipleRewards() {
        assertTrue(scoreObject.getScore() == 2);

        player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing,
                scoreObject);

        player.getScoreObj().setScore(2.0);

        Reward reward1 = new Reward(new Position(2 * Game.tileSize, 3 * Game.tileSize));
        Reward reward2 = new Reward(new Position(4 * Game.tileSize, 5 * Game.tileSize));
        Reward reward3 = new Reward(new Position(6 * Game.tileSize, 7 * Game.tileSize));

        collisionChecker.checkPlayerRewardCollision(player, reward1);

        player.setPosition(4 * Game.tileSize, 5 * Game.tileSize);
        collisionChecker.checkPlayerRewardCollision(player, reward2);

        player.setPosition(6 * Game.tileSize, 7 * Game.tileSize);
        collisionChecker.checkPlayerRewardCollision(player, reward3);

        double score = player.getScoreObj().getScore();

        assertEquals(3.5, score);
    }

    @Test
    public void testBonusReward() {
        player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing,
                scoreObject);
        Reward bonusReward = new Reward(new Position(4 * Game.tileSize, 5 * Game.tileSize), 4000);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // Handle interrupted exception
            e.printStackTrace();
        }

        player.setPosition(4 * Game.tileSize, 5 * Game.tileSize);

        Boolean collide = collisionChecker.checkPlayerRewardCollision(player, bonusReward);

        assertTrue(scoreObject.getScore() == 2);
        assertTrue(collide);

    }

    @Test
    public void testBonusRewardCollide() {
        player = new Player(new Position(4 * Game.tileSize, 5 * Game.tileSize), collisionChecker, playing,
                scoreObject);
        Reward bonusReward = new Reward(new Position(4 * Game.tileSize, 5 * Game.tileSize), 4000);

        Boolean collide = collisionChecker.checkPlayerRewardCollision(player, bonusReward);

        double actualScore = player.getScoreObj().getScore();
        double expectedScore = 3.0;

        assertEquals(actualScore, expectedScore);
        assertTrue(collide);

    }

}
