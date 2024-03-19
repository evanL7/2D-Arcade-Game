package StaticEntity;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Display.Game;
import Gamestates.Playing;

/**
 * The TileManager class manages the tiles used in the game.
 * It loads tile sprites and map data, and provides methods to draw the tiles on the game panel.
 */
public class TileManager {
    
    private List<StaticEntity> staticEntities;
    public Tile[] tile; // Stores the tile sprites
    public int mapTileNum[][]; // Stores the map data that indicates which tile to use    

    Playing playing;

    /**
     * Constructs a TileManager object.
     */
    public TileManager(Playing playing) {
        this.staticEntities = new ArrayList<>();
    
        tile = new Tile[48]; // Assuming 48 tiles are used, adjust as needed
        mapTileNum = new int[Game.maxWorldRow][Game.maxWorldCol];

        this.playing = playing;

        getTileImage();
        loadMap("/maps/map1.txt");
    }

    /**
     * Gets the list of static entities.
     * 
     * @return The list of static entities.
     */
    public List<StaticEntity> getStaticEntities() {
        return this.staticEntities;
    }
    
    /**
     * Loads the tile sprites from the resource files.
     * The tile sprites are sourced from https://limezu.itch.io/moderninteriors
     */
    private void getTileImage() {
        try {
            // Load the tile sprites
            loadTile("tileFloor", 0, false);
            loadTile("tileWallN", 1, true);
            loadTile("tileWallS", 2, true);
            loadTile("tileWallE", 3, true);
            loadTile("tileWallW", 4, true);
            loadTile("tileCornerNW", 5, true);
            loadTile("tileCornerNE", 6, true);
            loadTile("tileCornerSW", 7, true);
            loadTile("tileCornerSE", 8, true);
            loadTile("tileRoomWall", 9, true);
            loadTile("tileRoomWallBotMid", 10, true);
            loadTile("tileRoomWallBotRightCorner", 11, true);
            loadTile("tileRoomWallTopMid", 12, true);
            loadTile("tileRoomWallTopRightCorner", 13, true);
            loadTile("tileWallVert", 14, true);
            loadTile("tileWallVertFrontTop", 15, true);
            loadTile("tileWallVertFrontBot", 16, true);
            loadTile("tileWallVertSouthEnd", 17, true);
            loadTile("tileRoomWallTopLeftCorner", 18, true);
            loadTile("tileRoomWallBotLeftCorner", 19, true);
            loadTile("tileWallDefinition", 20, false);
            loadTile("tileWallVertUpsideDown", 21, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTile(String filePath, int index, boolean collision) {
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/" + filePath + ".png"));
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
        int startCol = Math.max(0, playing.getCamera().getXOffset() / Game.tileSize);
        int startRow = Math.max(0, playing.getCamera().getYOffset() / Game.tileSize);
        int endCol = Math.min(Game.maxWorldCol, (playing.getCamera().getXOffset() + Game.screenWidth) / Game.tileSize + 1);
        int endRow = Math.min(Game.maxWorldRow, (playing.getCamera().getYOffset() + Game.screenHeight) / Game.tileSize + 1);
    
        for (int worldRow = startRow; worldRow < endRow; worldRow++) {
            for (int worldCol = startCol; worldCol < endCol; worldCol++) {
                int tileNum = mapTileNum[worldRow][worldCol];
    
                int worldX = worldCol * Game.tileSize;
                int worldY = worldRow * Game.tileSize;

                // Check that only the visible tiles are drawn
                // System.out.println("startCol: " + startCol + " startRow: " + startRow + " endCol: " + endCol + " endRow: " + endRow);
                
                g.drawImage(tile[tileNum].image, worldX, worldY, Game.tileSize, Game.tileSize, null);
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
            while (col < Game.maxWorldCol && row < Game.maxWorldRow) {
                String line = br.readLine();
                String numbers[] = line.split(" ");
                while (col < Game.maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[row][col] = num;
                    col++;
                }
                if (col == Game.maxWorldCol) {
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
