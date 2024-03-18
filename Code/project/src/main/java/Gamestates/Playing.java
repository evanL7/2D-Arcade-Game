package Gamestates;

import java.awt.Graphics;
import java.util.HashSet;
import java.awt.event.KeyEvent;
//import java.awt.event.MouseEvent;

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
import MoveableEntity.Enemy;
import MoveableEntity.Player;
import StaticEntity.Reward;
import StaticEntity.StaticEntity;
import StaticEntity.TileManager;
import StaticEntity.Trap;

public class Playing extends State implements Statemethods {

    private Camera camera; // add
    private Score score; // Score object

    public TileManager tileManager;
    public CollisionChecker collisionChecker;
    private HashSet<Integer> keysPressed;
    public PathFinder pathFinder;
    public AssetManager assetManager;
    public StaticEntity staticEntities[];

    private Player player;
    private Enemy enemy;
    // private Trap trap;
    // private Reward rewardReg;
    // private Reward rewardBonus;
    private Time time; // Time object

    public int worldX = Game.tileSize * 23;
    public int worldY = Game.tileSize * 21;

    public int tempPlayerX = 100;
    public int tempplayerY = 200;

    // Calculate player initial position to place it in the middle of the screen
    int playerX = (Game.screenWidth - Game.tileSize) / 2;
    int playerY = (Game.screenHeight - Game.tileSize) / 2;

    int enemyX = 20 * Game.tileSize;
    int enemyY = 20 * Game.tileSize;

    int trapX = Game.screenWidth / 2 - Game.tileSize / 2;
    int trapY = Game.screenHeight / 3 - Game.tileSize / 2;

    int regRewardX = Game.screenWidth / 4 - Game.tileSize / 4;
    int regRewardY = Game.screenHeight / 4 - Game.tileSize / 4;

    int bonusRX = Game.screenWidth / 2 - Game.tileSize / 3;
    int bonusRY = Game.screenHeight / 4 - Game.tileSize / 5;

    public Playing(Game game) {
        super(game);
        initClasses();

        // To play background music
        //MusicManager.playMusic("/resources/assets/music/Optimistic-background-music.mp3");
        //MusicManager.playMusic("assests/music/Optimistic-background-music.mp3");

    }

    private void initClasses() {
        keysPressed = new HashSet<>();
        // tileManager = new TileManager();
        score = new Score();
        tileManager = new TileManager(this);
        collisionChecker = new CollisionChecker(tileManager);
        pathFinder = new PathFinder(this);

        player = new Player(new Position(playerX, playerY), collisionChecker, this, score);

        enemy = new Enemy(new Position(enemyX, enemyY), this);

        assetManager = new AssetManager(this);
        // Currently set to 25 static entities can be displayed, adjust as needed
        staticEntities = new StaticEntity[25];

        assetManager.setObjects();

        //rewardReg = new Reward(new Position(regRewardX, regRewardY), 1, 1);

        // this takes approx 12 seconds to despawn from the screen
        //rewardBonus = new Reward(new Position(bonusRX, bonusRY), 2500, 1, 1);

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
                score.incrementScore(trap.getDamage()); 
                if (score.getScore() <= 0) {
                    Gamestate.state = Gamestate.GAMEOVER;
                }
                trap.remove();
                break; // Exit the loop after handling the collision with one trap
            }

            else if(trap != null){
                trap.update();
            }
        }

        // Check collision between player and rewards
        for (Reward reward : StaticEntity.getAllRewards()) {
            if (reward != null && collisionChecker.checkPlayerRewardCollision(player, reward)) {
                player.increaseWin();
                
                if (player.getWin() == 3) {
                    // door opens??
                }   
                reward.remove();        
                break; // Exit the loop after handling the collision with one reward
            }

            // despwan
            else if (reward.getDespawnTimer() <= 0 && reward.rewardType == RewardType.BonusReward) {
                reward.remove();
            }

            else if (reward != null &&  collisionChecker.checkPlayerRewardCollision(player, reward) && reward.rewardType == RewardType.BonusReward) {
                score.incrementScore(reward.getRewardAmount());
                reward.remove(); 
            }

            else if(reward != null){
                reward.update();
            }

        }


        player.update();
        enemy.update(player);

        // if (trap != null) {
        //     trap.update();
        // }

        // for (int i = 0; i < staticEntities.length; i++) {
        //     if (staticEntities[i] != null) {
        //         // if (collisionChecker.checkPlayerTrapCollision(player, staticEntities[i])) {

        //         // }
        //         staticEntities[i].update();
        //     }
        // }

        // rewardReg.update();

        // responsible for despawning bonus rewards
        // not sure where this would go or how to implement this when there are multiple
        // bonus rewards
        // if (rewardBonus != null && rewardBonus.getDespawnTimer() > 0) {
        //     rewardBonus.update();
        //     if (collisionChecker.checkPlayerRewardCollision(player, rewardBonus)) {
        //         score.incrementScore(rewardBonus.getRewardAmount());
        //         rewardBonus = null;
        //     }
        //     else if (rewardBonus.getDespawnTimer() <= 0) {
        //         rewardBonus = null;
        //     }
        // }
    }



    @Override
    public void draw(Graphics g) {
        // Translate graphics object to adjust for camera position
        camera.update();
        camera.translate(g);

        // Render game objects with adjusted coordinates
        tileManager.draw(g);
        // Adjust player's position based on camera
        // int playerRenderX = player.getPosition().getX() - camera.getXOffset();
        // int playerRenderY = player.getPosition().getY() - camera.getYOffset();
        player.render(g);
        enemy.render(g);

        for (int i = 0; i < staticEntities.length; i++) {
            if (staticEntities[i] != null) {
                staticEntities[i].render(g);
            }
        }

        // if (trap != null) {
        //     trap.render(g);
        // }
        // if (rewardReg != null) {
        //     rewardReg.render(g);
        // }
        
        // if (rewardBonus != null && rewardBonus.getDespawnTimer() > 0) {
        //     rewardBonus.render(g);
        // }

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

            // test remove later!!!
            case KeyEvent.VK_P:
                Gamestate.state = Gamestate.GAMEOVER;
                break;

            // test remove later!!!
            case KeyEvent.VK_B:
                Gamestate.state = Gamestate.WIN;
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
        if (up) {
            action = PlayerConstants.UP;
        } else if (left) {
            action = PlayerConstants.LEFT;
        } else if (down) {
            action = PlayerConstants.DOWN;
        } else if (right) {
            action = PlayerConstants.RIGHT;
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
        initClasses();
    }
}
