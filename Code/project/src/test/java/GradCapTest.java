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

public class GradCapTest {
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

        Reward reward = new Reward(new Position(2 * Game.tileSize, 3 * Game.tileSize), 0.5f, 1);

        Boolean result = collisionChecker.checkPlayerRewardCollision(player, reward);
        double score = player.getScoreObj().getScore();

        assertTrue(result);
        assertEquals(2.5, score);
    }

}
