package game.tennis.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Player {

    private String name;
    private int score;
    private int serves;

}
