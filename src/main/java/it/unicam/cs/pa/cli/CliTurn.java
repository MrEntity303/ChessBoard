package it.unicam.cs.pa.cli;

import it.unicam.cs.pa.games.Move;
import it.unicam.cs.pa.games.Position;

public interface CliTurn {
    /**
     * This method causes the user to do some input for the move.
     * @return the Move of the player.
     */
    Move inputTurn();

    /**
     * This method causes the user to do some input for the position.
     * @return the Position of the player.
     */
    Position isValidInput(String input);
}
