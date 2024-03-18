package Helpers;

import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.List;

import Display.Game;
import Helpers.AnimationConstants.EnemyConstants;
import Helpers.AnimationConstants.PlayerConstants;
import MoveableEntity.Enemy;
import MoveableEntity.MoveableEntity;
import MoveableEntity.Player;
import StaticEntity.Door;
import StaticEntity.Reward;
import StaticEntity.StaticEntity;
import StaticEntity.TileManager;
import StaticEntity.Trap;

/**
 * The CollisionChecker class is responsible for checking collisions between
 * a MoveableEntity and the game tiles.
 */
public class CollisionChecker {

    TileManager tileManager;
    private List<StaticEntity> staticEntities; //add


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
        int entityLeftCol = entityLeft / Game.tileSize;
        int entityRightCol = entityRight / Game.tileSize;
        int entityTopRow = entityTop / Game.tileSize;
        int entityBottomRow = entityBottom / Game.tileSize;

        int tileNum1, tileNum2;

        // System.out.println("Entity position: " + entity.getPosition());
        // System.out.println("Direction: " + direction);

        switch (direction) {
            case PlayerConstants.UP:
                entityTopRow = (entityTop - entity.speed) / Game.tileSize;
                tileNum1 = tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileManager.mapTileNum[entityTopRow][entityRightCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case PlayerConstants.LEFT:
                entityLeftCol = (entityLeft - entity.speed) / Game.tileSize;
                tileNum1 = tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileManager.mapTileNum[entityBottomRow][entityLeftCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case PlayerConstants.DOWN:
                entityBottomRow = (entityBottom + entity.speed) / Game.tileSize;
                tileNum1 = tileManager.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = tileManager.mapTileNum[entityBottomRow][entityRightCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case PlayerConstants.RIGHT:
                entityRightCol = (entityRight + entity.speed) / Game.tileSize;
                tileNum1 = tileManager.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = tileManager.mapTileNum[entityBottomRow][entityRightCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    /**
     * Checks collision between two entities.
     * 
     * @param entity1 the first entity
     * @param entity2 the second entity
     * @return true if entities collide, false otherwise
     */
    // private boolean checkCollision(MoveableEntity entity1, StaticEntity entity2) {
    //     // Get the bounding boxes for the entities
    //     Rectangle entity1Bounds = new Rectangle(entity1.getPosition().getX(), entity1.getPosition().getY(),
    //                                              entity1.getWidth(), entity1.getHeight());
        
    //     Rectangle entity2Bounds = new Rectangle(entity2.getPosition().getX(), entity2.getPosition().getY(),
    //                                              entity2.getWidth(), entity2.getHeight());
        
    //         // Print information about the bounding boxes
    //     System.out.println("Entity 1 Bounds: " + entity1Bounds);
    //     System.out.println("Entity 2 Bounds: " + entity2Bounds);
        
    //     // Check if the bounding boxes intersect
    //     boolean result = entity1Bounds.intersects(entity2Bounds);
        
    //     // Print the result of the collision check
    //     System.out.println("Collision Detected: " + result);
    //     // Check if the bounding boxes intersect
    //     return result;
    // }
    
    /**
     * Checks for collisions between the specified player and rewards.
     * 
     * @param player The player entity.
     * @param reward The reward entity.
     * @return true if collision occurred, false otherwise.
     */
    public boolean checkPlayerRewardCollision(Player player, Reward reward) {
        // Rectangle playerBounds = new Rectangle(player.getPosition().getX(), player.getPosition().getY(),
        //                                         player.getWidth(), player.getHeight());
        
        
        // Rectangle rewardBounds = new Rectangle(reward.getPosition().getX(), reward.getPosition().getY(),
        //                                         reward.getWidth(), reward.getHeight());
        
        // new hitboxes
        Rectangle playerBounds = new Rectangle(player.getPosition().getX(), player.getPosition().getY(), (int) (Game.tileSize * 0.75), Game.tileSize);
        Rectangle rewardBounds = new Rectangle(reward.getPosition().getX(), reward.getPosition().getY(), (int) (Game.tileSize * 0.6), 15);

        // Print information about the bounding boxes
        //System.out.println("Player Bounds: " + playerBounds);
        //System.out.println("Reward Bounds: " + rewardBounds);
        
        // Check if the bounding boxes intersect
        boolean result = playerBounds.intersects(rewardBounds);
        
        // Print the result of the collision check
        System.out.println("Collision Detected: " + result);
        
        // Return the collision result
        return result;
    }
    

    /**
     * Checks for collisions between the specified player and traps.
     * 
     * @param player The player entity.
     * @param trap The trap entity.
     * @return true if collision occurred, false otherwise.
     */
    public boolean checkPlayerTrapCollision(Player player, Trap trap) {
        
        // old hitboxes
        // Rectangle playerBounds = new Rectangle(player.getPosition().getX(), player.getPosition().getY(),
        //                                         player.getWidth(), player.getHeight());
        // Rectangle trapBounds = new Rectangle(trap.getPosition().getX(), trap.getPosition().getY(),
        // trap.getWidth(), trap.getHeight());

        // new hitboxes
        Rectangle playerBounds = new Rectangle(player.getPosition().getX(), player.getPosition().getY(), (int) (Game.tileSize * 0.75), Game.tileSize);
        Rectangle trapBounds = new Rectangle(trap.getPosition().getX(), trap.getPosition().getY(),(int) (Game.tileSize * 0.6), 15);
        
        
        // Print information about the bounding boxes
        //System.out.println("Player Bounds: " + playerBounds);
        //System.out.println("Trap Bounds: " + trapBounds);
        
        // Check if the bounding boxes intersect
        boolean result = playerBounds.intersects(trapBounds);
        
        // Print the result of the collision check
        System.out.println("Collision Detected: " + result);
        
        // Return the collision result
        return result;
    }

    /**
     * Checks for collisions between the specified player and doors.
     * 
     * @param player The player entity.
     * @param door The door entity.
     * @return true if collision occurred, false otherwise.
     */
    public boolean checkPlayerDoorCollision(Player player, Door door) {
        Rectangle playerBounds = new Rectangle(player.getPosition().getX(), player.getPosition().getY(),
                                                player.getWidth(), player.getHeight());
        
        Rectangle doorBounds = new Rectangle(door.getPosition().getX(), door.getPosition().getY(),
                                                door.getWidth(), door.getHeight());
        
        // Print information about the bounding boxes
        //System.out.println("Player Bounds: " + playerBounds);
        //System.out.println("Door Bounds: " + doorBounds);
        
        // Check if the bounding boxes intersect
        boolean result = playerBounds.intersects(doorBounds);
        
        // Print the result of the collision check
        System.out.println("Collision Detected: " + result);
        
        // Return the collision result
        return result;
    }

    // public boolean checkCollisionStatic(Player player) {
        
    // }

    /**
     * Checks for collisions between the specified player and enemies.
     * 
     * @param player The player entity.
     * @param enemy The enemy entity.
     * @return true if collision occurred, false otherwise.
     */
    public boolean checkPlayerEnemyCollision(Player player, Enemy enemy) {
        
        Rectangle playerBounds = new Rectangle(player.getPosition().getX(), player.getPosition().getY(),
                                                player.getWidth(), player.getHeight());
        
        Rectangle enemyBounds = new Rectangle(enemy.getPosition().getX(), enemy.getPosition().getY(),
                                                enemy.getWidth(), enemy.getHeight());
        
        // Print information about the bounding boxes
        //System.out.println("Player Bounds: " + playerBounds);
        //System.out.println("Enemy Bounds: " + enemyBounds);
        
        // Check if the bounding boxes intersect
        boolean result = playerBounds.intersects(enemyBounds);
        
        // Print the result of the collision check
        System.out.println("Collision Detected: " + result);
        
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
        int entityLeftCol = entityLeft / Game.tileSize;
        int entityRightCol = entityRight / Game.tileSize;
        int entityTopRow = entityTop / Game.tileSize;
        int entityBottomRow = entityBottom / Game.tileSize;

        int tileNum1, tileNum2;

        switch (direction) {
            case EnemyConstants.UP:
                entityTopRow = (entityTop - entity.speed) / Game.tileSize;
                tileNum1 = tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileManager.mapTileNum[entityTopRow][entityRightCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case EnemyConstants.LEFT:
                entityLeftCol = (entityLeft - entity.speed) / Game.tileSize;
                tileNum1 = tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileManager.mapTileNum[entityBottomRow][entityLeftCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case EnemyConstants.DOWN:
                entityBottomRow = (entityBottom + entity.speed) / Game.tileSize;
                tileNum1 = tileManager.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = tileManager.mapTileNum[entityBottomRow][entityRightCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case EnemyConstants.RIGHT:
                entityRightCol = (entityRight + entity.speed) / Game.tileSize;
                tileNum1 = tileManager.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = tileManager.mapTileNum[entityBottomRow][entityRightCol];

                if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}