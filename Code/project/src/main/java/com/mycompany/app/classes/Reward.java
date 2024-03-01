package com.mycompany.app.classes;

public class Reward extends StaticEntity 
{

    //I changed the type for rewardAmount from float to double
    private double rewardAmount;
    private int rewardsToCollect;
    public RewardType rewardType;

    //I added another attribute for difficulty as that will determine how many rewards a player should collect
    public Difficulty difficulty;

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
        // the difficulty determines the number of rewards to collect
        switch (difficulty)
        {
            case Easy:
                this.rewardsToCollect = 3;
                break;
            
            case Medium:
                this.rewardsToCollect = 5;
                break;
            
            case Hard:
                this.rewardsToCollect = 7;
                break;
        }
    }

}
