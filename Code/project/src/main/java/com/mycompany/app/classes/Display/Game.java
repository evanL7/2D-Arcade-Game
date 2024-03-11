package com.mycompany.app.classes.Display;

import java.awt.Graphics;
import java.awt.font.GraphicAttribute;

import com.mycompany.app.classes.Helpers.CollisionChecker;
import com.mycompany.app.classes.Helpers.Position;
import com.mycompany.app.classes.Helpers.AnimationConstants.PlayerConstants;
import com.mycompany.app.classes.MoveableEntity.Enemy;
import com.mycompany.app.classes.MoveableEntity.Player;
import com.mycompany.app.classes.StaticEntity.TileManager;

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

    int playerX = Game.screenWidth / 2 - Game.tileSize / 2;
    int playerY = Game.screenHeight / 2 - Game.tileSize / 2;

    int enemyX = Game.screenWidth / 3 - Game.tileSize / 2;
    int enemyY = Game.screenHeight / 3 - Game.tileSize / 2;

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
    }

    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        player.update();
        enemy.update();
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

        // Reset graphics translation
        camera.reset(g);
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
