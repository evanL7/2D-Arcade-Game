package com.mycompany.app.classes.StaticEntity;

import com.mycompany.app.classes.Helpers.RewardType;
import java.awt.*;
import com.mycompany.app.classes.Helpers.Position;

public class Reward extends StaticEntity {

    private float rewardAmount;
    private int rewardsToCollect;
    public RewardType rewardType;

    // constructor for BonusRewards
    public Reward(Position position, boolean objectDespawns, int despawnTimer, Image sprite, float rewardAmount, int rewardsToCollect)
    {
        super(position, objectDespawns, despawnTimer, sprite);
        this.rewardAmount = rewardAmount;
        this.rewardsToCollect = rewardsToCollect;
        
        // since the only reward that also relies on despawnTimer to despawn are Bonus Rewards
        rewardType = RewardType.BonusReward;
    }

    // constructor for Regular Rewards
    public Reward(Position position, boolean objectDespawns, Image sprite, float rewardAmount, int rewardsToCollect)
    {
        super(position, objectDespawns, sprite);
        this.rewardAmount = rewardAmount;
        this.rewardsToCollect = rewardsToCollect;
        rewardType = RewardType.RegularReward;
    }

    public float getRewardAmount() 
    {
        return rewardAmount;
    }

    public void setRewardAmount(float rewardAmount) 
    {
        this.rewardAmount = rewardAmount;
    }

    public int getRewardsToCollect() 
    {
        return rewardsToCollect;
    }

    public void setRewardsToCollect(int rewardsToCollect) 
    {
        this.rewardsToCollect = rewardsToCollect;
    }

}
