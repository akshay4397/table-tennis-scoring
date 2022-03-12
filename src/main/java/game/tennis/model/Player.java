package game.tennis.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Player {

    private String name;
    private int score;
    private int serves;

}
