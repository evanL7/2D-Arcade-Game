package Helpers;

import Display.Game;
import Gamestates.Playing;
import StaticEntity.Door;
import StaticEntity.Reward;
import StaticEntity.Trap;

/**
 * The AssetManager class manages the assets used in the game.
 * It sets the objects in the game.
 */
public class AssetManager {

    Playing playing;

    public AssetManager(Playing playing) {
        this.playing = playing;
    }

    public void setObjects() {
        playing.staticEntities[0] = new Door(new Position(23 * Game.tileSize, 23 * Game.tileSize));
        playing.staticEntities[1] = new Trap(new Position(9 * Game.tileSize, 9 * Game.tileSize), 1);
        playing.staticEntities[2] = new Trap(new Position(19 * Game.tileSize, 4 * Game.tileSize), 1);
        playing.staticEntities[3] = new Trap(new Position(9 * Game.tileSize, 12 * Game.tileSize), 1);
        playing.staticEntities[4] = new Trap(new Position(4 * Game.tileSize, 18 * Game.tileSize), 1);
        playing.staticEntities[5] = new Trap(new Position(9 * Game.tileSize, 20 * Game.tileSize), 1);
        playing.staticEntities[6] = new Trap(new Position(18 * Game.tileSize, 12 * Game.tileSize), 1);
        playing.staticEntities[7] = new Trap(new Position(15 * Game.tileSize, 16 * Game.tileSize), 1);
        playing.staticEntities[8] = new Reward(new Position(5 * Game.tileSize, 21 * Game.tileSize), 1, 1);
        playing.staticEntities[9] = new Reward(new Position(20 * Game.tileSize, 11 * Game.tileSize), 1, 1);
        playing.staticEntities[10] = new Reward(new Position(21 * Game.tileSize, 4 * Game.tileSize), 1, 1);
    }
}
