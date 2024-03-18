package Display;

import java.awt.Graphics;

import Gamestates.Gamestate;
import Gamestates.Menu;
import Gamestates.Playing;
import Gamestates.GameOver;
import Gamestates.GameWin;


/**
 * The `Game` class represents the main game loop and controls the overall game flow.
 * It implements the `Runnable` interface to allow it to be executed in a separate thread.
 * The game loop continuously updates and renders the game state until the game is exited.
 */
public class Game implements Runnable {

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

    // World size
    public final static int maxWorldCol = 25;
    public final static int maxWorldRow = 25;
    public final static int worldWidth = tileSize * maxWorldCol;
    public final static int worldHeight = tileSize * maxWorldRow;

    public static String gameTitle = "Grade Quest";

    private Playing playing;
    private Menu menu;
    private GameOver gameOver;
    private GameWin win;

    public Game() {
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
        gameOver = new GameOver(this);
        win = new GameWin(this);
    }

    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case OPTIONS:
                break;
            case QUIT:
                break;
            case GAMEOVER:
                gameOver.update();
                break;
            case WIN:
                win.update();
                break;
        }
    }

    public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            case OPTIONS:
                break;
            case QUIT:
                break;
            case GAMEOVER:
                gameOver.draw(g);
                break;
            case WIN:
                win.draw(g);
                break;
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;

        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;
        int prevFPS = 0;
        int prevUPS = 0;

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
                if (frames != prevFPS || updates != prevUPS) {
                    System.out.println("FPS: " + frames + " | UPS: " + updates);
                    prevFPS = frames;
                    prevUPS = updates;
                }
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost() {
		if (Gamestate.state == Gamestate.PLAYING) {
			playing.getPlayer().resetDirBooleans();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public GameWin getGameWin() {
        return win;
    }
}
