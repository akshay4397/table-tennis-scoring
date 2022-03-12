package game.tennis;

import static game.tennis.constant.GameConstants.*;
import game.tennis.model.Player;
import game.tennis.model.ScoreBoard;
import game.tennis.utils.HelperUtils;

import java.util.Objects;

public class Tennis {

    private ScoreBoard scoreBoard;
    private final HelperUtils helperUtils = new HelperUtils();
    private boolean isTie;
    private int tieBreakerPoints;

    public Tennis() {
        initializeScoreBoard();
    }

    public void play() {

        while (Objects.isNull(this.scoreBoard.getWinner())) {

            int points = helperUtils.generateRandomPoint();
            settleResult(points);
            updateCurrentPlayer();

        }

        System.out.println("winner " + this.scoreBoard);


    }

    private void settleResult(int points) {

        Player winner = points % 2 == 0
                ? this.scoreBoard.getPlayer1()
                : this.scoreBoard.getPlayer2();

        int oldScore = winner.getScore();
        winner.setScore(oldScore + points);

        if (this.isTie && points >= this.tieBreakerPoints) {
            this.scoreBoard.setWinner(winner);
        }

        updateTieStatus();

        if (!this.isTie && this.scoreBoard.getPlayer1().getScore() >= WINNING_POINTS_1) this.scoreBoard.setWinner(this.scoreBoard.getPlayer1());
        if (!this.isTie && this.scoreBoard.getPlayer2().getScore() >= WINNING_POINTS_1) this.scoreBoard.setWinner(this.scoreBoard.getPlayer2());

    }

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

    private void updateCurrentPlayer() {
        if (this.scoreBoard.getCurrentPlayer().getScore() == MAX_SERVES_IN_A_ROW) {
            Player newCurrentPlayer = this.scoreBoard.getCurrentPlayer() == this.scoreBoard.getPlayer1()
                    ? this.scoreBoard.getPlayer2()
                    : this.scoreBoard.getPlayer1();

            this.scoreBoard.setCurrentPlayer(newCurrentPlayer);
            return;
        }

        int currentServeCount = this.scoreBoard.getCurrentPlayer().getScore();
        this.scoreBoard.getCurrentPlayer().setScore(++currentServeCount);
    }

    private void initializeScoreBoard() {

        Player player1 = Player.builder()
                .name("Player 1")
                .build();

        Player player2 = Player.builder()
                .name("Player 2")
                .build();

        this.scoreBoard = ScoreBoard.builder()
                .player1(player1)
                .player2(player2)
                .currentPlayer(player1)
                .build();

    }

}
