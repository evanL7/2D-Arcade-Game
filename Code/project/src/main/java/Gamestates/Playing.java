package Gamestates;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Random;
import java.awt.event.KeyEvent;

import Display.Camera;
import Display.Game;
import Display.Score;
import Display.Time;
import Helpers.AnimationConstants.PlayerConstants;
import Helpers.AssetManager;
import Helpers.CollisionChecker;
import Helpers.MusicManager;
import Helpers.PathFinder;
import Helpers.Position;
import Helpers.RewardType;
import Helpers.SoundManager;
import MoveableEntity.MoveableEntity;
import MoveableEntity.Enemy;
import MoveableEntity.Player;
import StaticEntity.Reward;
import StaticEntity.StaticEntity;
import StaticEntity.TileManager;
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

    private Camera camera; // add
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

    int enemyX = 20 * Game.tileSize;
    int enemyY = 20 * Game.tileSize;

    public Playing(Game game) {
        super(game);
        initClasses();

        MusicManager.playMusic("music/Optimistic-background-music.mp3", 0.5f);
    }

    private void initClasses() {
        keysPressed = new HashSet<>();
        // tileManager = new TileManager();
        score = new Score();
        tileManager = new TileManager(this);
        collisionChecker = new CollisionChecker(tileManager);
        pathFinder = new PathFinder(this);

        playerSpawnPositions = new Position[4];
        playerSpawnPositions[0] = new Position(2 * Game.tileSize, 3 * Game.tileSize); // Spawn point at the top left
                                                                                      // corner of the map
        playerSpawnPositions[1] = new Position(2 * Game.tileSize, 22 * Game.tileSize); // Spawn point at the bottom left
                                                                                       // corner of the map
        playerSpawnPositions[2] = new Position(22 * Game.tileSize, 3 * Game.tileSize); // Spawn point at the top right
                                                                                       // corner of the map
        // playerSpawnPositions[3] = new Position(22 * Game.tileSize, 22 *
        // Game.tileSize); // Spawn point at the bottom right corner of the map

        Random rand = new Random();
        player = new Player(playerSpawnPositions[rand.nextInt(3)], collisionChecker, this, score);

        enemy = new Enemy(new Position(enemyX, enemyY), this);

        assetManager = new AssetManager(this);
        // Currently set to 25 static entities can be displayed, adjust as needed
        staticEntities = new StaticEntity[25];

        assetManager.setObjects();

        // rewardReg = new Reward(new Position(regRewardX, regRewardY), 1, 1);

        // this takes approx 12 seconds to despawn from the screen
        // rewardBonus = new Reward(new Position(bonusRX, bonusRY), 2500, 1, 1);

        // Create the Camera object with the player
        camera = new Camera(player);
        score = new Score();
        time = new Time();
    }

    @Override
    public void update() {

        // Check collision between player and traps
        for (Trap trap : StaticEntity.getAllTraps()) {
            if (trap != null && collisionChecker.checkPlayerTrapCollision(player, trap)) {
                score.decreaseScore(trap.getDamage());
                if (score.getScore() < 0) {
                    player.resetWin();
                    Gamestate.state = Gamestate.GAMEOVER;
                }
                trap.remove();
                break; // Exit the loop after handling the collision with one trap
            }

            else if (trap != null) {
                trap.update();
            }
        }

        for (Enemy enemy : MoveableEntity.getAllEnemies()) {
            if (enemy != null && collisionChecker.checkPlayerEnemyCollision(player, enemy)) {
                player.resetWin();
                Gamestate.state = Gamestate.GAMEOVER;
            }
        }

        // Check collision between player and rewards
        for (Reward reward : StaticEntity.getAllRewards()) {
            if (reward != null && collisionChecker.checkPlayerRewardCollision(player, reward)
                    && reward.rewardType == RewardType.RegularReward) {
                player.increaseWin();

                if (player.getWin() == 3) {
                    // door opens??
                }
                reward.remove();
                break; // Exit the loop after handling the collision with one reward
            }

            else if (reward != null && collisionChecker.checkPlayerRewardCollision(player, reward)
                    && reward.rewardType == RewardType.BonusReward) {
                score.incrementScore(reward.getRewardAmount());
                reward.remove();
            }

            // despawn
            else if (reward.getDespawnTimer() <= 0 && reward.rewardType == RewardType.BonusReward) {
                reward.remove();
            }

            else if (reward != null) {
                reward.update();
            }
        }

        for (Door door : StaticEntity.getAllDoors()) {
            if (door != null && player.getWin() == 3) {
                // add code for showing door opening animation

                if (collisionChecker.checkPlayerDoorCollision(player, door)) {
                    player.resetWin();
                    Gamestate.state = Gamestate.WIN;
                }
            }
        }

        player.update();
        enemy.update(player);
    }

    @Override
    public void draw(Graphics g) {
        // Translate graphics object to adjust for camera position
        camera.update();
        camera.translate(g);

        // Render game objects with adjusted coordinates
        tileManager.draw(g);

        player.render(g);
        enemy.render(g);

        // for (int i = 0; i < staticEntities.length; i++) {
        // if (staticEntities[i] != null) {
        // staticEntities[i].render(g);
        // }
        // }

        for (Trap trap : StaticEntity.getAllTraps()) {
            if (trap != null) {
                trap.render(g);
            }
        }

        for (Reward reward : StaticEntity.getAllRewards()) {
            if (reward != null) {
                reward.render(g);
            }
        }

        for (Door door : StaticEntity.getAllDoors()) {
            if (door != null) {
                door.render(g);
            }
        }

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

    protected void restartGame() {
        StaticEntity.resetStaticEntities(); // Added this
        MoveableEntity.resetMoveableEntities(); // Added this
        initClasses();
    }
}
