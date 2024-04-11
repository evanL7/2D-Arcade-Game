package Gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Random;

import Animation.AnimationConstants.PlayerConstants;

import java.awt.event.KeyEvent;

import Display.Camera;
import Display.Game;
import Display.Score;
import Display.Time;
import Helpers.AssetManager;
import Helpers.CollisionChecker;
import Helpers.MusicManager;
import Helpers.Position;
import Helpers.RewardType;
import Helpers.SoundManager;
import Helpers.TileManager;
import MoveableEntity.MoveableEntity;
import MoveableEntity.Enemy;
import MoveableEntity.Player;
import PathFinding.PathFinder;
import StaticEntity.Reward;
import StaticEntity.StaticEntity;
import StaticEntity.Trap;
import StaticEntity.Door;

/**
 * The `Playing` class represents the game state when the player is actively
 * playing the game.
 * It extends the `State` class and implements the `Statemethods` interface.
 * It handles the game logic, updates the game objects, and renders them on the
 * screen.
 * 
 * The `Playing` class contains various instance variables such as `camera`,
 * `score`, `tileManager`, `collisionChecker`, etc.
 * It also has methods to initialize the game objects, update the game state,
 * draw the game objects on the screen,
 * handle key events, and retrieve player, camera, time, and score objects.
 * 
 * The `Playing` class is responsible for checking collision between the player
 * and traps, enemies, and rewards.
 * It also handles player movement based on the keys pressed by the user.
 * 
 * The `Playing` class is used by the `Game` class to represent the playing
 * state of the game.
 */
public class Playing extends State implements Statemethods {

    private Camera camera;
    private Score score; // Score object

    public TileManager tileManager;
    public CollisionChecker collisionChecker;
    private HashSet<Integer> keysPressed;
    public PathFinder pathFinder;
    public AssetManager assetManager;
    public StaticEntity staticEntities[];
    Position playerSpawnPositions[];

    private Player player;
    private Enemy enemy;
    private Time time; // Time object

    public Playing(Game game) {
        super(game);
        initClasses();

        MusicManager.playMusic("music/Optimistic-background-music.mp3", 0.5f);
    }

    private void initClasses() {
        keysPressed = new HashSet<>();
        tileManager = new TileManager(this);
        collisionChecker = new CollisionChecker(tileManager);
        pathFinder = new PathFinder(this);

        playerSpawnPositions = new Position[3]; // 3 spawn points for the player
        // Spawn point at the top left corner of the map
        playerSpawnPositions[0] = new Position(2 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize());
        // Spawn point at the bottom left corner of the map
        playerSpawnPositions[1] = new Position(2 * gameSettings.getTileSize(), 22 * gameSettings.getTileSize());
        // Spawn point at the top right corner of the map
        playerSpawnPositions[2] = new Position(22 * gameSettings.getTileSize(), 3 * gameSettings.getTileSize());

        Random rand = new Random();

        score = new Score();

        player = new Player(playerSpawnPositions[rand.nextInt(3)], collisionChecker, this);
        enemy = new Enemy(new Position(20 * gameSettings.getTileSize(), 20 * gameSettings.getTileSize()), this);

        staticEntities = new StaticEntity[25]; // Currently set to 25 static entities can be displayed, adjust as needed
        assetManager = new AssetManager(this);
        assetManager.setObjects();

        // Create the Camera object with the player
        camera = new Camera(player);

        time = new Time();
    }

    @Override
    public void update() {

        // Check collision between player and traps
        for (Trap trap : StaticEntity.getAllTraps()) {
            if (collisionChecker.checkPlayerTrapCollision(player, trap)) {
                if (score.getScore() < 0) {
                    Gamestate.state = Gamestate.GAMEOVER;
                }
                trap.remove();
                break; // Exit the loop after handling the collision with one trap
            } else {
                trap.update();
            }
        }

        for (Enemy enemy : MoveableEntity.getAllEnemies()) {
            if (collisionChecker.checkPlayerEnemyCollision(player, enemy)) {
                Gamestate.state = Gamestate.GAMEOVER;
            }
        }

        for (Reward reward : StaticEntity.getAllRewards()) {
            if (collisionChecker.checkPlayerRewardCollision(player, reward)) {
                reward.remove();
                break; // Exit the loop after handling the collision with one reward
            } else if (reward.getDespawnTimer() <= 0 && reward.rewardType == RewardType.BonusReward) {
                reward.remove(); // despawn
            } else {
                reward.update();
            }
        }

        Door door = StaticEntity.getDoor();
        if (door.getOpen() && collisionChecker.checkPlayerDoorCollision(player)) {
            String soundFilePath = "sounds/mixkit-game-bonus-reached-2065.wav";
            SoundManager.playSound(soundFilePath, 0.5f);
            Gamestate.state = Gamestate.WIN;
        }

        // Update the assetManager class only if there are 6 or less rewards on the map.
        // Initially, 3 rewards are allocated to the grad caps and 3 rewards can be the
        // bonus rewards.
        // If the player collects all the grad caps, up to 6 bonus rewards can be
        // spawned
        if (StaticEntity.getAllRewards().size() <= 6) {
            assetManager.update();
        }

        player.update();
        enemy.update(player);
    }

    @Override
    public void draw(Graphics g) {
        // Set background color
        g.setColor(new Color(0xadd8e6));
        g.fillRect(0, 0, gameSettings.getScreenWidth(), gameSettings.getScreenHeight());

        // Translate graphics object to adjust for camera position
        camera.update();
        camera.translate(g);

        // Render game objects with adjusted coordinates
        tileManager.draw(g);

        player.render(g);
        enemy.render(g);

        for (Trap trap : StaticEntity.getAllTraps()) {
            trap.render(g);
        }

        for (Reward reward : StaticEntity.getAllRewards()) {
            reward.render(g);
        }

        StaticEntity.getDoor().render(g);

        // Reset graphics translation
        camera.reset(g);

        // Render score at the top-left corner
        score.draw(g);
        time.displayElapsedTime(g); // displays time in the top right corner

        g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE:
                Gamestate.state = Gamestate.MENU;
                time.pauseTimer(); // pauses the timer
                break;
            case KeyEvent.VK_P:
                Gamestate.state = Gamestate.WIN;
                break;
            case KeyEvent.VK_Q:
                Gamestate.state = Gamestate.GAMEOVER;
                break;
            default:
                if (!keysPressed.contains(keyCode)) {
                    keysPressed.add(keyCode);
                    handleKeys();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keysPressed.remove(keyCode);
        handleKeys();
    }

    private void handleKeys() {
        // Handle keys based on the current state of keysPressed
        boolean up = keysPressed.contains(KeyEvent.VK_W);
        boolean left = keysPressed.contains(KeyEvent.VK_A);
        boolean down = keysPressed.contains(KeyEvent.VK_S);
        boolean right = keysPressed.contains(KeyEvent.VK_D);

        // Set player actions based on the keys pressed
        player.setUp(up);
        player.setLeft(left);
        player.setDown(down);
        player.setRight(right);

        // Determine the primary direction based on the last key pressed
        int action = player.getAction(); // Default action

        // Prevent bug where animations were not matching most recent key press
        switch (action) {
            case PlayerConstants.UP:
                if (right) {
                    action = PlayerConstants.RIGHT;
                } else if (left) {
                    action = PlayerConstants.LEFT;
                } else if (down) {
                    action = PlayerConstants.DOWN;
                }
                break;
            case PlayerConstants.LEFT:
                if (up) {
                    action = PlayerConstants.UP;
                } else if (down) {
                    action = PlayerConstants.DOWN;
                } else if (right) {
                    action = PlayerConstants.RIGHT;
                }
                break;
            case PlayerConstants.DOWN:
                if (up) {
                    action = PlayerConstants.UP;
                } else if (left) {
                    action = PlayerConstants.LEFT;
                } else if (right) {
                    action = PlayerConstants.RIGHT;
                }
                break;
            case PlayerConstants.RIGHT:
                if (up) {
                    action = PlayerConstants.UP;
                } else if (down) {
                    action = PlayerConstants.DOWN;
                } else if (left) {
                    action = PlayerConstants.LEFT;
                }
                break;
        }
        player.setAction(action);
    }

    protected void restartGame() {
        StaticEntity.resetStaticEntities();
        MoveableEntity.resetMoveableEntities();
        initClasses();
        player.resetWin();
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
        keysPressed.clear();
    }

    public Player getPlayer() {
        return player;
    }

    public Camera getCamera() {
        return camera;
    }

    public Time getTime() {
        return time;
    }

    public Score getScoreObj() {
        return score;
    }
}
