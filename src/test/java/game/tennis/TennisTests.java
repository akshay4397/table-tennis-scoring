package game.tennis;

import game.tennis.utils.HelperUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TennisTests {

    private final HelperUtils helperUtils = Mockito.mock(HelperUtils.class);

    @InjectMocks
    private Tennis tennis = new Tennis("Player 1", "Player 2", helperUtils);


    @BeforeEach
    public void clearScoreBoardState() {

        // clear scores and serves of player 1
        tennis.getScoreBoard().getPlayer1().setScore(0);
        tennis.getScoreBoard().getPlayer1().setServes(0);

        // clear scores and serves of player 2
        tennis.getScoreBoard().getPlayer2().setScore(0);
        tennis.getScoreBoard().getPlayer2().setServes(0);

        // reset current player to player 1
        tennis.getScoreBoard().setCurrentPlayer(tennis.getScoreBoard().getPlayer1());

        // clear winner player, tie status and tiebreaker points
        tennis.getScoreBoard().setWinner(null);
        tennis.setTie(Boolean.FALSE);
        tennis.setTieBreakerPoints(0);

    }

    @Test
    @DisplayName("Player 1 have winner points and winner player will be Player 1")
    public void playTest_player1HasWinningPoints_player1ShouldBeWinnerPlayer() {

        Mockito.when(helperUtils.generateRandomPoint()).thenReturn(12);
        tennis.play();

        Assertions.assertNotNull(tennis.getScoreBoard().getWinner());
        Assertions.assertEquals(tennis.getScoreBoard().getPlayer1(), tennis.getScoreBoard().getWinner());
        Assertions.assertEquals("Player 1", tennis.getScoreBoard().getWinner().getName());
        Assertions.assertEquals(12, tennis.getScoreBoard().getWinner().getScore());

    }


    @Test
    @DisplayName("Player 2 have winner points and winner player will be Player 2")
    public void playTest_player2HasWinningPoints_player2ShouldBeWinnerPlayer() {

        Mockito.when(helperUtils.generateRandomPoint()).thenReturn(11);
        tennis.play();

        Assertions.assertNotNull(tennis.getScoreBoard().getWinner());
        Assertions.assertEquals(tennis.getScoreBoard().getPlayer2(), tennis.getScoreBoard().getWinner());
        Assertions.assertEquals("Player 2", tennis.getScoreBoard().getWinner().getName());
        Assertions.assertEquals(11, tennis.getScoreBoard().getWinner().getScore());

    }



    @Test
    @DisplayName("Tie at 10 points, Player 1 should we winner as new points will be 2")
    public void playTest_tieAt10Points_player1ShouldBeWinnerPlayer() {

        this.tennis.getScoreBoard().getPlayer1().setScore(10);
        this.tennis.getScoreBoard().getPlayer2().setScore(10);
        this.tennis.setTie(Boolean.TRUE);
        this.tennis.setTieBreakerPoints(2);

        Mockito.when(helperUtils.generateRandomPoint()).thenReturn(2);
        tennis.play();

        Assertions.assertNotNull(tennis.getScoreBoard().getWinner());
        Assertions.assertEquals(tennis.getScoreBoard().getPlayer1(), tennis.getScoreBoard().getWinner());
        Assertions.assertEquals("Player 1", tennis.getScoreBoard().getWinner().getName());
        Assertions.assertEquals(12, tennis.getScoreBoard().getWinner().getScore());

    }


    @Test
    @DisplayName("Tie at 10 points, Player 2 should we winner as new points will be 3")
    public void playTest_tieAt10Points_player2ShouldBeWinnerPlayer() {

        this.tennis.getScoreBoard().getPlayer1().setScore(10);
        this.tennis.getScoreBoard().getPlayer2().setScore(10);
        this.tennis.setTie(Boolean.TRUE);
        this.tennis.setTieBreakerPoints(2);

        Mockito.when(helperUtils.generateRandomPoint()).thenReturn(3);
        tennis.play();

        Assertions.assertNotNull(tennis.getScoreBoard().getWinner());
        Assertions.assertEquals(tennis.getScoreBoard().getPlayer2(), tennis.getScoreBoard().getWinner());
        Assertions.assertEquals("Player 2", tennis.getScoreBoard().getWinner().getName());
        Assertions.assertEquals(13, tennis.getScoreBoard().getWinner().getScore());

    }


    @Test
    @DisplayName("Tie at 20 points, Player 1 should we winner as new points will be 2")
    public void playTest_tieAt20Points_player1ShouldBeWinnerPlayer() {

        this.tennis.getScoreBoard().getPlayer1().setScore(20);
        this.tennis.getScoreBoard().getPlayer2().setScore(20);
        this.tennis.setTie(Boolean.TRUE);
        this.tennis.setTieBreakerPoints(1);

        Mockito.when(helperUtils.generateRandomPoint()).thenReturn(2);
        tennis.play();

        Assertions.assertNotNull(tennis.getScoreBoard().getWinner());
        Assertions.assertEquals(tennis.getScoreBoard().getPlayer1(), tennis.getScoreBoard().getWinner());
        Assertions.assertEquals("Player 1", tennis.getScoreBoard().getWinner().getName());
        Assertions.assertEquals(22, tennis.getScoreBoard().getWinner().getScore());

    }


    @Test
    @DisplayName("Tie at 20 points, Player 2 should we winner as new points will be 3")
    public void playTest_tieAt20Points_player2ShouldBeWinnerPlayer() {

        this.tennis.getScoreBoard().getPlayer1().setScore(20);
        this.tennis.getScoreBoard().getPlayer2().setScore(20);
        this.tennis.setTie(Boolean.TRUE);
        this.tennis.setTieBreakerPoints(1);

        Mockito.when(helperUtils.generateRandomPoint()).thenReturn(3);
        tennis.play();

        Assertions.assertNotNull(tennis.getScoreBoard().getWinner());
        Assertions.assertEquals(tennis.getScoreBoard().getPlayer2(), tennis.getScoreBoard().getWinner());
        Assertions.assertEquals("Player 2", tennis.getScoreBoard().getWinner().getName());
        Assertions.assertEquals(23, tennis.getScoreBoard().getWinner().getScore());

    }


    @Test
    @DisplayName("Player 1 served 0 times, final player should be player 2")
    public void playTest_player1Served0Times_player2ShouldBeFinalPlayer() {

        Mockito.when(helperUtils.generateRandomPoint()).thenReturn(4);
        tennis.play();

        Assertions.assertNotNull(tennis.getScoreBoard().getCurrentPlayer());
        Assertions.assertEquals(tennis.getScoreBoard().getPlayer2(), tennis.getScoreBoard().getCurrentPlayer());
        Assertions.assertEquals("Player 2", tennis.getScoreBoard().getCurrentPlayer().getName());

    }

}
