package Helpers;

import java.util.Random;

import Display.GameSettings;
import Gamestates.Playing;
import StaticEntity.Door;
import StaticEntity.Reward;
import StaticEntity.Trap;

/**
 * The AssetManager class manages the assets used in the game.
 * It sets the static entity objects in the game.
 */
public class AssetManager {

    private Playing playing;
    private GameSettings gameSettings;
    private Random rand;

    public AssetManager(Playing playing) {
        this.playing = playing;
        this.gameSettings = new GameSettings();
        this.rand = new Random();
    }

    /**
     * Sets the objects in the game world.
     */
    public void setObjects() {
        playing.staticEntities[0] = new Door(new Position(23 * gameSettings.getTileSize(), 22 * gameSettings.getTileSize()));
        playing.staticEntities[1] = new Trap(new Position(9 * gameSettings.getTileSize(), 9 * gameSettings.getTileSize()));
        playing.staticEntities[2] = new Trap(new Position(19 * gameSettings.getTileSize(), 4 * gameSettings.getTileSize()));
        playing.staticEntities[3] = new Trap(new Position(9 * gameSettings.getTileSize(), 12 * gameSettings.getTileSize()));
        playing.staticEntities[4] = new Trap(new Position(4 * gameSettings.getTileSize(), 18 * gameSettings.getTileSize()));
        playing.staticEntities[5] = new Trap(new Position(9 * gameSettings.getTileSize(), 20 * gameSettings.getTileSize()));
        playing.staticEntities[6] = new Trap(new Position(18 * gameSettings.getTileSize(), 12 * gameSettings.getTileSize()));
        playing.staticEntities[7] = new Trap(new Position(15 * gameSettings.getTileSize(), 16 * gameSettings.getTileSize()));
        playing.staticEntities[8] = new Reward(new Position(5 * gameSettings.getTileSize(), 21 * gameSettings.getTileSize()));
        playing.staticEntities[9] = new Reward(new Position(20 * gameSettings.getTileSize(), 11 * gameSettings.getTileSize()));
        playing.staticEntities[10] = new Reward(new Position(21 * gameSettings.getTileSize(), 4 * gameSettings.getTileSize()));
    }

    /**
     * Updates the game world.
     */
    public void update() {
        // Check if it's time to spawn a new reward
        if (rand.nextInt(2000) < 1) { // 0.05% chance of spawning a reward
            // Find an empty slot in the staticEntities array to place the new reward
            for (int i = 0; i < playing.staticEntities.length; i++) {
                if (playing.staticEntities[i] == null) {
                    Position randomPosition = generateRandomPosition();
                    Reward newReward = new Reward(randomPosition, 4000); // 4000 is roughly 20 seconds
                    playing.staticEntities[i] = newReward;
                    break;
                }
            }
        }
    }

    /**
     * Generates a random position within the Game World
     * Checks if the position is valid (no walls)
     * 
     * @return the random position
     */
    private Position generateRandomPosition() {
        while (true) {
            int randomX = rand.nextInt(gameSettings.getMaxWorldCol());
            int randomY = rand.nextInt(gameSettings.getMaxWorldRow());
            Position position = new Position(randomX * gameSettings.getTileSize(), randomY * gameSettings.getTileSize());
            // Regenerates a new random position if the position is invalid
            if (isPositionValid(position)) {
                return position;
            }
        }
    }

    /**
     * Checks if a position is valid (no walls or another static entity) to place an
     * entity there
     * 
     * @param position the position to check if valid or not
     * @return true if the position is valid, false otherwise
     */
    public boolean isPositionValid(Position position) {
        // Check if the position conflicts with a map tile
        int tileNum = playing.tileManager.mapTileNum[position.getY() / gameSettings.getTileSize()][position.getX() / gameSettings.getTileSize()];
        if (playing.tileManager.tile[tileNum].collision == true) {
            return false;
        }
        // Check if the position conflicts with the player's location
        if (position.getX() == playing.getPlayer().getPosition().getX()
                && position.getY() == playing.getPlayer().getPosition().getY()) {
            return false;
        }
        // Check if the position conflicts with an existing static entity
        for (int i = 0; i < playing.staticEntities.length; i++) {
            if (playing.staticEntities[i] != null && position.getX() == playing.staticEntities[i].getPosition().getX()
                    && position.getY() == playing.staticEntities[i].getPosition().getY()) {
                return false;
            }
        }
        return true;
    }
}
