package Display;

public class GameSettings {

    private final int FPS_SET = 120;
    private final int UPS_SET = 200; // Updates per second to prevent the game from running too fast

    public String mapFilePath = "/maps/map1.txt";
    public String gameTitle = "Grade Quest";

    // Tile settings
    private final int originalTileSize = 16;
    private final int scale = 3;
    public final int tileSize = originalTileSize * scale;

    // Grid size
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;

    // Scaled screen size
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // World size
    public final int maxWorldCol = 25;
    public final int maxWorldRow = 25;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
}
