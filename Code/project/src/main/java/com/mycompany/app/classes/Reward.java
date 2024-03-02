package com.mycompany.app.classes;

public class Reward extends StaticEntity 
{

    //I changed the type for rewardAmount from float to double
    private double rewardAmount;
    private int rewardsToCollect;
    public RewardType rewardType;


    public double getRewardAmount()
    {
        return rewardAmount;
    }

    public void setRewardAmount()
    {
        this.rewardAmount = 0.5;
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
