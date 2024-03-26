import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Display.Game;
import Display.Score;
import Gamestates.Playing;
import Helpers.Position;
import Helpers.CollisionChecker;
import MoveableEntity.Enemy;
import MoveableEntity.Player;
import StaticEntity.TileManager;

public class EnemyTest {

    @Test
    public void testCheckPlayerEnemyCollision() {
        Game game = new Game();
        Playing playing = new Playing(game);
        TileManager tileManager = new TileManager(playing);      
        CollisionChecker collisionChecker = new CollisionChecker(tileManager); 
        Score scoreObject = new Score(); 

        Enemy enemy = new Enemy(new Position(2 * Game.tileSize, 3 * Game.tileSize), playing);
        Player player = new Player(new Position(2 * Game.tileSize, 3 * Game.tileSize), collisionChecker, playing, scoreObject);

        Boolean result = collisionChecker.checkPlayerEnemyCollision(player, enemy);

        assertTrue(result);
    }
}
