package game.tennis.utils;

import game.tennis.constant.GameConstants;

import java.util.Random;

public class HelperUtils {

    private final Random random = new Random();

    public int generateRandomPoint() {
        return random.ints(1, GameConstants.MAX_POINTS_IN_A_SERVE)
                .findFirst()
                .getAsInt();
    }

}
