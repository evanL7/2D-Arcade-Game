import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Display.Game;
import Display.Score;
import Gamestates.Playing;
import Helpers.CollisionChecker;
import Helpers.Position;
import MoveableEntity.Player;
import StaticEntity.Reward;
import StaticEntity.TileManager;

public class RewardTest {
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
    }

    @Test
    public void testCheckPlayerRewardCollision() {

        assertTrue(scoreObject.getScore() == 2);
        player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing,
                scoreObject);

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

}
