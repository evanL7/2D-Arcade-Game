package StaticEntity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import Display.Game;
import Helpers.Position;
import Helpers.RewardType;
// import MoveableEntity.MoveableEntity;
// import MoveableEntity.Player;

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
    private float rewardAmount;

    /** The number of rewards that players need to collect to complete the level. */
    private int rewardsToCollect;

    /** The type of reward, either Bonus Reward or Regular Reward. */
    public RewardType rewardType;

    private BufferedImage rewardImage; // add

    /**
     * Constructs a new Bonus Reward.
     * 
     * @param position         The position of the reward.
     * @param despawnTimer     The time, in milliseconds, after which the Bonus
     *                         Reward will despawn.
     * @param rewardAmount     The amount of the reward.
     * @param rewardsToCollect The number of rewards to collect.
     */
    public Reward(Position position, int despawnTimer, float rewardAmount, int rewardsToCollect) {
        super(position, despawnTimer);
        this.rewardAmount = rewardAmount;
        this.rewardsToCollect = rewardsToCollect;

        // since the only reward that also relies on despawnTimer to despawn are Bonus
        // Rewards
        rewardType = RewardType.BonusReward;
        loadRewardImage(); // add
    }

    /**
     * Constructs a new Regular Reward.
     * 
     * @param position         The position of the reward.
     * @param rewardAmount     The amount of the reward.
     * @param rewardsToCollect The number of rewards to collect.
     */
    public Reward(Position position, float rewardAmount, int rewardsToCollect) {
        super(position);
        this.rewardAmount = rewardAmount;
        this.rewardsToCollect = rewardsToCollect;

        // since regular rewards don't rely on despawnTimer
        rewardType = RewardType.RegularReward;
        loadRewardImage(); // add
    }

    @Override
    public Position getPosition() {
        // Return the position of the reward
        return position; // Assuming position is the attribute storing the position
    }

    @Override
    public Image getSprite() {
        // Return the sprite image associated with the reward entity
        return rewardImage; // Assuming you have a field named rewardImage that holds the sprite image
    }

    @Override
    public Rectangle getBoundingBox() {
        // Return the bounding box of the reward entity
        // Implement this method based on how you define the bounding box for the reward entity
        return new Rectangle(position.getX(), position.getY(), getWidth(), getHeight());
    }
    
    /**
     * Gets the amount of the reward.
     * 
     * @return The amount of the reward.
     */
    public float getRewardAmount() {
        return rewardAmount;
    }

    /**
     * Sets the amount of the reward.
     * 
     * @param rewardAmount The new amount of the reward.
     */
    public void setRewardAmount(float rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    /**
     * Gets the number of rewards to collect to complete the level.
     * 
     * @return The number of rewards to collect.
     */
    public int getRewardsToCollect() {
        return rewardsToCollect;
    }

    /**
     * Sets the number of rewards to collect to complete the level.
     * 
     * @param rewardsToCollect The new number of rewards to collect.
     */
    public void setRewardsToCollect(int rewardsToCollect) {
        this.rewardsToCollect = rewardsToCollect;
    }

    public void render(Graphics g) // add
    {
        g.drawImage(rewardImage, position.getX(), position.getY(), Game.tileSize + 2, 30, null);
    }

    private void loadRewardImage() // add
    {
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

    public int getDespawnTimer() // add
    {
        return despawnTimer;
    }

    public void update() // add
    {
        if (rewardType == RewardType.BonusReward && despawnTimer > 0) {
            despawnTimer--;
        }
    }

    


}
