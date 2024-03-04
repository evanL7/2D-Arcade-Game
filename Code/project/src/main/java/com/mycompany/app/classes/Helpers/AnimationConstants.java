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
    public static int PlayerSpriteAmount(int playerAction) {
        return 3;
    }

    public static class EnemyConstants {

    }
}