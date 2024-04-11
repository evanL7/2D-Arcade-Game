package Display;

public class GameSettings {

    private final int FPS_SET = 120;
    private final int UPS_SET = 200; // Updates per second to prevent the game from running too fast

    private String mapFilePath = "/maps/map1.txt";
    private String gameTitle = "Grade Quest";

    // Tile settings
    private final int originalTileSize = 16;
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale;

    // Grid size
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;

    // Scaled screen size
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    // World size
    private final int maxWorldCol = 25;
    private final int maxWorldRow = 25;
    private final int worldWidth = tileSize * maxWorldCol;
    private final int worldHeight = tileSize * maxWorldRow;

    public int getFPS_SET() {
        return FPS_SET;
    }

    public int getUPS_SET() {
        return UPS_SET;
    }

    public String getMapFilePath() {
        return mapFilePath;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }
}
