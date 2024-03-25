package Helpers;

import java.util.Random;

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
        playing.staticEntities[11] = new Reward(generateRandomPosition(), 4000, 1, 0);
        playing.staticEntities[12] = new Reward(generateRandomPosition(), 4000, 1, 0);
    }

    private Position generateRandomPosition() {
        Random rand = new Random();
        while (true) {
            int randomX = rand.nextInt(Game.maxWorldCol);
            int randomY = rand.nextInt(Game.maxWorldRow);
            Position position = new Position(randomX * Game.tileSize, randomY * Game.tileSize);
            // Regenerates a new random position if the position is invalid
            if (isPositionValid(position)) {
                return position;
            }
        }
    }

    public boolean isPositionValid(Position position) {
        // Check if the position conflicts with an existing static entity
        for (int i = 0; i < playing.staticEntities.length; i++) {
            if (playing.staticEntities[i] != null && playing.staticEntities[i].getPosition().equals(position)) {
                return false;
            }
        }
        // Check if the position conflicts with a map tile
        if (playing.tileManager.mapTileNum[position.getY() / Game.tileSize][position.getX() / Game.tileSize] != 0) {
            return false;
        }
        // Check if the position conflicts with the player's location
        if (position.equals(playing.getPlayer().getPosition())) {
            return false;
        }
        return true;
    }
}
