package game.tennis;

import game.tennis.utils.HelperUtils;

public class TennisGame {

    public static void main(String[] args) {

        Tennis tennis = new Tennis("Player 1", "Player 2", new HelperUtils());
        tennis.play();

    }

}
