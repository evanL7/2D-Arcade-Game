package StaticEntity;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Display.Game;
import Helpers.BoardData;

/**
 * The TileManager class manages the tiles used in the game.
 * It loads tile sprites and map data, and provides methods to draw the tiles on the game panel.
 */
public class TileManager {
    
    public Tile[] tile; // Stores the tile sprites
    public int mapTileNum[][]; // Stores the map data that indicates which tile to use    

    /**
     * Constructs a TileManager object.
     */
    public TileManager() {
        tile = new Tile[48]; // Assuming 48 tiles are used, adjust as needed
        mapTileNum = new int[Game.maxScreenRow][Game.maxScreenCol];

        getTileImage();
        loadMap("/maps/mapTest.txt");
    }

    /**
     * Loads the tile sprites from the resource files.
     * The tile sprites are sourced from https://limezu.itch.io/moderninteriors
     */
    private void getTileImage() {
        try {
            // Load the tile sprites
            loadTile("/assets/tileFloor.png", 0, false);
            loadTile("/assets/tileWallN.png", 1, true);
            loadTile("/assets/tileWallS.png", 2, true);
            loadTile("/assets/tileWallE.png", 3, true);
            loadTile("/assets/tileWallW.png", 4, true);
            loadTile("/assets/tileCornerNW.png", 5, true);
            loadTile("/assets/tileCornerNE.png", 6, true);
            loadTile("/assets/tileCornerSW.png", 7, true);
            loadTile("/assets/tileCornerSE.png", 8, true);
            loadTile("/assets/tileRoomWall.png", 9, true);
            loadTile("/assets/tileRoomWallBotMid.png", 10, true);
            loadTile("/assets/tileRoomWallBotRightCorner.png", 11, true);
            loadTile("/assets/tileRoomWallTopMid.png", 12, true);
            loadTile("/assets/tileRoomWallTopRightCorner.png", 13, true);
            loadTile("/assets/tileWallVert.png", 14, true);
            loadTile("/assets/tileWallVertFrontTop.png", 15, true);
            loadTile("/assets/tileWallVertFrontBot.png", 16, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTile(String filePath, int index, boolean collision) {
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(filePath));
            tile[index].collision = collision;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the tiles to the game screen.
     * 
     * @param g the Graphics object to draw the tiles on
     */
    public void draw(Graphics g) {
        
        int col = 0, row = 0, x = 0, y = 0;
        
        while (col < Game.maxScreenCol && row < Game.maxScreenRow) {
            int tileNum = mapTileNum[row][col];
            g.drawImage(tile[tileNum].image, x, y, Game.tileSize, Game.tileSize, null);
            
            col++;
            x += Game.tileSize;

            if (col == Game.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += Game.tileSize;
            }
        }
    }

    /**
     * Loads the map data from the specified file.
     * 
     * @param filePath the path of the file containing the map data
     */
    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (col < Game.maxScreenCol && row < Game.maxScreenRow) {
                String line = br.readLine();
                String numbers[] = line.split(" ");
                while (col < Game.maxScreenCol) {
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[row][col] = num;
                    col++;
                }
                if (col == Game.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
