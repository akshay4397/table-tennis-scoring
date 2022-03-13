package game.tennis;

import static game.tennis.constant.GameConstants.*;
import game.tennis.model.Player;
import game.tennis.model.ScoreBoard;
import game.tennis.utils.HelperUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * This class represents the tennis scoring game
 */
@Getter
@Setter
public class Tennis {

    private ScoreBoard scoreBoard;
    private boolean isTie;
    private int tieBreakerPoints;
    private final HelperUtils helperUtils;

    public Tennis(String player1Name, String player2Name, HelperUtils helperUtils) {
        initializeScoreBoard(player1Name, player2Name);
        this.helperUtils = helperUtils;
    }

    /**
     * This method is for starting the game
     */
    public void play() {

        while (Objects.isNull(this.scoreBoard.getWinner())) {

            updateCurrentPlayer();
            int points = helperUtils.generateRandomPoint();
            settleResult(points);

        }

        System.out.print("Winner : " + this.scoreBoard.getWinner().getName());
        System.out.println("\tScore : " + this.scoreBoard.getWinner().getScore());

    }

    /**
     * This method is for updating scores after each serves
     *
     * @param points points scored in a serves
     */
    private void settleResult(int points) {

        Player winner = points % 2 == 0
                ? this.scoreBoard.getPlayer1()
                : this.scoreBoard.getPlayer2();

        int oldScore = winner.getScore();
        winner.setScore(oldScore + points);

        if (this.isTie && points >= this.tieBreakerPoints) {
            this.scoreBoard.setWinner(winner);
            return;
        }

        updateTieStatus();

        if (!this.isTie && this.scoreBoard.getPlayer1().getScore() >= WINNING_POINTS_1) this.scoreBoard.setWinner(this.scoreBoard.getPlayer1());
        if (!this.isTie && this.scoreBoard.getPlayer2().getScore() >= WINNING_POINTS_1) this.scoreBoard.setWinner(this.scoreBoard.getPlayer2());

    }

    /**
     * This method checks if the current scoreboard causes tie
     */
    private void updateTieStatus() {
        if (this.scoreBoard.getPlayer1().getScore() == this.scoreBoard.getPlayer2().getScore()) {
            this.isTie = Boolean.TRUE;
            if (this.scoreBoard.getPlayer1().getScore() == TIE_POINTS_1) {
                this.tieBreakerPoints = WINNING_POINTS_1 - TIE_POINTS_1;
                return;
            }

            if (this.scoreBoard.getPlayer1().getScore() == TIE_POINTS_2) {
                this.tieBreakerPoints = WINNING_POINTS_2 - TIE_POINTS_2;
                return;
            }
        }

        this.isTie = Boolean.FALSE;
        this.tieBreakerPoints = 0;
    }

    /**
     * This method updates current player of the game. As only 2 continuous serves are allowed for a player
     */
    private void updateCurrentPlayer() {
        if (this.scoreBoard.getCurrentPlayer().getServes() == MAX_SERVES_IN_A_ROW) {
            Player newCurrentPlayer = this.scoreBoard.getCurrentPlayer() == this.scoreBoard.getPlayer1()
                    ? this.scoreBoard.getPlayer2()
                    : this.scoreBoard.getPlayer1();

            this.scoreBoard.setCurrentPlayer(newCurrentPlayer);
            return;
        }

        int currentServeCount = this.scoreBoard.getCurrentPlayer().getServes();
        this.scoreBoard.getCurrentPlayer().setServes(++currentServeCount);
    }

    /**
     * This method initializes players with player name and scoreboard with current player to player 1
     *
     * @param player1Name player 1 name
     * @param player2Name player 2 name
     */
    private void initializeScoreBoard(String player1Name, String player2Name) {

        Player player1 = Player.builder()
                .name(player1Name)
                .build();

        Player player2 = Player.builder()
                .name(player2Name)
                .build();

        this.scoreBoard = ScoreBoard.builder()
                .player1(player1)
                .player2(player2)
                .currentPlayer(player1)
                .build();

    }

}
