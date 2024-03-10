package com.mycompany.app.classes.Helpers;

import com.mycompany.app.classes.Display.Game;
import com.mycompany.app.classes.Helpers.AnimationConstants.PlayerConstants;
import com.mycompany.app.classes.MoveableEntity.MoveableEntity;
import com.mycompany.app.classes.StaticEntity.TileManager;

public class CollisionChecker {
    
    TileManager tileManager;

    public CollisionChecker(TileManager tileManager) {
        this.tileManager = tileManager;
    }

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
