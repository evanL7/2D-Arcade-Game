package com.mycompany.app.classes.StaticEntity;

import java.awt.Graphics;

import javax.imageio.ImageIO;

import com.mycompany.app.classes.BoardData;
import com.mycompany.app.classes.Display.Game;
import com.mycompany.app.classes.Display.GamePanel;

public class TileManager {
    
    GamePanel gamePanel;
    Tile[] tile; // Stores the tile sprites

    BoardData boardData;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[48]; // Assuming 48 tiles are used, adjust as needed
        getTileImage();
    }

    private void getTileImage() {
        try {
            // Load the tile sprites
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("tileFloor.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("tileWallN.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("tileWallS.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("tileWallE.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("tileWallW.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("tileCornerNW.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("tileCornerNE.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("tileCornerSW.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("tileCornerSE.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        
        int col = 0, row = 0, x = 0, y = 0;
        
        while (col < GamePanel.maxScreenCol && row < GamePanel.maxScreenRow) {
            g.drawImage(tile[0].image, x, y, GamePanel.tileSize, GamePanel.tileSize, null);
            
            col++;
            x += GamePanel.tileSize;

            if (col == GamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += GamePanel.tileSize;
            }
        }
    }
}

