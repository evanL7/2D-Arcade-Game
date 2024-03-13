package Helpers;

import MoveableEntity.Enemy;

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
        public static final int DOWN = 0;
        public static final int LEFT = 1;
        public static final int RIGHT = 2;
        public static final int UP = 3;

    }

    public static int SpriteEnemyAmount(int playerAction) {
        switch (playerAction) {
            case EnemyConstants.UP:
            case EnemyConstants.DOWN:
            case EnemyConstants.LEFT:
            case EnemyConstants.RIGHT:
                return 3;
            default:
                return 1;
        }
    }
}