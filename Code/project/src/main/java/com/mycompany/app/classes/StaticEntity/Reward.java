package com.mycompany.app.classes.StaticEntity;

import com.mycompany.app.classes.Helpers.RewardType;
import java.awt.*;
import com.mycompany.app.classes.Helpers.Position;


// Regular Reward sprite is 16x16
// I couldn't find any free sprites so I recreated 1


// Bonus Reward sprite for the scroll/cheatsheet is 16x16
// the gold and bombs are 32x32 but we won't use them
// sprite taken from https://elthen.itch.io/2d-pixel-art-dungeon-collectables



/**
 * Represents a reward entity in the game.
 * 
 * <p>Rewards are static entities that players can collect in the game. There are two types of rewards:
 * Bonus Rewards, which despawn after a certain time, and Regular Rewards, which do not have a timer-based despawn mechanism.
 * They will both despawn after a player collects them.
 */
public class Reward extends StaticEntity {

    /** The amount of the reward that a player receives. */
    private float rewardAmount;

    /** The number of rewards that players need to collect to complete the level. */
    private int rewardsToCollect;

    /** The type of reward, either Bonus Reward or Regular Reward. */
    public RewardType rewardType;

    /**
     * Constructs a new Bonus Reward.
     * 
     * @param position        The position of the reward.
     * @param objectDespawns  Whether the reward despawns upon collision.
     * @param despawnTimer    The time, in milliseconds, after which the Bonus Reward will despawn.
     * @param sprite          The sprite representing the reward.
     * @param rewardAmount    The amount of the reward.
     * @param rewardsToCollect The number of rewards to collect.
     */
    public Reward(Position position, boolean objectDespawns, int despawnTimer, Image sprite, float rewardAmount, int rewardsToCollect)
    {
        super(position, objectDespawns, despawnTimer, sprite);
        this.rewardAmount = rewardAmount;
        this.rewardsToCollect = rewardsToCollect;
        
        // since the only reward that also relies on despawnTimer to despawn are Bonus Rewards
        rewardType = RewardType.BonusReward;
    }

    /**
     * Constructs a new Regular Reward.
     * 
     * @param position        The position of the reward.
     * @param objectDespawns  Whether the reward despawns upon collision.
     * @param sprite          The sprite representing the reward.
     * @param rewardAmount    The amount of the reward.
     * @param rewardsToCollect The number of rewards to collect.
     */
    public Reward(Position position, boolean objectDespawns, Image sprite, float rewardAmount, int rewardsToCollect)
    {
        super(position, objectDespawns, sprite);
        this.rewardAmount = rewardAmount;
        this.rewardsToCollect = rewardsToCollect;
        
        // since regular rewards don't rely on despawnTimer
        rewardType = RewardType.RegularReward;
    }

    /**
     * Gets the amount of the reward.
     * 
     * @return The amount of the reward.
     */
    public float getRewardAmount() 
    {
        return rewardAmount;
    }

    /**
     * Sets the amount of the reward.
     * 
     * @param rewardAmount The new amount of the reward.
     */
    public void setRewardAmount(float rewardAmount) 
    {
        this.rewardAmount = rewardAmount;
    }

    /**
     * Gets the number of rewards to collect to complete the level.
     * 
     * @return The number of rewards to collect.
     */
    public int getRewardsToCollect() 
    {
        return rewardsToCollect;
    }

    /**
     * Sets the number of rewards to collect to complete the level.
     * 
     * @param rewardsToCollect The new number of rewards to collect.
     */
    public void setRewardsToCollect(int rewardsToCollect) 
    {
        this.rewardsToCollect = rewardsToCollect;
    }

}
