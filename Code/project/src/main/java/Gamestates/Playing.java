package Gamestates;

import java.awt.Graphics;
import java.util.HashSet;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Display.Camera;
import Display.Game;
import Display.Score;
import Helpers.AnimationConstants.PlayerConstants;
import Helpers.CollisionChecker;
import Helpers.Position;
import MoveableEntity.Enemy;
import MoveableEntity.Player;
import StaticEntity.Reward;
import StaticEntity.TileManager;
import StaticEntity.Trap;

public class Playing extends State implements Statemethods {

    private Camera camera; // add
    private Score score; // Score object

    private TileManager tileManager;
    public CollisionChecker collisionChecker;
    private HashSet<Integer> keysPressed;

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

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        keysPressed = new HashSet<>();
        tileManager = new TileManager();
        score = new Score();
        collisionChecker = new CollisionChecker(tileManager);

        player = new Player(new Position(playerX, playerY), collisionChecker, score, tileManager);

        enemy = new Enemy(new Position(enemyX, enemyY));
        trap = new Trap(new Position(trapX, trapY), 1);
        rewardReg = new Reward(new Position(regRewardX, regRewardY), 10, 1); // add

        // this takes approx 25 seconds to despawn from the screen
        rewardBonus = new Reward(new Position(bonusRX, bonusRY), 10000, 10, 1); // add
        
        // Create the Camera object with the player
        camera = new Camera(player);

        score = new Score();
    }

    @Override
    public void update() {
        // Check collision between player and rewards
    // if (collisionChecker.checkPlayerRewardCollision(player, rewardReg)) {
    //     score.incrementScore(rewardReg.getRewardAmount());
    //    // rewardReg.reset(); // Reset the reward's position
    // }
    // if (rewardBonus != null && rewardBonus.getDespawnTimer() > 0) {
    //     if (collisionChecker.checkPlayerRewardCollision(player, rewardBonus)) {
    //         score.incrementScore(rewardBonus.getRewardAmount());
    //        // rewardBonus.reset(); // Reset the reward's position
    //     }
    // }

    // Check collision between player and traps
    if (collisionChecker.checkPlayerTrapCollision(player, trap)) {
        // Decrease score (example: by 10 for hitting a trap)
        score.incrementScore(-10); // Adjust the amount as per your game's logic
        //trap.reset(); // Reset the trap's position
    }

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

    @Override
    public void draw(Graphics g) {
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

        // Render score at the top-left corner
        score.draw(g);
        
        g.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {        
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE:
                Gamestate.state = Gamestate.MENU;
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
    }

    public Player getPlayer() {
        return player;
    }
}
