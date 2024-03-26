import static org.junit.jupiter.api.Assertions.*;
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
        assertTrue(scoreObject.getScore() == 2);
        Player player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing,
                scoreObject);

        Reward reward = new Reward(new Position(2 * Game.tileSize, 3 * Game.tileSize), 1, 1);

        Boolean result = collisionChecker.checkPlayerRewardCollision(player, reward);
        double score = player.getScoreObj().getScore();
        
        assertTrue(result);
        assertEquals(3, score);
    }
}
