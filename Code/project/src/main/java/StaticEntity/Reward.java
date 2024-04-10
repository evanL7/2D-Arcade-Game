package StaticEntity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import Display.Game;
import Helpers.Position;
import Helpers.RewardType;

// Regular Reward sprite is 16x16
// I couldn't find any free sprites so I recreated 1

// Both bonus reward sprites have dimensions 16x16
// Extra sprite just in case the first bonus reward sprite doesn't fit the game's art style
// sprite taken from https://elthen.itch.io/2d-pixel-art-dungeon-collectables

/**
 * Represents a reward entity in the game.
 * 
 * <p>
 * Rewards are static entities that players can collect in the game. There are
 * two types of rewards:
 * Bonus Rewards, which despawn after a certain time, and Regular Rewards, which
 * do not have a timer-based despawn mechanism.
 * They will both despawn after a player collects them.
 */
public class Reward extends StaticEntity {

    /** The amount of the reward that a player receives. */
    private double rewardAmount;

    /** The type of reward, either Bonus Reward or Regular Reward. */
    public RewardType rewardType;

    private BufferedImage rewardImage;

    /**
     * Constructs a new Bonus Reward.
     * 
     * @param position         The position of the reward.
     * @param despawnTimer     The time, in milliseconds, after which the Bonus
     *                         Reward will despawn.
     */
    public Reward(Position position, int despawnTimer) {
        super(position, despawnTimer);
        this.rewardAmount = 1;

        // since the only reward that also relies on despawnTimer to despawn are Bonus
        // Rewards
        rewardType = RewardType.BonusReward;
        loadRewardImage();
    }

    /**
     * Constructs a new Regular Reward.
     * 
     * @param position         The position of the reward.
     */
    public Reward(Position position) {
        super(position);
        this.rewardAmount = 0.5;

        // since regular rewards don't rely on despawnTimer
        rewardType = RewardType.RegularReward;
        loadRewardImage();
    }

    @Override
    public Image getSprite() {
        // Return the sprite image associated with the reward entity
        return rewardImage; // Assuming you have a field named rewardImage that holds the sprite image
    }
    
    /**
     * Gets the amount of the reward.
     * 
     * @return The amount of the reward.
     */
    public double getRewardAmount() {
        return rewardAmount;
    }

    public void render(Graphics g) {
        g.drawImage(rewardImage, position.getX(), position.getY(), Game.tileSize + 2, 30, null);
    }

    private void loadRewardImage() {
        if (rewardType == RewardType.RegularReward) {
            try {
                InputStream is = getClass().getResourceAsStream("/assets/Grad_Cap.png");
                rewardImage = ImageIO.read(is);
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // For Bonus Rewards
        else {
            try {
                InputStream is = getClass().getResourceAsStream("/assets/BonusA.png");
                rewardImage = ImageIO.read(is);
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getDespawnTimer() {
        return despawnTimer;
    }

    public void update() {
        if (rewardType == RewardType.BonusReward && despawnTimer > 0) {
            despawnTimer--;
        }
    }
}
