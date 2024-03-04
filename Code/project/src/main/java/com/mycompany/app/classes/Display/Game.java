package com.mycompany.app.classes.Display;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        // int frames = 0;
        // long lastCheck = System.currentTimeMillis();

        while (true) {
            now = System.nanoTime();

            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = System.nanoTime();
                // frames++;
            }

            // Check frames of the game
            // if (System.currentTimeMillis() - lastCheck >= 1_000) {
            // lastCheck = System.currentTimeMillis();
            // System.out.println("FPS: " + frames);
            // frames = 0;
            // }
        }
    }
}
