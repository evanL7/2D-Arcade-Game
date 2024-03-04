package com.mycompany.app.classes.Helpers;

// AnimationConstants keeps all sprite image array constants together
public class AnimationConstants {
    public static class PlayerConstants {
        public static final int UP = 0;
        public static final int RIGHT = 1;
        public static final int DOWN = 2;
        public static final int LEFT = 3;
    }

    // returns the amount of animation sprites in an array
    // TO DO: add switch cases for enemys, static entities, etc.
    public static int SpriteAmount(int playerAction) {
        switch (playerAction) {
            case PlayerConstants.UP:
            case PlayerConstants.DOWN:
            case PlayerConstants.LEFT:
            case PlayerConstants.RIGHT:
                return 3;
            default:
                return 1;
        }
    }

    public static class EnemyConstants {

    }
}