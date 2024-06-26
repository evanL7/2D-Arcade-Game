package Helpers;

import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.List;

import Animation.AnimationConstants.EnemyConstants;
import Animation.AnimationConstants.PlayerConstants;
import Display.GameSettings;
import MoveableEntity.Enemy;
import MoveableEntity.MoveableEntity;
import MoveableEntity.Player;
import StaticEntity.Door;
import StaticEntity.Reward;
import StaticEntity.StaticEntity;
import StaticEntity.Trap;

/**
 * The CollisionChecker class is responsible for checking collisions between
 * a MoveableEntity and the game tiles.
 */
public class CollisionChecker {

    private TileManager tileManager;
    private List<StaticEntity> staticEntities;
    private GameSettings gameSettings;

    /**
     * Constructs a CollisionChecker object with the specified TileManager.
     * 
     * @param tileManager the TileManager object used for collision checking
     */
    public CollisionChecker(TileManager tileManager) {
        this.tileManager = tileManager;
        this.staticEntities = new ArrayList<>();
        // Populate the list of static entities
        this.staticEntities.addAll(tileManager.getStaticEntities());
        this.gameSettings = new GameSettings();
    }

    /**
     * Checks for collisions between the specified MoveableEntity and the game tiles
     * in the given direction.
     * 
     * @param entity    the MoveableEntity to check for collisions
     * @param direction the direction in which the entity is moving
     */
    public void checkTile(MoveableEntity entity, int direction) {
        int entityLeft = entity.position.getX() + entity.solidArea.x;
        int entityRight = entity.position.getX() + entity.solidArea.x + entity.solidArea.width;
        int entityTop = entity.position.getY() + entity.solidArea.y;
        int entityBottom = entity.position.getY() + entity.solidArea.y + entity.solidArea.height;

        // Pinpoints the tile the entity is attempting to move into
        int entityLeftCol = entityLeft / gameSettings.getTileSize();
        int entityRightCol = entityRight / gameSettings.getTileSize();
        int entityTopRow = entityTop / gameSettings.getTileSize();
        int entityBottomRow = entityBottom / gameSettings.getTileSize();

        int tileNum1, tileNum2;

        switch (direction) {
            case PlayerConstants.UP:
                entityTopRow = (entityTop - entity.speed) / gameSettings.getTileSize();
                tileNum1 = tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileManager.mapTileNum[entityTopRow][entityRightCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case PlayerConstants.LEFT:
                entityLeftCol = (entityLeft - entity.speed) / gameSettings.getTileSize();
                tileNum1 = tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileManager.mapTileNum[entityBottomRow][entityLeftCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case PlayerConstants.DOWN:
                entityBottomRow = (entityBottom + entity.speed) / gameSettings.getTileSize();
                tileNum1 = tileManager.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = tileManager.mapTileNum[entityBottomRow][entityRightCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case PlayerConstants.RIGHT:
                entityRightCol = (entityRight + entity.speed) / gameSettings.getTileSize();
                tileNum1 = tileManager.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = tileManager.mapTileNum[entityBottomRow][entityRightCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    /**
     * Checks for collisions between the specified player and rewards.
     * 
     * @param player The player entity.
     * @param reward The reward entity.
     * @return true if collision occurred, false otherwise.
     */
    public boolean checkPlayerRewardCollision(Player player, Reward reward) {

        Rectangle playerBounds = player.getBoundingBox();
        Rectangle rewardBounds = reward.getBoundingBox();
        
        // Check if the bounding boxes intersect
        boolean result = playerBounds.intersects(rewardBounds);
        if (result) {
            if (reward.rewardType == RewardType.RegularReward) {
                player.increaseWin();

                if (player.getWin() == 3) {
                    StaticEntity.getDoor().setOpen();
                }
            }
            player.getScoreObj().incrementScore(reward.getRewardAmount());
        }

        // Return the collision result
        return result;
    }

    /**
     * Checks for collisions between the specified player and traps.
     * 
     * @param player The player entity.
     * @param trap   The trap entity.
     * @return true if collision occurred, false otherwise.
     */
    public boolean checkPlayerTrapCollision(Player player, Trap trap) {

        Rectangle playerBounds = player.getBoundingBox();
        Rectangle trapBounds = trap.getBoundingBox();
        
        // Check if the bounding boxes intersect
        boolean result = playerBounds.intersects(trapBounds);
        if (result) {
            player.getScoreObj().decreaseScore(trap.getDamage());
        }

        // Return the collision result
        return result;
    }

    /**
     * Checks for collisions between the specified player and doors.
     * 
     * @param player The player entity.
     * @return true if collision occurred, false otherwise.
     */
    public boolean checkPlayerDoorCollision(Player player) {

        Door door = StaticEntity.getDoor();
        
        Rectangle doorBounds = door.getBoundingBox();
        Rectangle playerBounds = player.getBoundingBox();

        // Check if the bounding boxes intersect
        boolean result = playerBounds.intersects(doorBounds);

        // Return the collision result
        return result;
    }

    /**
     * Checks for collisions between the specified player and enemies.
     * 
     * @param player The player entity.
     * @param enemy  The enemy entity.
     * @return true if collision occurred, false otherwise.
     */
    public boolean checkPlayerEnemyCollision(Player player, Enemy enemy) {

        Rectangle playerBounds = player.getBoundingBox();
        Rectangle enemyBounds = enemy.getBoundingBox();

        // Check if the bounding boxes intersect
        boolean result = playerBounds.intersects(enemyBounds);

        // Return the collision result
        return result;
    }

    public void updateStaticEntities() {
        this.staticEntities.clear();
        this.staticEntities.addAll(tileManager.getStaticEntities());
    }

    public void checkTileEnemy(MoveableEntity entity, int direction) {
        int entityLeft = entity.position.getX() + entity.solidArea.x;
        int entityRight = entity.position.getX() + entity.solidArea.x + entity.solidArea.width;
        int entityTop = entity.position.getY() + entity.solidArea.y;
        int entityBottom = entity.position.getY() + entity.solidArea.y + entity.solidArea.height;

        // Pinpoints the tile the entity is attempting to move into
        int entityLeftCol = entityLeft / gameSettings.getTileSize();
        int entityRightCol = entityRight / gameSettings.getTileSize();
        int entityTopRow = entityTop / gameSettings.getTileSize();
        int entityBottomRow = entityBottom / gameSettings.getTileSize();

        int tileNum1, tileNum2;

        switch (direction) {
            case EnemyConstants.UP:
                entityTopRow = (entityTop - entity.speed) / gameSettings.getTileSize();
                tileNum1 = tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileManager.mapTileNum[entityTopRow][entityRightCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case EnemyConstants.LEFT:
                entityLeftCol = (entityLeft - entity.speed) / gameSettings.getTileSize();
                tileNum1 = tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileManager.mapTileNum[entityBottomRow][entityLeftCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case EnemyConstants.DOWN:
                entityBottomRow = (entityBottom + entity.speed) / gameSettings.getTileSize();
                tileNum1 = tileManager.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = tileManager.mapTileNum[entityBottomRow][entityRightCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case EnemyConstants.RIGHT:
                entityRightCol = (entityRight + entity.speed) / gameSettings.getTileSize();
                tileNum1 = tileManager.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = tileManager.mapTileNum[entityBottomRow][entityRightCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}