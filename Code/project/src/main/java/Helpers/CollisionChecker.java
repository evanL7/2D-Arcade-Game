package Helpers;

import Display.Game;
import Helpers.AnimationConstants.PlayerConstants;
import MoveableEntity.MoveableEntity;
import StaticEntity.TileManager;

/**
 * The CollisionChecker class is responsible for checking collisions between
 * a MoveableEntity and the game tiles.
 */
public class CollisionChecker {
    
    TileManager tileManager;

    /**
     * Constructs a CollisionChecker object with the specified TileManager.
     * 
     * @param tileManager the TileManager object used for collision checking
     */
    public CollisionChecker(TileManager tileManager) {
        this.tileManager = tileManager;
    }

    /**
     * Checks for collisions between the specified MoveableEntity and the game tiles
     * in the given direction.
     * 
     * @param entity the MoveableEntity to check for collisions
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
}