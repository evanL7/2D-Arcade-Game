package Display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Helpers.SoundManager;

public class Score {
    private double score;

    public Score() {
        this.score = 2.00;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void incrementScore(double incrementAmount) {

        //String soundFilePath = "C:\\Users\\mouss\\CMPT276S24_group3\\Code\\project\\src\\main\\resources\\sounds\\bloop_x.wav";
        String soundFilePath = "/sounds/bloop_x.wav";
        SoundManager.playSound(soundFilePath);

        this.score += incrementAmount;
    }

    // Add method to decrease score
    public void decreaseScore(double decrementAmount) {
        this.score -= decrementAmount;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(String.format("Score: %.2f", score), 10, 30);
    }
}
