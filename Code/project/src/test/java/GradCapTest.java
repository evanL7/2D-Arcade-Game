import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testCheckPlayerRewardCollision() {
        Game game = new Game();
        Playing playing = new Playing(game);
        TileManager tileManager = new TileManager(playing);
        CollisionChecker collisionChecker = new CollisionChecker(tileManager);
        Score scoreObject = new Score();

        Player player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing,
                scoreObject);

        Reward reward = new Reward(new Position(2 * Game.tileSize, 3 * Game.tileSize), 1, 1);

        Boolean result = collisionChecker.checkPlayerRewardCollision(player, reward);
        double score = scoreObject.getScore();

        assertTrue(result);
        assertTrue(score == 3.00);
    }
}
