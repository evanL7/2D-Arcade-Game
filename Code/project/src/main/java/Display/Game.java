package Display;

import java.awt.Graphics;
import java.awt.font.GraphicAttribute;

import Helpers.CollisionChecker;
import Helpers.Position;
import Helpers.AnimationConstants.PlayerConstants;
import MoveableEntity.Enemy;
import MoveableEntity.Player;
import StaticEntity.TileManager;
import StaticEntity.Trap;
import StaticEntity.Reward; // add

public class Game implements Runnable {

    private Camera camera; // add

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200; // Updates per second to prevent the game from running too fast

    // Tile settings
    final static int originalTileSize = 16;
    final static int scale = 3;
    public final static int tileSize = originalTileSize * scale;

    // Grid size
    public final static int maxScreenCol = 16;
    public final static int maxScreenRow = 12;

    // Scaled screen size
    public final static int screenWidth = tileSize * maxScreenCol;
    public final static int screenHeight = tileSize * maxScreenRow;

    private TileManager tileManager;
    public CollisionChecker collisionChecker;
    private Player player;
    private Enemy enemy;
    private Trap trap;
    private Reward rewardReg; // add
    private Reward rewardBonus; // add

    int playerX = Game.screenWidth / 2 - Game.tileSize / 2;
    int playerY = Game.screenHeight / 2 - Game.tileSize / 2;

    int enemyX = Game.screenWidth / 3 - Game.tileSize / 2;
    int enemyY = Game.screenHeight / 3 - Game.tileSize / 2;

    int trapX = Game.screenWidth / 2 - Game.tileSize / 2;
    int trapY = Game.screenHeight / 3 - Game.tileSize / 2;

    int regRewardX = Game.screenWidth / 4 - Game.tileSize / 4; // add
    int regRewardY = Game.screenHeight / 4 - Game.tileSize / 4; // add

    int bonusRX = Game.screenWidth / 2 - Game.tileSize / 3; // add
    int bonusRY = Game.screenHeight / 4 - Game.tileSize / 5; // add

    public Game() {
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        // Create the Camera object with the player
        camera = new Camera(player);

        startGameLoop();
    }

    private void initClasses() {
        tileManager = new TileManager();
        collisionChecker = new CollisionChecker(tileManager);
        player = new Player(new Position(playerX, playerY), collisionChecker);
        enemy = new Enemy(new Position(enemyX, enemyY));
        trap = new Trap(new Position(trapX, trapY), 1);
        rewardReg = new Reward(new Position(regRewardX, regRewardY), 10, 1); // add

        // this takes approx 25 seconds to despawn from the screen
        rewardBonus = new Reward(new Position(bonusRX, bonusRY), 10000, 10, 1); // add
    }

    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        player.update();
        enemy.update(player.getPosition());
        trap.update();
        rewardReg.update();
        if (rewardBonus != null) {
            rewardBonus.update();
            if (rewardBonus.getDespawnTimer() <= 0) {
                rewardBonus = null;
            }
        }

    }

    public void render(Graphics g) {
        // Translate graphics object to adjust for camera position
        camera.update();
        camera.translate(g);

        // Render game objects with adjusted coordinates
        tileManager.draw(g);
        // Adjust player's position based on camera
        int playerRenderX = player.getPosition().getX() - camera.getXOffset();
        int playerRenderY = player.getPosition().getY() - camera.getYOffset();
        player.render(g);
        enemy.render(g);
        trap.render(g);
        rewardReg.render(g); // add
        if (rewardBonus != null && rewardBonus.getDespawnTimer() > 0) // add
        {
            rewardBonus.render(g); // add
        }

        // Reset graphics translation
        camera.reset(g);
        g.dispose();
    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;

        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            // Check frames of the game
            if (System.currentTimeMillis() - lastCheck >= 1_000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }
}
