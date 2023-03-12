package it.unicam.cs.pa.cli;

import it.unicam.cs.pa.games.Move;
import it.unicam.cs.pa.games.Position;

public interface CliTurn {
    Move inputTurn();
    Position isValidInput(String input);
}
