package com.mycompany.app.classes;

public class Reward extends StaticEntity 
{

    private float rewardAmount;
    private int rewardsToCollect;
    public RewardType rewardType;

    public float getRewardAmount()
    {
        return rewardAmount;
    }

    public void setRewardAmount()
    {
        this.rewardAmount = 0.5f;
    }

    public int getRewardsToCollect()
    {
        return rewardsToCollect;
    }

    //I changed the return type for this method to void since I believe it shouldn't return anything
    public void setRewardsToCollect()
    {

    }

}
