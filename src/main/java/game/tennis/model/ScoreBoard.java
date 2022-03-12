package game.tennis.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScoreBoard {

    private Player winner;
    private Player currentPlayer;
    private Player player1;
    private Player player2;

}
